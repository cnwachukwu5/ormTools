package com.appslabtest.ormtools.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appslabtest.ormtools.model.KRI_Value;
import com.appslabtest.ormtools.springdatajpa.dao.repositories.KRIValueRepository;

@Service
public class KRI_ValueServiceImpl implements KRI_ValueService{
	
	@Resource
	KRIValueRepository kriValueRepository;
	
	@Override
	@Transactional
	public void addKRIValue(KRI_Value kri_Value) {
		kriValueRepository.save(kri_Value);
	}
	
	@Override
	@Transactional
	public KRI_Value findKRIValue(String kri_code, int kri_value_month, String kri_value_year) {
		return kriValueRepository.findKRIValue(kri_code, kri_value_month, kri_value_year);
	}
	
	@Override
	@Transactional
	public List<KRI_Value> findAllKRIs_Values_For_The_Month(int kri_value_month, String kri_value_year, int valueSubmittedBy){
		return kriValueRepository.findAllKRIValues_For_A_Given_Month(kri_value_month, kri_value_year, valueSubmittedBy);
	}
	
	@Override
	@Transactional
	public List<KRI_Value> findKRI_Values_For_The_Quarter(String quarter, String kri_value_year, int valueSubmittedBy, String kri_code){
		return kriValueRepository.findAllKRIValues_For_A_Given_Quarter(quarter, kri_value_year, valueSubmittedBy, kri_code);
	}

}
