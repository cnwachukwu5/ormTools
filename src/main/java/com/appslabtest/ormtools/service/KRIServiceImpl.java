package com.appslabtest.ormtools.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appslabtest.ormtools.model.KRI;
import com.appslabtest.ormtools.springdatajpa.dao.repositories.KRIRepository;

@Service
public class KRIServiceImpl implements KRIService{
	
	@Resource
	KRIRepository kriRepository;
	
	@Override
	@Transactional
	public void addKRI(KRI kri) {
		kriRepository.save(kri);
	}
	
	@Override
	@Transactional
	public KRI findKRI(String kri_code, int dept_id) throws Exception{
		return kriRepository.findKRI(kri_code, dept_id);
	}
	
	@Override
	@Transactional
	public List<KRI> findKRI(String kri_dec) {
		return kriRepository.findKRI(kri_dec);
	}
	
	@Override
	@Transactional
	public void updateKRI(KRI kri) throws Exception{
		KRI kriToUpdate = findKRI(kri.getKri_code(), kri.getKri_owner_dept().getDeptid());
		
		if(kriToUpdate == null) {
			throw new Exception("KRI not found...");
		}
		kriToUpdate.setKri_deactivate_reason(kri.getKri_deactivate_reason());
		kriToUpdate.setKri_desc(kri.getKri_desc());
		kriToUpdate.setKri_lower_bound(kri.getKri_lower_bound());
		kriToUpdate.setKri_upper_bound(kri.getKri_upper_bound());
		kriToUpdate.setKri_status(kri.isKri_status());
		kriToUpdate.setKri_reason_for_collection(kri.getKri_reason_for_collection());
		
		kriRepository.save(kriToUpdate);
	}
	
	@Override
	@Transactional
	public void deleteKRI(KRI kri) throws Exception{
		KRI kriToDelete = findKRI(kri.getKri_code(), kri.getKri_owner_dept().getDeptid());
		
		if(kriToDelete == null) {
			throw new Exception("KRI not found...");
		}
		
		kriToDelete.setKri_status(false);
		kriRepository.save(kriToDelete);
	}
	
	@Override
	@Transactional
	public List<KRI> findAllActiveKRI(int dept_id){
		List<KRI> allActiveKRIs = kriRepository.findAllActiveKRIs(dept_id);
		return allActiveKRIs;
	}
	
	@Override
	@Transactional
	public List<KRI> findAllKRIs(int dept_id){
		List<KRI> allKRIsForDept = kriRepository.findAllKRIs(dept_id);
		return allKRIsForDept;
	}
}
