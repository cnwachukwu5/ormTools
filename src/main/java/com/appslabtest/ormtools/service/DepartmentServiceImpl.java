package com.appslabtest.ormtools.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appslabtest.ormtools.model.Department;
import com.appslabtest.ormtools.service.DepartmentService;
import com.appslabtest.ormtools.springdatajpa.dao.repositories.DepartmentRepository;


@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Resource
	DepartmentRepository departmentRepository;
	
	@Override
	@Transactional
	public Department findByName(Department department) {
		Department dept = departmentRepository.findDepartment(department.getDept_name());
		return dept;
	}
	
	@Override
	@Transactional
	public int addDepartment(Department department) {
		int result = 0;
		Department deptAlreadyExist = findByName(department);
		
		if(deptAlreadyExist == null) {
			
			result = departmentRepository.addDepartment(department.getDept_name());
		}else {
			result = -1;
		}
		return result;
	}
	
	@Override
	@Transactional
	public List<Department> findAll(){
		return departmentRepository.findAll();
	}

}
