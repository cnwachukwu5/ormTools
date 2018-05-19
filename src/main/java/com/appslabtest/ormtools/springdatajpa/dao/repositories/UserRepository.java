package com.appslabtest.ormtools.springdatajpa.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appslabtest.ormtools.model.*;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("SELECT max(u.userid) FROM User u")
	int getMaxUserId();
	
	
	@Query(value = "SELECT * FROM User WHERE username = ?1 AND isActive > 0", nativeQuery = true)
	public User findUser(String username);

}
