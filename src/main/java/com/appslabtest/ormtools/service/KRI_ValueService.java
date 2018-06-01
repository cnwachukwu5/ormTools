package com.appslabtest.ormtools.service;

import java.util.List;

import com.appslabtest.ormtools.model.KRI_Value;

public interface KRI_ValueService {
	
	void addKRIValue(KRI_Value kri_Value);
	
	//void updateKRI_Value(KRI_Value kri_Value) throws Exception; //Will be enabled upon request
	//void deleteKRI_Value(KRI_Value kri_Value) throws Exception; //Will be enabled upon request
	
	KRI_Value findKRIValue(String kri_code, int kri_value_month, String kri_value_year);
	
	List<KRI_Value> findAllKRIs_Values_For_The_Month(int kri_value_month, String kri_value_year, int valueSubmittedBy);
	
	List<KRI_Value> findKRI_Values_For_The_Quarter(String quarter, String kri_value_year, int valueSubmittedBy, String kri_code);
}
