package com.appslabtest.ormtools.service;

import java.util.List;

import com.appslabtest.ormtools.model.KRI;

public interface KRIService {
	
	 void addKRI(KRI kri);
	 void updateKRI(KRI kri) throws Exception;
	 void deleteKRI(KRI kri) throws Exception;
	 KRI findKRI(String kri_code, int dept_id) throws Exception;
	 List<KRI> findAllActiveKRI(int dept_id);
	 List<KRI> findAllKRIs(int dept_id);
}
