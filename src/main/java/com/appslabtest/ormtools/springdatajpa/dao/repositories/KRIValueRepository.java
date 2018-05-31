package com.appslabtest.ormtools.springdatajpa.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appslabtest.ormtools.model.*;

public interface KRIValueRepository extends JpaRepository<KRI_Value, Integer>{
	
	//Return kri value for given kri, month and year submitted.
	@Query(value="SELECT * FROM KRI_Value WHERE kri_code = ?1 AND kri_value_month = ?2 AND kri_value_year = ?3", nativeQuery = true)
	public KRI_Value findKRIValue(String kri_code, int kri_value_month, String kri_value_year);
	
	//Return all KRI values for a given month and given year and a given User (user has a department)
	@Query(value="SELECT * FROM KRI_Value WHERE kri_value_month = ?1 AND kri_value_year = ?2 AND valueSubmittedBy = ?3", nativeQuery = true)
	public List<KRI_Value> findAllKRIValues_For_A_Given_Month(int kri_value_month, String kri_value_year, int valueSubmittedBy);
	
	//Return all quarterly values for a given KRI
	@Query(value="SELECT * FROM KRI_Value WHERE quarter = ?1 AND kri_value_year = ?2 AND valueSubmittedBy = ?3", nativeQuery = true)
	public List<KRI_Value> findAllKRIValues_For_A_Given_Quarter(String quarter, String kri_value_year, int valueSubmittedBy);
}
