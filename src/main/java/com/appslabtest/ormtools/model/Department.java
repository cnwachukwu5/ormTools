package com.appslabtest.ormtools.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="deptID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer deptid;
	
	@Column(name="department", nullable=false)
	private String dept_name;
	
	@OneToMany(mappedBy="department")
	private List<User> users;

	public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Department) && (deptid != null) ? deptid.equals(((Department)obj).deptid) : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (deptid != null) ? (this.getClass().hashCode() + deptid.hashCode()) : super.hashCode();
	}
	
	public String toString() {
		return "[Department_ID: " + this.getDeptid() + " Department_Name: " + this.getDept_name() + " ]";
	}

}
