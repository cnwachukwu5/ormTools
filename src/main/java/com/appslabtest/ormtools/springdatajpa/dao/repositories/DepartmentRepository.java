package com.appslabtest.ormtools.springdatajpa.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.appslabtest.ormtools.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	@Query(value="SELECT * FROM Department WHERE department LIKE %?1", nativeQuery = true)
	public Department findDepartment(String dept_name);
	
	@Modifying
	@Query(value="INSERT INTO Department (department) values(?1)", nativeQuery = true)
	public int addDepartment(String dept_name);

}
