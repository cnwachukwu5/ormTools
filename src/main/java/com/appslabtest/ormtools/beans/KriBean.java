package com.appslabtest.ormtools.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.appslabtest.ormtools.model.KRI;
import com.appslabtest.ormtools.model.Department;
import com.appslabtest.ormtools.service.KRIService;
import com.appslabtest.ormtools.utilities.MessengerUtil;


@SessionScope
@Component
public class KriBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	KRIService kriService;
	
	private String kri_code;
	private String kri_desc;
	private String kri_reason_for_collection;
	private double kri_lower_bound;
	private double kri_upper_bound;
	private Department kri_owner_dept;
	private boolean kri_status;
	private String kri_deactivate_reason;
	private KRI kri;
	
	public KRIService getKriService() {
		return kriService;
	}
	public void setKriService(KRIService kriService) {
		this.kriService = kriService;
	}
	public String getKri_code() {
		return kri_code;
	}
	public void setKri_code(String kri_code) {
		this.kri_code = kri_code;
	}
	public String getKri_desc() {
		return kri_desc;
	}
	public void setKri_desc(String kri_desc) {
		this.kri_desc = kri_desc;
	}
	public String getKri_reason_for_collection() {
		return kri_reason_for_collection;
	}
	public void setKri_reason_for_collection(String kri_reason_for_collection) {
		this.kri_reason_for_collection = kri_reason_for_collection;
	}
	public double getKri_lower_bound() {
		return kri_lower_bound;
	}
	public void setKri_lower_bound(double kri_lower_bound) {
		this.kri_lower_bound = kri_lower_bound;
	}
	public double getKri_upper_bound() {
		return kri_upper_bound;
	}
	public void setKri_upper_bound(double kri_upper_bound) {
		this.kri_upper_bound = kri_upper_bound;
	}
	public Department getKri_owner_dept() {
		return kri_owner_dept;
	}
	public void setKri_owner_dept(Department kri_owner_dept) {
		this.kri_owner_dept = kri_owner_dept;
	}
	public boolean isKri_status() {
		return kri_status;
	}
	public void setKri_status(boolean kri_status) {
		this.kri_status = kri_status;
	}
	public String getKri_deactivate_reason() {
		return kri_deactivate_reason;
	}
	public void setKri_deactivate_reason(String kri_deactivate_reason) {
		this.kri_deactivate_reason = kri_deactivate_reason;
	}
	public KRI getKri() {
		return kri;
	}
	public void setKri(KRI kri) {
		this.kri = kri;
	}
	
	

}
