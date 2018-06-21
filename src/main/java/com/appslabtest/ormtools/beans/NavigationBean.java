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

}
