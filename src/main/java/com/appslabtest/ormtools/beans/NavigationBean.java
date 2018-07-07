package com.appslabtest.ormtools.beans;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@Component
@SessionScope
public class NavigationBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
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

}
