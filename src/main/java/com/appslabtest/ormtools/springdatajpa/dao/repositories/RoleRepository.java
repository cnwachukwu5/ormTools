package com.appslabtest.ormtools.springdatajpa.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appslabtest.ormtools.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	@Query(value="SELECT * FROM role where rolename LIKE %?1", nativeQuery = true)
	public Role findRoleByName(String name);
}
