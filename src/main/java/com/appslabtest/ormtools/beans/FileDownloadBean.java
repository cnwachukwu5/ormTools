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
	
	public FileDownloadBean() {
		InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/templateFiles/kriTemplate.xls");
		file = new DefaultStreamedContent(stream, "application/xls", "downloaded_kriTemplate.xls");
	}

	public StreamedContent getFile() {
		return file;
	}
}
