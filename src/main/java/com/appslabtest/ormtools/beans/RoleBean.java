package com.appslabtest.ormtools.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.appslabtest.ormtools.model.Role;
import com.appslabtest.ormtools.service.RoleService;

@RequestScope
@Component
public class RoleBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	RoleService roleService;
	
	private Role role = new Role();
	private List<Role> roles;
	private String role_name;
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	@PostConstruct
	public void init() {
		roles = getRoleService().getAllRoles();
	}
	

}
