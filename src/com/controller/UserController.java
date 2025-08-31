// Inside this class we can also handle the user requests

package com.controller;

import java.sql.SQLException;

import com.service.UserService;

public class UserController {
	UserService userService = new UserService();
	
	public String insertUser(String username, String password, String email) throws SQLException {
		return userService.insertUser(username, password, email);
	}
	
	public void getUserByID() {
		userService.getUserByID();
	}
	
	public String updateUser() {
		return userService.updateUser();
	}
	
	public String deleteUser(int id) {
		return userService.deleteUser(id);
	}
}
