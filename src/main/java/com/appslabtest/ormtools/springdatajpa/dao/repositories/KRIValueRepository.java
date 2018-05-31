package com.appslabtest.ormtools.springdatajpa.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appslabtest.ormtools.model.*;

public interface KRIValueRepository extends JpaRepository<KRI_Value, Integer>{
	//TODO 1. find kriValue for a given month and given year and a given User
	//TODO 2. KRIValues for a given month and given year and a given User and quarter
}
