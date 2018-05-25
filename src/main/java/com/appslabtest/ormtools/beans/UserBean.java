package com.appslabtest.ormtools.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import org.primefaces.context.RequestContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.dao.DataAccessException;

import com.appslabtest.ormtools.model.User;
import com.appslabtest.ormtools.model.Department;
import com.appslabtest.ormtools.model.Role;
import com.appslabtest.ormtools.service.UserService;



@SessionScope
@Component
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	UserService userService;
	
	private String firstname;
	private String lastname;
	private String username;
	private String pwd;
	private String email;
	private Department department;
	private Role role;
	private User user = new User();
	private User loggeduser;
	
	public UserBean() {}
	
	public UserService getUserService() {
		return userService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Department getDepartment() {
		return department;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public User getLoggeduser() {
		return loggeduser;
	}

	public void setLoggeduser(User loggeduser) {
		this.loggeduser = loggeduser;
	}

	public String validateUser() {
		loggeduser = getUserService().findUser(user);
		//System.out.println("User role is: " + loggeduser.getRole().getRole());
		if(loggeduser != null) {
			if(loggeduser.getPwd().equals(user.getPwd())) {
				return "/main";
			}else {
				//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid user credentials!", "Login"));
				addMessage(FacesMessage.SEVERITY_ERROR, "Invalid user credentials!","Login");
				return "";
			}
		}else {
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User does not exist!", "Login"));
			addMessage(FacesMessage.SEVERITY_ERROR, "User does not exist!","Login");
			return "";
		}
	}
	
	public void checkUser() {
		User userExists = getUserService().findUser(user);
		if(userExists == null) {
			addMessage(FacesMessage.SEVERITY_ERROR, "User does not exist!","Login");
			
		}else {
			addMessage(FacesMessage.SEVERITY_INFO, "User " + userExists.getFirstname() + " does exist!","Login");
			user.setFirstname(userExists.getFirstname());
			user.setLastname(userExists.getLastname());
			user.setEmail(userExists.getEmail());
			user.setRole(userExists.getRole());
			user.setDepartment(userExists.getDepartment());
		}
	}
	
	public void addUser() {
		
		try {	
			 	User new_user = new User();
			
			 	new_user.setFirstname(user.getFirstname());
			 	new_user.setLastname(user.getLastname());
			 	new_user.setUsername(user.getUsername());
			 	new_user.setPwd(user.getPwd());
			 	new_user.setEmail(user.getEmail());
			 	new_user.setDepartment(user.getDepartment());
			 	new_user.setRole(user.getRole());
			 	new_user.setActive(true);
			
			 	getUserService().addUser(new_user);
			 	addMessage(FacesMessage.SEVERITY_INFO, "User successfully created!","User Creation");
			 	reset();
		}catch(DataAccessException e) {
				addMessage(FacesMessage.SEVERITY_ERROR, "User creation error!","User Creation");
				e.printStackTrace();
		 }
		}//End of method
	
	public void changeUser(User user) {
		this.user = user;
	}
	
	public void updateUser() throws Exception {
		
		getUserService().updateUser(user);
		addMessage(FacesMessage.SEVERITY_INFO, "User Information updated successfully .","Update");
        this.reset();
	}
	
	public void deleteUser() throws Exception {
		getUserService().deleteUser(user);
		addMessage(FacesMessage.SEVERITY_INFO, "User Information deleted successfully .","Delete");
	}
	
	public void reset() {
        
        this.user.reset();
    }
	
	public String logout() {
		return "index";
	}
	
	public String showUserManager() {
		return "usermanager";
	}
	
	public void addMessage(Severity msg, String summary, String details) {
		FacesMessage message = new FacesMessage(msg,summary, details);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
