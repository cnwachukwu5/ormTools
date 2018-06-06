package com.appslabtest.ormtools.springdatajpa.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appslabtest.ormtools.model.Month;

public interface MonthRepository extends JpaRepository<Month, Long>{
	
	@Query(value="SELECT * FROM month where month LIKE %?1", nativeQuery = true)
	public Month findMonthByName(String name);

}
