package com.appslabtest.ormtools.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appslabtest.ormtools.model.User;
import com.appslabtest.ormtools.service.UserService;
import com.appslabtest.ormtools.springdatajpa.dao.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	UserRepository userRepository;
	
	@Override
	@Transactional
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	@Override
	@Transactional
	public User findById(int userid) {
		User user = userRepository.findOne(userid);
		return user;
	}
	
	@Override
	@Transactional
	public void updateUser(User user) throws Exception {
		User updatedUser = findUserForUpdating(user.getUsername());
		
		if(updatedUser == null) {
			throw new Exception("User not found");
		}
		
		updatedUser.setDepartment(user.getDepartment());
		updatedUser.setEmail(user.getEmail());
		updatedUser.setFirstname(user.getFirstname());
		updatedUser.setLastname(user.getLastname());
		updatedUser.setPwd(user.getPwd());
		updatedUser.setRole(user.getRole());
		updatedUser.setActive(true);
		
		userRepository.save(updatedUser);
	}
	
	@Override
	@Transactional
	public void deleteUser(User user) throws Exception {
		User deletedUser = findUser(user);
		
		if (deletedUser == null) {
			throw new Exception("User does not exist");
		}else {
			deletedUser.setActive(false);
			userRepository.save(deletedUser);
		}
	}
	
	@Override
	@Transactional
	public User findUser(User user) {
		User Loggeduser = userRepository.findUser(user.getUsername());
		return Loggeduser;
	}
	
	@Override
	@Transactional
	public User findUserForUpdating(String username) {
		User updateUser = userRepository.findUserForUpdating(username);
		return updateUser;
	}

}
