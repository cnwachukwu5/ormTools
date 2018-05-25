package com.appslabtest.ormtools.service;

import com.appslabtest.ormtools.model.User;

public interface UserService {
	void addUser(User user);
	User findById(int userId);
	void updateUser(User user) throws Exception;
	void deleteUser(User user) throws Exception;
	User findUser(User user);
	User findUserForUpdating(String username);
}
