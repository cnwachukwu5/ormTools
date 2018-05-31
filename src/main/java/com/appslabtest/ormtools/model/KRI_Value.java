package com.appslabtest.ormtools.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="kri_value")
public class KRI_Value implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="kri_code")
	private KRI kri_code;
	
	@Column(name="kriValue", nullable=false)
	private double kri_value;
	
	@ManyToOne
	@JoinColumn(name="kri_value_month")
	private Month kri_value_month;
	
	@Column(name="kri_value_year", nullable=false)
	private String kri_value_year;
	
	@Column(name="kri_value_datetime", nullable=false)
	private Timestamp timeSubmitted;
	
	@ManyToOne
	@JoinColumn(name="kri_value_submitted_by")
	private User valueSubmittedBy;
	
	@Column(name="kri_value_justification", nullable=false)
	private String kri_value_justification;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public KRI getKri_code() {
		return kri_code;
	}

	public void setKri_code(KRI kri_code) {
		this.kri_code = kri_code;
	}

	public double getKri_value() {
		return kri_value;
	}

	public void setKri_value(double kri_value) {
		this.kri_value = kri_value;
	}

	public Month getKri_value_month() {
		return kri_value_month;
	}

	public void setKri_value_month(Month kri_value_month) {
		this.kri_value_month = kri_value_month;
	}

	public String getKri_value_year() {
		return kri_value_year;
	}

	public void setKri_value_year(String kri_value_year) {
		this.kri_value_year = kri_value_year;
	}

	public Timestamp getTimeSubmitted() {
		return timeSubmitted;
	}

	public void setTimeSubmitted(Timestamp timeSubmitted) {
		this.timeSubmitted = timeSubmitted;
	}

	public User getValueSubmittedBy() {
		return valueSubmittedBy;
	}

	public void setValueSubmittedBy(User valueSubmittedBy) {
		this.valueSubmittedBy = valueSubmittedBy;
	}

	public String getKri_value_justification() {
		return kri_value_justification;
	}

	public void setKri_value_justification(String kri_value_justification) {
		this.kri_value_justification = kri_value_justification;
	}
	
	public String toString() {
		return "KRIValue: " + 
				"KRI_CODE: " + this.getKri_code() + 
				"KRI_VALUE: " + this.getKri_value() + 
				"KRI_MONTH: " + this.getKri_value_month()
				+ "KRI_YEAR: " + this.getKri_value_year() 
				+ "SUBMITTED BY: " + this.getValueSubmittedBy()
				+ "VALUE: " + this.getKri_value();
	}
}
