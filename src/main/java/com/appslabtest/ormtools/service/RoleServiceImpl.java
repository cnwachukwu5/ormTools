package com.appslabtest.ormtools.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appslabtest.ormtools.model.Role;
import com.appslabtest.ormtools.springdatajpa.dao.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Resource
	RoleRepository roleRepository;
	
	@Override
	@Transactional
	public Role findByName(String name) {
		
		Role role= roleRepository.findRoleByName(name);
		return role;
	}
	
	@Override
	@Transactional
	public List<Role> getAllRoles(){
		return roleRepository.findAll();
	}
	
	

}
