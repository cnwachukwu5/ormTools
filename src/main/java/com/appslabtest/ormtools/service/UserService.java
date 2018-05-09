package com.appslabtest.ormtools.service;

import com.appslabtest.ormtools.model.User;

public interface UserService {
	void addUser(User user);
	User findById(int userId);
	User updateUser(User user) throws Exception;
	void deleteUser(User user) throws Exception;
	User findUser(User user);
}
