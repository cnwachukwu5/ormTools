package com.appslabtest.ormtools.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="month")
public class Month implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="month")
	private String month_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMonth_name() {
		return month_name;
	}

	public void setMonth_name(String month_name) {
		this.month_name = month_name;
	}
	
	public String toString() {
		return "Month: " + this.getMonth_name();
	}
}
