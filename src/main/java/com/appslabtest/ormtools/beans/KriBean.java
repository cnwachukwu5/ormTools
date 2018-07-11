package com.appslabtest.ormtools.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.primefaces.context.RequestContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.dao.DataAccessException;

import com.appslabtest.ormtools.model.KRI;
import com.appslabtest.ormtools.model.Department;
import com.appslabtest.ormtools.service.DepartmentService;
import com.appslabtest.ormtools.service.KRIService;
import com.appslabtest.ormtools.utilities.MessengerUtil;
import com.appslabtest.ormtools.utilities.IDGen;


@SessionScope
@Component
public class KriBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	KRIService kriService;
	
	@Autowired
	DepartmentService departmentService;
	
	private String kri_code;
	private String kri_desc;
	private String kri_reason_for_collection;
	private double kri_lower_bound;
	private double kri_upper_bound;
	private Department kri_owner_dept;
	private boolean kri_status;
	private String kri_deactivate_reason;
	private KRI kri = new KRI();
	private MessengerUtil messengerUtil = new MessengerUtil();
	private boolean isValidated = false;
	private List<KRI> allDeptKRIs;
	
	
	public List<KRI> getAllDeptKRIs() {
		return allDeptKRIs;
	}
	public void setAllDeptKRIs(List<KRI> allDeptKRIs) {
		this.allDeptKRIs = allDeptKRIs;
	}
	public DepartmentService getDepartmentService() {
		return departmentService;
	}
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	public KRIService getKriService() {
		return kriService;
	}
	public void setKriService(KRIService kriService) {
		this.kriService = kriService;
	}
	public String getKri_code() {
		return kri_code;
	}
	public void setKri_code(String kri_code) {
		this.kri_code = kri_code;
	}
	public String getKri_desc() {
		return kri_desc;
	}
	public void setKri_desc(String kri_desc) {
		this.kri_desc = kri_desc;
	}
	public String getKri_reason_for_collection() {
		return kri_reason_for_collection;
	}
	public void setKri_reason_for_collection(String kri_reason_for_collection) {
		this.kri_reason_for_collection = kri_reason_for_collection;
	}
	public double getKri_lower_bound() {
		return kri_lower_bound;
	}
	public void setKri_lower_bound(double kri_lower_bound) {
		this.kri_lower_bound = kri_lower_bound;
	}
	public double getKri_upper_bound() {
		return kri_upper_bound;
	}
	public void setKri_upper_bound(double kri_upper_bound) {
		this.kri_upper_bound = kri_upper_bound;
	}
	public Department getKri_owner_dept() {
		return kri_owner_dept;
	}
	public void setKri_owner_dept(Department kri_owner_dept) {
		this.kri_owner_dept = kri_owner_dept;
	}
	public boolean isKri_status() {
		return kri_status;
	}
	public void setKri_status(boolean kri_status) {
		this.kri_status = kri_status;
	}
	public String getKri_deactivate_reason() {
		return kri_deactivate_reason;
	}
	public void setKri_deactivate_reason(String kri_deactivate_reason) {
		this.kri_deactivate_reason = kri_deactivate_reason;
	}
	public KRI getKri() {
		return kri;
	}
	public void setKri(KRI kri) {
		this.kri = kri;
	}
	
	public void addKRI() {
		/*
		 * This method adds a new KRI to the KRI table
		 */
		checkThresholdValues();
		if(isValidated) {
			 try {
				 List<KRI> kris = findOneKRI(kri.getKri_desc());
				 if((kris.isEmpty())) {
					 KRI newKRI = new KRI();
						String kriID = new IDGen().getGeneratedID(kri.getKri_owner_dept().getDept_name());
						newKRI.setKri_code(kriID);
						newKRI.setKri_desc(kri.getKri_desc());
						newKRI.setKri_reason_for_collection(kri.getKri_reason_for_collection());
						newKRI.setKri_lower_bound(kri.getKri_lower_bound());
						newKRI.setKri_upper_bound(kri.getKri_upper_bound());
						newKRI.setKri_owner_dept(kri.getKri_owner_dept());
						newKRI.setKri_status(kri.isKri_status());
						newKRI.setKri_deactivate_reason("");
						
						getKriService().addKRI(newKRI);
						reset();
						messengerUtil.addMessage(FacesMessage.SEVERITY_INFO, "KRI created succesfully", "Add new KRI");
				 }else {
					 messengerUtil.addMessage(FacesMessage.SEVERITY_ERROR, "This KRI already exists", "Add new KRI");
				 }
					
				}catch(DataAccessException dataAccessException) {
					messengerUtil.addMessage(FacesMessage.SEVERITY_ERROR, "Error: " + dataAccessException.getMessage(), "KRI creation Error");
				}
		 }else {
			 messengerUtil.addMessage(FacesMessage.SEVERITY_ERROR, "Low threshold bound should not be greater than upper bound...", "KRI Threshold values");
		 }
		
	}//End of addKRI
	
	public void reset() {
		kri.setKri_code("");
		kri.setKri_desc("");
		kri.setKri_reason_for_collection("");
		kri.setKri_lower_bound(0);
		kri.setKri_upper_bound(0);
		kri.setKri_owner_dept(null);
		kri.setKri_status(false);
		kri.setKri_deactivate_reason("");
	}
	
	public List<KRI> findOneKRI(String kri_desc) {
		return getKriService().findKRI(kri_desc);
	}
	
	public void checkKRI() {
		try {
			KRI kriExists = getKriService().findKRI(kri.getKri_code(), kri.getKri_owner_dept().getDeptid());
			if(kriExists == null) {
				messengerUtil.addMessage(FacesMessage.SEVERITY_ERROR, "There is no record for this KRI", "Find KRI");
			}else {
				kri.setKri_code(kriExists.getKri_code());
				kri.setKri_deactivate_reason(kriExists.getKri_deactivate_reason());
				kri.setKri_desc(kriExists.getKri_desc());
				kri.setKri_lower_bound(kriExists.getKri_lower_bound());
				kri.setKri_owner_dept(kriExists.getKri_owner_dept());
				kri.setKri_reason_for_collection(kriExists.getKri_reason_for_collection());
				kri.setKri_status(kriExists.isKri_status());
				kri.setKri_upper_bound(kriExists.getKri_upper_bound());
			}
		}catch (Exception e) {
			messengerUtil.addMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e, "KRI check error");
		}
		
	}
	
	public void getKRIProperties() {
		System.out.println("This is invoked ...");
		try {
			List<KRI> kris = getKriService().findKRI(kri.getKri_desc());
			if(!(kris.isEmpty())) {
				kri = kris.get(0);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateKRI() {
		System.out.println(kri.getKri_desc()); //partialSubmit
		if(kri == null) {
			messengerUtil.addMessage(FacesMessage.SEVERITY_ERROR, "KRI does not exist", "Update KRI");
		}else {
			try {
				getKriService().updateKRI(kri);
				messengerUtil.addMessage(FacesMessage.SEVERITY_INFO, "KRI updated successfully", "Update KRI");
			}catch (Exception e) {
				messengerUtil.addMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e, "KRI update error");
			}
			
		}
			
	}
	
	public void deleteKRI() {
		if(kri == null) {
			messengerUtil.addMessage(FacesMessage.SEVERITY_ERROR, "KRI does not exist", "Delete KRI");
		}else {
			try {
				if(!(kri.isKri_status())) {
					messengerUtil.addMessage(FacesMessage.SEVERITY_ERROR, "This KRI has already been deactivated", "Delete KRI");
				}else {
					getKriService().deleteKRI(kri);
					messengerUtil.addMessage(FacesMessage.SEVERITY_INFO, "KRI deleted successfully", "Delete KRI");
				}				
				
			}catch (Exception e) {
				messengerUtil.addMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e, "KRI deletion error");
			}
			
		}
	}
	
	public List<KRI> getAllActiveKRIsForDepartment(Department department){
		List<KRI> allActiveKRI = getKriService().findAllActiveKRI(department.getDeptid());
		if(allActiveKRI.isEmpty()) {
			//messengerUtil.addMessage(FacesMessage.SEVERITY_ERROR, "There are not active KRIs for this department...", "All active KRIs");
			return null;
		}else {
			return allActiveKRI;
		}
	}
	
	public void getAllKRIsForDepartment(){
		List<KRI> allKRIs = getKriService().findAllKRIs(kri.getKri_owner_dept().getDeptid());
		if(allKRIs.isEmpty()) {
			messengerUtil.addMessage(FacesMessage.SEVERITY_ERROR, "There are no KRIs for this department...", "All KRIs");
			
		}else {
			setAllDeptKRIs(allKRIs);
		}
	}
	
	public void checkThresholdValues() {
		
		if(kri.getKri_lower_bound() > kri.getKri_upper_bound()) {
			isValidated = false;
			//messengerUtil.addMessage(FacesMessage.SEVERITY_ERROR, "Low threshold bound should not be greater than upper bound...", "KRI Threshold values");
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,  "KRI Threshold values", "Low threshold bound must be less than upper threshold bound...");
			RequestContext.getCurrentInstance().showMessageInDialog(facesMessage);
		}else {
			isValidated = true;
		}
	}
	
	public boolean addBatch(List<KRI> kris) {
		System.out.println("I started executing");
		
		boolean result = false; 
		
		System.out.println("Size is: " + kris.size());
		for(int i = 0; i < kris.size(); i++) {
			kri = kris.get(i);
			getKriService().addKRI(kri);			
		}
		result = true;
		return result;
	}

}
