// Inside this class we can also write the business logic and rules of application

package com.service;

import java.sql.SQLException;

import com.dao.UserDao;

public class UserService {
	UserDao userDao = new UserDao();
	
	@SuppressWarnings("unused")
	public String  insertUser(String username, String password, String email) throws SQLException {
		System.out.println(userDao.createTable());
		String msg = null;
		if(username.length() < 3 || username.length() > 10) {
			msg = "Username should be less than 10 as well as gratter than 3";
		}else {
			if(msg == null) {
				msg = (userDao.insertUser(username, password, email));
			}else {
				msg = "Failed to insert user";
			}
		}
		
		return msg;
	}
	
	public void getUserByID() {
		userDao.getUserByID();
	}
	
	public String updateUser() {
		return userDao.updateUser();
	}
}
