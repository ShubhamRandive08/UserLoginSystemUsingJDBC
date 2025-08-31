// In that we can also write the code of the Database connection

package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBConnectivity {
	private final static String url = "jdbc:mysql://localhost:3306/porject1_user_login_system";
	private final static String username = "root";
	private final static String password = "root";
	
	public static Connection dbConnection()  {
		Connection con = null;
		try {
			// Load the class
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Establish the connection
			con = DriverManager.getConnection(url, username, password);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return con;
		
	}
}
