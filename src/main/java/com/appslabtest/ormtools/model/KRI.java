package com.appslabtest.ormtools.model;

import java.io.Serializable; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="kri")

public class KRI implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="kri_code", nullable=false)
	private String kri_code;
	
	@Column(name="kri_desc", nullable=false)
	private String kri_desc;
	
	@Column(name="kri_reason_collection", nullable=false)
	private String kri_reason_for_collection;
	
	@Column(name="kri_lower_bound", nullable=false)
	private double kri_lower_bound;
	
	@Column(name="kri_upper_bound", nullable=false)
	private double kri_upper_bound;
	
	@ManyToOne
	@JoinColumn(name="kri_owner_dept")
	private Department kri_owner_dept;
	
	@Column(name="kri_status", nullable=false)
	private boolean kri_status;
	
	@Column(name="kri_deactivate_reason")
	private String kri_deactivate_reason;

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
	
	public String toString() {
		return "KRI: " + 
				"\n\tKRI_Code: " + this.getKri_code() + 
				"\n\tKRI_DESC: " + this.getKri_desc() + 
				"\n\tKRI_REASON_COLLECTION: " + this.getKri_reason_for_collection() + 
				"\n\tKRI_Lower_Limit: " + this.getKri_lower_bound() + 
				"\n\tKRI_Upper_Limit: " + this.getKri_upper_bound() + 
				"\n\tKRI_Owner_Department: " + this.getKri_owner_dept() +
				"\n\tKRI_Status: " + this.isKri_status() + 
				"\n\tKRI_Deactivate_Status: " + this.getKri_deactivate_reason();
	}

}
