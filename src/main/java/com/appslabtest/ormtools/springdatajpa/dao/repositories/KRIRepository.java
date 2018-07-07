package com.appslabtest.ormtools.springdatajpa.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appslabtest.ormtools.model.*;

public interface KRIRepository extends JpaRepository<KRI, Integer>{
	
	@Query(value="SELECT * FROM KRI WHERE kri_code = ?1 AND kri_owner_dept = ?2", nativeQuery = true)
	public KRI findKRI(String kri_code, int kri_owner_dept);
	
	@Query(value="SELECT * FROM KRI WHERE kri_desc = ?1", nativeQuery = true)
	public List<KRI> findKRI(String kri_dec);
	
	@Query(value="SELECT * FROM KRI WHERE kri_owner_dept = ?1 AND kri_status > 0", nativeQuery = true)
	public List<KRI> findAllActiveKRIs(int kri_owner_dept);
	
	@Query(value="SELECT * FROM KRI WHERE kri_owner_dept = ?1", nativeQuery = true)
	public List<KRI> findAllKRIs(int kri_owner_dept);
}
