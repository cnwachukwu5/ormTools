package com.appslabtest.ormtools.beans;


import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.appslabtest.ormtools.model.Department;
import com.appslabtest.ormtools.model.KRI;
import com.appslabtest.ormtools.utilities.MessengerUtil;



@Component
@SessionScope
public class FileUploadBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private UploadedFile file;
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void handleKRIUpload(FileUploadEvent event) {
		List<KRI> newKRIs = new ArrayList<>();
		
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
					KRI newKRI = new KRI();
					int numCell = nextRow.getPhysicalNumberOfCells();
					
					for(int j = 0; j < numCell; j++) {
						Cell cell = nextRow.getCell(j);
						if(cell != null) {
							switch(getCellIndex(cell)) {
								case 0:
									newKRI.setKri_desc((String)getCellVaue(cell));	
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
									Department kri_owner_dept = new Department();
									kri_owner_dept.setDept_name((String)getCellVaue(cell));
									newKRI.setKri_owner_dept(kri_owner_dept);
									break;
							}
						}
					}
					
					newKRIs.add(newKRI);
					
				}//End of for - statement
				/*TODO
				 * 1. Auto wire kriBean. loop through the list, create kri_code and insert on the db.
				 * 2. Display message after successful data insertion into the db
				 */
			}else {
				new MessengerUtil().addMessage(FacesMessage.SEVERITY_ERROR,"Error processing file. Invalid column headers ", "XLS read");
			}
			
			workbook.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		//System.out.println("Uploaded File Name Is :: "+file.getFileName()+" :: Uploaded File Size :: "+file.getSize());
		new MessengerUtil().addMessage(FacesMessage.SEVERITY_INFO,"Got the file: ", "XLS read");
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
