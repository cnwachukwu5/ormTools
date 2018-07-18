package com.appslabtest.ormtools.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
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
	private KRI kri;
	private double kri_value;
	private String kri_value_month;
	private String kri_value_year;
	private Timestamp timeSubmitted;
	private User valueSubmittedBy;
	private String kri_value_justification;
	private String quarter;
	private MessengerUtil messengerUtil = new MessengerUtil();
	private List<KRI> allDeptKRIs;
	private List<KRI_Value> kriWithValues = new ArrayList<>();
	private int counter;
	private boolean disablePreviousButton;
	private boolean disableNextButton;
	private boolean disableSubmitButton;
	private String listStatus;
	
	private KRI_Value kri_Value2 = new KRI_Value();
	
	public boolean isDisablePreviousButton() {
		return disablePreviousButton;
	}

	public void setDisablePreviousButton(boolean disablePreviousButton) {
		this.disablePreviousButton = disablePreviousButton;
	}

	public boolean isDisableNextButton() {
		return disableNextButton;
	}

	public void setDisableNextButton(boolean disableNextButton) {
		this.disableNextButton = disableNextButton;
	}

	public boolean isDisableSubmitButton() {
		return disableSubmitButton;
	}

	public void setDisableSubmitButton(boolean disableSubmitButton) {
		this.disableSubmitButton = disableSubmitButton;
	}

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
	public KRI getKri() {
		return kri;
	}
	public void setKri(KRI kri) {
		this.kri = kri;
	}
	public double getKri_value() {
		return kri_value;
	}
	public void setKri_value(double kri_value) {
		this.kri_value = kri_value;
	}
	public String getKri_value_month() {
		return kri_value_month;
	}
	public void setKri_value_month(String kri_value_month) {
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
	
	public KRI_Value addKRIValue(KRI kri) {
		KRI_Value newKRIValue = new KRI_Value();
		DateUtils current = new DateUtils();
		try {
			//KRI kri = getKriBean().getKri();
			newKRIValue.setKri(kri);
			newKRIValue.setKri_value(0.0);
			newKRIValue.setKri_value_justification("");
			newKRIValue.setKri_value_month(current.kriMonth());
			newKRIValue.setKri_value_year(current.currentYear());
			newKRIValue.setTimeSubmitted(current.currentTimeStamp());
			newKRIValue.setValueSubmittedBy(getUserBean().getUser());
			newKRIValue.setQuarter(current.currentQuarter());
			
			//getKri_ValueService().addKRIValue(newKRIValue);
			//messengerUtil.addMessage(FacesMessage.SEVERITY_INFO, "KRI value added succesfully", "Add new KRI value");
			
		}catch(DataAccessException accessException) {
			messengerUtil.addMessage(FacesMessage.SEVERITY_ERROR, "Error: " + accessException.getMessage(), "KRI value creation Error");
		}
		catch(Exception accessException) {
			messengerUtil.addMessage(FacesMessage.SEVERITY_ERROR, "Error: " + accessException.getMessage(), "KRI value creation Error");
		}
		return newKRIValue;
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
			setDisablePreviousButton(true);
			setDisableNextButton(true);
			setDisableSubmitButton(true);
			listStatus = "No KRIs to display";
//			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "KRI Value", "There are no KRis for the user department");
//			RequestContext.getCurrentInstance().showMessageInDialog(facesMessage);
		}else {
			
			for(int i = 0; i < allDeptKRIs.size(); i++) {
				kriWithValues.add(addKRIValue(allDeptKRIs.get(i)));
			}
			
			setCounter(0);
			kri_Value2.setKri(kriWithValues.get(getCounter()).getKri());
			listStatus = (getCounter() + 1) + " of " + kriWithValues.size();
			setDisableSubmitButton(true);
			setDisablePreviousButton(true);
		}
	}
	
	public String getStatusUpdate() {
		return listStatus;
	}
	
	public void nextButtonAction() {
		/**
		 * Read the bean property values, create a kri value instance
		 * add the created kri value instance to List of kri value
		 * get the next kri instance to display on the form or disable the next button
		 */		
			//Create a KRIValue instance with values on the form
			
				try {
					addKRIValueToList();
//					KRI_Value currentKRIValue = kriWithValues.get(getCounter());
//					currentKRIValue.setKri_value(kri_Value2.getKri_value());
//					currentKRIValue.setKri_value_justification(kri_Value2.getKri_value_justification());
//					kriWithValues.set(getCounter(), currentKRIValue);
					setCounter(getCounter() + 1);
					if(getCounter() < kriWithValues.size()) {
						kri_Value2.setKri_value(kriWithValues.get(getCounter()).getKri_value());
						kri_Value2.setKri_value_justification(kriWithValues.get(getCounter()).getKri_value_justification());
						kri_Value2.setKri(kriWithValues.get(getCounter()).getKri());
						listStatus = (getCounter() + 1) + " of " + kriWithValues.size();
					}else {
						setDisableNextButton(true);
					}
				
					if(kriWithValues != null && (!(kriWithValues.isEmpty()))) {
						setDisablePreviousButton(false);
					}
				
					if(kriWithValues.size() - getCounter() == 1) {
						setDisableSubmitButton(false);
						setDisableNextButton(true);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
		
	}//End of method
	
	public void previousButtonAction() {
		/**
		 * Loop through the kriWithValues list to enable user make modification to input values
		 */
		
		if(getCounter() >= kriWithValues.size()) {
			setCounter(getCounter() - 1);
		}
		
		try {
			//Save current value on form
			addKRIValueToList();
//			KRI_Value currentKRIValue = kriWithValues.get(getCounter());
//			currentKRIValue.setKri_value(kri_Value2.getKri_value());
//			currentKRIValue.setKri_value_justification(kri_Value2.getKri_value_justification());
//			kriWithValues.set(getCounter(), currentKRIValue);
			
			//GEt the next item in the list
			setCounter(getCounter() - 1);
			kri_Value2.setKri(kriWithValues.get(getCounter()).getKri());
			kri_Value2.setKri_value(kriWithValues.get(getCounter()).getKri_value());
			kri_Value2.setKri_value_justification(kriWithValues.get(getCounter()).getKri_value_justification());
			setDisableNextButton(false);
			
			if(getCounter() == 0) {
				setDisablePreviousButton(true);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		listStatus = (getCounter() + 1) + " of " + kriWithValues.size();
		
	}
	
	public void addKRIValueToList() {
		KRI_Value currentKRIValue = kriWithValues.get(getCounter());
		currentKRIValue.setKri_value(kri_Value2.getKri_value());
		currentKRIValue.setKri_value_justification(kri_Value2.getKri_value_justification());
		kriWithValues.set(getCounter(), currentKRIValue);
	}
	
	public void submitAction() {
		
		if(getCounter() == 0 || getCounter() == 3) {
			addKRIValueToList();
		}
		
		System.out.println();
		if(!(kriWithValues.isEmpty())) {
			for(KRI_Value a : kriWithValues) {
				System.out.print("KRI: " + a.getKri().getKri_desc());
				System.out.print(" ");
				System.out.print("KRI justification: " + a.getKri_value_justification());
				System.out.print(" ");
				System.out.print("KRI Value: " + a.getKri_value());
				System.out.println();
			}
		}
	}
	
	public void showInjectedValues() {
		System.out.println("Logged in user: " + getUserBean().getUser().getUsername() + " " + getUserBean().getLoggeduser().getEmail() 
				+ " " + getUserBean().getLoggeduser().getDepartment());
	}
}
