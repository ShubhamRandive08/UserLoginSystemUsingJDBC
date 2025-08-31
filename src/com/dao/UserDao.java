// In that class we can also write the all database code

//👉 Suppose you want to register a user
//
//Controller: Gets request from user (say /register).
//
//Service: Checks business rules (e.g., user already exists or not).
//
//DAO: Saves user details into the database.
//
//Response goes back → Service → Controller → User.

package com.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import com.connection.DBConnectivity;
import com.entity.UserData;

public class UserDao {
	DBConnectivity dbConnection = new DBConnectivity();
	Connection con = DBConnectivity.dbConnection();
	Scanner sc = new Scanner(System.in);

	public String createTable() throws SQLException {
//		PreparedStatement pt = con.prepareStatement(
//				"create table if not exists userdatajdbc (id int NOT NULL AUTO_INCREMENT, username varchar(50), password varchar(50), email varchar(50), PRIMARY KEY(id))");
//		
//		
//
//		pt.executeUpdate();
//		
//		return "Table created Successfully";

		String msg = null;
		DatabaseMetaData dbm = con.getMetaData();
		ResultSet tables = dbm.getTables(null, null, "userdatajdbc", null);

		if (!tables.next()) { // Table does not exist
			String sql = "CREATE TABLE userdatajdbc (" + "id INT NOT NULL AUTO_INCREMENT,"
					+ "username VARCHAR(50) NOT NULL," + "password VARCHAR(50) NOT NULL," + "email VARCHAR(100),"
					+ "PRIMARY KEY (id))";
			PreparedStatement pst = con.prepareStatement(sql);

			msg = "Table Created Successfully";
			pst.executeUpdate();
		} else {
			msg = "";
		}

		return msg;
	}

	public String insertUser(String username, String password, String email) {
		try {
			PreparedStatement pt = con
					.prepareStatement("insert into userdatajdbc(username, password, email) values (?,?,?)");
			pt.setString(1, username);
			pt.setString(2, password);
			pt.setString(3, email);

			pt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

		}

		return "User Insert Successfully";
	}

	public ArrayList<UserData> getAllUser() {
		ArrayList<UserData> userdata = new ArrayList<UserData>();
		try {
			PreparedStatement pt = con.prepareStatement("select * from userdatajdbc");
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				UserData user = new UserData(id, username, password, email);
				userdata.add(user);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

		}

		return userdata;
	}
	
	public boolean getUserByIDMainCode(int id) {
		boolean recordFound = false;
		try {
			PreparedStatement getUserById = con.prepareStatement("select * from userdatajdbc where id = ?");
			getUserById.setInt(1, id);

			ResultSet rs = getUserById.executeQuery();
			
			
			
				while (rs.next()) {
					recordFound = true;
					int uid = rs.getInt("id");
					String username = rs.getString("username");
					String password = rs.getString("password");
					String email = rs.getString("email");
					System.out.println("Student Information : \nUser ID : " + uid + "\nUsername : " + username
							+ "\nPassword : " + password + "\nEmail : " + email);
				}
				
				if(!recordFound) {
					System.out.println("Record of this ID is not exists.");
				}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return recordFound;
	}

	public void getUserByID() {
		try {
			System.out.println("Enter the User ID, who do you want to fetch?");
			int id = sc.nextInt();

			getUserByIDMainCode(id);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

		}

	}

	@SuppressWarnings("unused")
	public String updateUser() {
		String msg = null;
		try {
			System.out.println("Enter the User ID, who do you want to update?");
			int id = sc.nextInt();
			
			if(getUserByIDMainCode(id)) {
				System.out.println("Enter the new username of the user : ");
				String username = sc.next();
				
				System.out.println("Enter the new password of the user : ");
				String password = sc.next();
				
				System.out.println("Enter the new email of the user : ");
				String email = sc.next();
				
				PreparedStatement pt = con.prepareStatement("update userdatajdbc set username = ?, password = ?, email = ? where id = ?");
				pt.setString(1, username);
				pt.setString(2, password);
				pt.setString(3, email);
				pt.setInt(4, id);
				
				pt.executeUpdate();
				msg = "User update success";
			}else {
				 System.out.println("Update cancelled. User with ID " + id + " not found.");
			}
			
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

		}
		
		if(msg == null) {
			msg = "ID not found";
		}else {
			msg = "User update success";
		}
		
		return msg;
	}

	public String deleteUser(int id) {
		try {
			PreparedStatement pt = con.prepareStatement("delete from userdatajdbc where id = ?");
			pt.setInt(1, id);
			
			pt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

		}
		
		return "Delete Success ";
	}

}
