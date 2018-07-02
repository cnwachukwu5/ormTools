package com.appslabtest.ormtools.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.util.Pair;

import javax.faces.application.FacesMessage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.appslabtest.ormtools.model.Department;
import com.appslabtest.ormtools.model.KRI;
import com.appslabtest.ormtools.service.DepartmentService;
import com.appslabtest.ormtools.utilities.IDGen;
import com.appslabtest.ormtools.utilities.MessengerUtil;



@Component
@SessionScope
public class FileUploadBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private UploadedFile file;
	
	@Autowired
	private KriBean kriBean;
	
	@Autowired
	private DepartmentService departmentService;
	
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public KriBean getKriBean() {
		return kriBean;
	}

	public void setKriBean(KriBean kriBean) {
		this.kriBean = kriBean;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void handleKRIUpload(FileUploadEvent event) {
		List<KRI> newKRIs = new ArrayList<>();
		List<Pair<String, Row>> rejects = new ArrayList<>();
		
		try(InputStream fileInputStream = event.getFile().getInputstream()){
			Workbook workbook = new HSSFWorkbook(fileInputStream);
			Sheet sheet1 = workbook.getSheetAt(0);
			
			int numRows = sheet1.getPhysicalNumberOfRows();
			
			//Get the first row and confirm that the columns headers are correct
			Row headerRow = sheet1.getRow(0);
			boolean validated = validateHeaderRow(headerRow);
			
			if(validated) {
				for(int i = 1; i < numRows; i++) {
					Row nextRow = sheet1.getRow(i);
					int numCell = nextRow.getPhysicalNumberOfCells();
					
					KRI newKRI = new KRI();
					
					for(int j = 0; j < numCell; j++) {
						Cell cell = nextRow.getCell(j);
						if(cell != null) {
							switch(getCellIndex(cell)) {
								case 0:
									List<KRI> kri = getKriBean().findOneKRI((String)getCellVaue(cell));
									if(!(kri.isEmpty())) {
										//System.out.println("List is not empty");
										String errorMessage = "Duplicate KRI entry";
										rejects.add(new Pair<String, Row>(errorMessage, nextRow));
										
									}else {
										newKRI.setKri_desc((String)getCellVaue(cell));	
									}
									break;
								case 1:
									newKRI.setKri_reason_for_collection((String)getCellVaue(cell));
									break;
								case 2:
									newKRI.setKri_lower_bound((double)getCellVaue(cell));
									break;
								case 3:
									newKRI.setKri_upper_bound((double)getCellVaue(cell));
									break;
								case 4: 
									Department kridept = new Department();
									kridept.setDept_name((String)getCellVaue(cell));
									Department dept = getDepartmentService().findByName(kridept);
									if(dept == null) {
										String errorMessage = "Department does not exist. Create the department in the settings module";
										rejects.add(new Pair<String, Row>(errorMessage, nextRow));
									}else {
										newKRI.setKri_owner_dept(dept);
									}
									break;
							}//End of switch statement
							
						}//End of if statement - check if cell is null
						
					}//End inner for loop 
					try {
						//Validate that the KRI instance has all properties set before adding to the list
						if(newKRI.getKri_desc() != null && !(newKRI.getKri_reason_for_collection().equals("")) && (newKRI.getKri_lower_bound() < newKRI.getKri_upper_bound()) && 
								(newKRI.getKri_owner_dept() != null)) {
							String kriID = new IDGen().getGeneratedID(newKRI.getKri_owner_dept().getDept_name());
							newKRI.setKri_code(kriID);
							newKRI.setKri_status(true);
							newKRI.setKri_deactivate_reason("");
							
							//Add KRI to newKRIs list
							newKRIs.add(newKRI);
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
					
						
				}//End of outer for - statement			
				System.out.println("newKRIs size: " + newKRIs.size());
				boolean status = false;
				try{
					//Add to db
					if(!(newKRIs.isEmpty())) {
						status  = getKriBean().addBatch(newKRIs);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				System.out.println("Status: " + status);
				
				System.out.println("Rejects size after: " + rejects.size());
				
				if(rejects.isEmpty() && status) {
					new MessengerUtil().addMessage(FacesMessage.SEVERITY_INFO,"KRIs added successfully ", "Batch upload KRIs");
				}else {
					writeRejectsToFile(rejects);
					new MessengerUtil().addMessage(FacesMessage.SEVERITY_WARN,"Some KRIs were not uploaded. Check the reject panel for details ", "Batch upload KRIs");
				}
				
			}else {
				new MessengerUtil().addMessage(FacesMessage.SEVERITY_ERROR,"Error processing file. Invalid column headers ", "XLS read");
			}
			
			workbook.close();
		}catch (Exception e) {
			// TODO: handle exception
		}		
	}
	
	public void writeRejectsToFile(List<Pair<String, Row>> rejectedKRIs) {
		String[] columnHeaders = {"kri_desc", "kri_reason_collection", "kri_lower_bound", "kri_upper_bound", "kri_owner_dept"};
		
		Workbook workbook = new HSSFWorkbook();
		
		Sheet sheet = workbook.createSheet("Rejected KRIs");
		
		//Create font to display error message
		Font errorFont = workbook.createFont();
		errorFont.setBold(true);
		errorFont.setColor(IndexedColors.RED.getIndex());
		
		//Create a cellStyle to display the error
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(errorFont);
		
		//Create the header row
		Row headerRow = sheet.createRow(0);
		
		for(int i = 0; i < columnHeaders.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columnHeaders[i]);
		}
		
		//create other rows
		int rowNum = 1;
		for(Pair<String, Row> entry : rejectedKRIs) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(entry.getValue().getCell(0).getStringCellValue());
			row.createCell(1).setCellValue(entry.getValue().getCell(1).getStringCellValue());
			row.createCell(2).setCellValue(entry.getValue().getCell(2).getNumericCellValue());
			row.createCell(3).setCellValue(entry.getValue().getCell(3).getNumericCellValue());
			row.createCell(4).setCellValue(entry.getValue().getCell(4).getStringCellValue());
			
			Cell cell = row.createCell(5);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(entry.getKey());
		}
		
		// Resize all columns to fit the content size
		for(int i = 0; i < columnHeaders.length + 1; i++) {
			sheet.autoSizeColumn(i);
		}
		
		// Write the output to a file
		File file = new File("C:\\Users\\cyprian\\eclipse-workspace\\ormTools\\WebContent\\resources\\templateFiles\\rejectedKRI.xls"); //Set this to server directory
		try(FileOutputStream outputStream = new FileOutputStream(file)){
			workbook.write(outputStream);
			workbook.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Object getCellVaue(Cell cell) {
		
		switch(cell.getCellTypeEnum()) {
			case STRING: 
				return cell.getStringCellValue();
			case NUMERIC: 
				return cell.getNumericCellValue();
		default:
			break;	
		}
		return null;
	}
	
	private int getCellIndex (Cell cell) {
		
		return cell.getColumnIndex();
	}
	
	private boolean validateHeaderRow(Row firstRow) {
		Iterator<Cell> cellIterator = firstRow.cellIterator();
		String headerCell0 = ""; String headerCell1 = ""; String headerCell2 = ""; String headerCell3 = ""; String headerCell4 = "";
		boolean validated = false;
		
		while(cellIterator.hasNext()) {
			Cell nextCell = cellIterator.next();
			switch(getCellIndex(nextCell)) {
				case 0: 
					headerCell0 = (String)getCellVaue(nextCell);
					break;
				case 1: 
					headerCell1 = (String)getCellVaue(nextCell);
					break;
				case 2: 
					headerCell2 = (String)getCellVaue(nextCell);
					break;
				case 3: 
					headerCell3 = (String)getCellVaue(nextCell);
					break;
				case 4: 
					headerCell4 = (String)getCellVaue(nextCell);
					break;
			}
		}
		
		if(headerCell0.equals("kri_desc") && headerCell1.equals("kri_reason_collection") && headerCell2.equals("kri_lower_bound") && headerCell3.equals("kri_upper_bound") && headerCell4.equals("kri_owner_dept")) {
			validated = true;
		}
		return validated;
	}//End of method
	
}
