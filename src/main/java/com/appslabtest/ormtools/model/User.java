package com.appslabtest.ormtools.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="userID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userid;
	
	@Column(name="firstname", nullable=false)
	private String firstname;
	
	@Column(name="lastname", nullable=false)
	private String lastname;
	
	@Column(name="username", nullable=false)
	private String username;
	
	@Column(name="password", nullable=false)
	private String pwd;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@ManyToOne
	@JoinColumn(name="deptID")
	private Department department;
	
	@ManyToOne
	@JoinColumn(name="roleID")
	private Role role;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public void reset() {
		this.setDepartment(null);
		this.setEmail("");
		this.setFirstname("");
		this.setLastname("");
		this.setPwd("");
		this.setRole(null);
		this.setUsername("");
	}
	
	public String toString() {
		return "User: " + 
				"\n\tUserID: " + this.userid +
				"\n\tFirstName: " + this.firstname + 
				"\n\tLastName: " + this.lastname + 
				"\n\tUsername: " + this.username + 
				"\n\tEmail: " + this.email +
				"\n\tDepartment: " + this.department +
				"\n\tRole: " + this.role;
	}

}
