package com.appslabtest.ormtools.service;

import java.util.List;

import com.appslabtest.ormtools.model.Role;

public interface RoleService {
	public Role findByName(String name);
	public List<Role> getAllRoles();
}
