package com.appslabtest.ormtools.beans;


import java.io.InputStream;
import java.io.Serializable;

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
		
		try(InputStream fileInputStream = event.getFile().getInputstream()){
			Workbook workbook = new HSSFWorkbook(fileInputStream);
			Sheet sheet1 = workbook.getSheetAt(0);
			//Iterator<Row> iterator = sheet1.iterator();
			
			int numRows = sheet1.getPhysicalNumberOfRows();
			
			for(int i = 0; i < numRows; i++) {
				Row nextRow = sheet1.getRow(i);
				int numCell = nextRow.getPhysicalNumberOfCells();
				for(int j = 0; j < numCell; j++) {
					Cell cell = nextRow.getCell(j);
					
					//TODO
					//Get the cell values for the first row. Check that they match the column names: "kri_desc", "kri_reason_collection", "kri_lower_bound", "kri_upper_bound" and "kri_owner_dept"
					//Autowire KRIBean to access KRI instance and the addKRI method.
					//Import KRI model class and instantiate it with each row of the excel data.
					
//					System.out.print(cell.getStringCellValue());
//					System.out.print(" ");
				}
			}
			
//			while(iterator.hasNext()) {
//				Row nextRow = iterator.next();
//				Iterator<Cell> cellIterator = nextRow.cellIterator();
//				while(cellIterator.hasNext()) {
//					Cell nextCell = cellIterator.next();
//					System.out.print(nextCell.getStringCellValue());
//				}
//			}
			workbook.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		//System.out.println("Uploaded File Name Is :: "+file.getFileName()+" :: Uploaded File Size :: "+file.getSize());
		new MessengerUtil().addMessage(FacesMessage.SEVERITY_INFO,"Got the file: ", "XLS read");
	}
}
