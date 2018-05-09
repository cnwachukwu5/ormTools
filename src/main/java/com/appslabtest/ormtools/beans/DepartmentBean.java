package com.appslabtest.ormtools.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.appslabtest.ormtools.model.Department;
import com.appslabtest.ormtools.service.DepartmentService;

@RequestScope
@Component
public class DepartmentBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	DepartmentService departmentService;
	
	private Department department = new Department();
	
	private String dept_name;
	private List<Department> departments;

	@PostConstruct
	public void init() {
		departments = getDepartmentService().findAll();
	}
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public void addDepartment(Department dept) {
		Department new_dept = new Department();
		new_dept.setDept_name(dept.getDept_name());
		
		try {
			int result = getDepartmentService().addDepartment(new_dept);
			if(result > 0) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Department successfully added", "Add department"));
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "The department already exists", "Add department"));
			}
		}catch(Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error adding department", "Add Department"));
			System.err.println(ex);
		}
	}
	
	public void allDepartments(){
		departments = getDepartmentService().findAll();
	}
}
