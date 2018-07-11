package com.appslabtest.ormtools.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.appslabtest.ormtools.service.KRI_ValueService;
import com.appslabtest.ormtools.model.Department;
import com.appslabtest.ormtools.model.KRI;
import com.appslabtest.ormtools.model.KRI_Value;
import com.appslabtest.ormtools.model.Month;
import com.appslabtest.ormtools.model.User;
import com.appslabtest.ormtools.utilities.MessengerUtil;
import com.appslabtest.ormtools.utilities.DateUtils;


@SessionScope
@Component
public class KriValueBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	KRI_ValueService kri_ValueService;
	
	@Autowired
	KriBean kriBean;
	
	@Autowired
	UserBean userBean;
	
	private int id;
	private KRI kri_code;
	private double kri_value;
	private Month kri_value_month;
	private String kri_value_year;
	private Timestamp timeSubmitted;
	private User valueSubmittedBy;
	private String kri_value_justification;
	private String quarter;
	private MessengerUtil messengerUtil = new MessengerUtil();
	private List<KRI> allDeptKRIs;
	private int counter;
	
	private KRI_Value kri_Value2 = new KRI_Value();
	
	public int getCounter() {
		return counter;
	}
	
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public KriBean getKriBean() {
		return kriBean;
	}
	public void setKriBean(KriBean kriBean) {
		this.kriBean = kriBean;
	}
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUser(UserBean userBean) {
		this.userBean = userBean;
	}
	
	public List<KRI> getAllDeptKRIs() {
		return allDeptKRIs;
	}
	public void setAllDeptKRIs(List<KRI> allDeptKRIs) {
		this.allDeptKRIs = allDeptKRIs;
	}
	public KRI_Value getKri_Value2() {
		return kri_Value2;
	}
	public void setKri_Value2(KRI_Value kri_Value2) {
		this.kri_Value2 = kri_Value2;
	}
	public KRI_ValueService getKri_ValueService() {
		return kri_ValueService;
	}
	public void setKri_ValueService(KRI_ValueService kri_ValueService) {
		this.kri_ValueService = kri_ValueService;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public KRI getKri_code() {
		return kri_code;
	}
	public void setKri_code(KRI kri_code) {
		this.kri_code = kri_code;
	}
	public double getKri_value() {
		return kri_value;
	}
	public void setKri_value(double kri_value) {
		this.kri_value = kri_value;
	}
	public Month getKri_value_month() {
		return kri_value_month;
	}
	public void setKri_value_month(Month kri_value_month) {
		this.kri_value_month = kri_value_month;
	}
	public String getKri_value_year() {
		return kri_value_year;
	}
	public void setKri_value_year(String kri_value_year) {
		this.kri_value_year = kri_value_year;
	}
	public Timestamp getTimeSubmitted() {
		return timeSubmitted;
	}
	public void setTimeSubmitted(Timestamp timeSubmitted) {
		this.timeSubmitted = timeSubmitted;
	}
	public User getValueSubmittedBy() {
		return valueSubmittedBy;
	}
	public void setValueSubmittedBy(User valueSubmittedBy) {
		this.valueSubmittedBy = valueSubmittedBy;
	}
	public String getKri_value_justification() {
		return kri_value_justification;
	}
	public void setKri_value_justification(String kri_value_justification) {
		this.kri_value_justification = kri_value_justification;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	
	public void addValue() {
		KRI_Value newKRIValue = new KRI_Value();
		DateUtils current = new DateUtils();
		try {
			newKRIValue.setKri_code(kriBean.getKri());
			newKRIValue.setKri_value(kri_Value2.getKri_value());
			newKRIValue.setKri_value_justification(kri_Value2.getKri_value_justification());
			newKRIValue.setKri_value_month(kri_Value2.getKri_value_month());
			newKRIValue.setKri_value_year(current.currentYear());
			newKRIValue.setTimeSubmitted(current.currentTimeStamp());
			newKRIValue.setValueSubmittedBy(userBean.getUser());
			newKRIValue.setKri_value_justification(kri_Value2.getKri_value_justification());
			newKRIValue.setQuarter(current.currentQuarter());
			
			getKri_ValueService().addKRIValue(newKRIValue);
			//messengerUtil.addMessage(FacesMessage.SEVERITY_INFO, "KRI value added succesfully", "Add new KRI value");
			
		}catch(DataAccessException accessException) {
			messengerUtil.addMessage(FacesMessage.SEVERITY_ERROR, "Error: " + accessException.getMessage(), "KRI value creation Error");
		}
	}//End of addValue method
	
	public List<KRI> getAllActiveKRIForDepartment(Department department){
		/**
		 * Returns all active KRIs for the department passed as parameter
		 * @param department
		 * @return List of KRIs for the department
		 */
		return getKriBean().getAllActiveKRIsForDepartment(department);
	}
	
	public void getDepartmentKRIs() {
		/**
		 * Returns all active kris for the user department and set the first kri instance
		 */
		
		allDeptKRIs = getAllActiveKRIForDepartment(getUserBean().getLoggeduser().getDepartment());
		
		if(allDeptKRIs == null || allDeptKRIs.isEmpty()) {
			messengerUtil.addMessage(FacesMessage.SEVERITY_ERROR, "There are not KRIs for the user department.", "Get User KRIs");
		}else {
			setCounter(0);
			getKriBean().setKri(allDeptKRIs.get(getCounter()));
		}
	}
	
	public String getStatusUpdate() {
		return (getCounter() + 1) + " of " + allDeptKRIs.size();
	}
	
	public void showInjectedValues() {
		System.out.println("Logged in user: " + getUserBean().getUser().getUsername() + " " + getUserBean().getLoggeduser().getEmail() 
				+ " " + getUserBean().getLoggeduser().getDepartment());
	}
}
