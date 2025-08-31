package com.app;

import java.sql.SQLException;
import java.util.Scanner;
import com.controller.UserController;
import com.entity.UserData;

public class Main {
	public static void main(String[] args) throws SQLException {
		UserController userController = new UserController();
		UserData user = new UserData();
		Scanner sc = new Scanner(System.in);
		
		// Code 1
//		System.out.println("Enter the Username : ");
//		String username = sc.next();
//		
//		System.out.println("Enter the Password : ");
//		String password = sc.next();
//		
//		System.out.println("Enter the Email : ");
//		String email = sc.next();
//		
//		user.setPassword(password);
//		user.setEmail(email);
//		user.setUsername(username);
//		
//		System.out.println(userController.insertUser(user.getUsername(), user.getPassword(), user.getEmail()));
//		
		
		
		// Code 2
		userController.getUserByID();
//		System.out.println(userController.updateUser());
		
//		System.out.println(userController.deleteUser(2));
	}
}
