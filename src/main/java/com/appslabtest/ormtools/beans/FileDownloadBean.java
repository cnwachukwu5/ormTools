package com.appslabtest.ormtools.beans;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Component
@SessionScope
public class FileDownloadBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private StreamedContent file;
	private StreamedContent file2;
	
	public FileDownloadBean() {
		InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/templateFiles/kriTemplate.xls");
		file = new DefaultStreamedContent(stream, "application/xls", "downloaded_kriTemplate.xls");
		
		InputStream stream2 = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/templateFiles/rejectedKRI.xls");
		file2 = new DefaultStreamedContent(stream2, "application/xls", "rejectedKRI.xls");
	}

	public StreamedContent getFile() {
		return file;
	}
	
	public StreamedContent getFile2() {
		return file2;
	}
}
