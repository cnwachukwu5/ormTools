package com.appslabtest.ormtools.service;

import java.util.List;

import com.appslabtest.ormtools.model.Department;


public interface DepartmentService {
	
	public int addDepartment(Department department);
	public Department findByName(Department department);
	public List<Department> findAll();

}
