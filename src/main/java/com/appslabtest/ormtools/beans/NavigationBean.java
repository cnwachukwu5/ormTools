package com.appslabtest.ormtools.beans;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@Component
@SessionScope
public class NavigationBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	KriValueBean kriValueBean;
	
	public KriValueBean getKriValueBean() {
		return kriValueBean;
	}

	public void setKriValueBean(KriValueBean kriValueBean) {
		this.kriValueBean = kriValueBean;
	}

	public String gotoCreateUserPage() {
		return "/user/createuser.xhtml?faces-redirect=true";
	}
	
	public String gotoUpdateUserPage() {
		return "/user/updateuser.xhtml?faces-redirect=true";
	}
	
	public String gotoDeleteUserPage() {
		return "/user/deleteuser.xhtml?faces-redirect=true";
	}
	
	public String gotoaddKRISinglePage() {
		return "/kri/addsinglekri.xhtml?faces-redirect=true";
	}
	public String gotoaddKRIBatchPage() {
		return "/kri/addkribatch.xhtml?faces-redirect=true";
	}
	
	public String gotoupdateKRIPage() {
		return "/kri/updatekri.xhtml?faces-redirect=true";
	}
	
	public String gotodeleteKRIPage() {
		return "/kri/deletekri.xhtml?faces-redirect=true";
	}
	
	public String gotosumitKRIValuePage() {
		
		getKriValueBean().getDepartmentKRIs();
		return "/kri/submitkrivalues.xhtml?faces-redirect=true";
	}

}
