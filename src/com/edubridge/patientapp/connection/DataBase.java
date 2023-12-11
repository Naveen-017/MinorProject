package com.edubridge.patientapp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
	public static Connection getConnection() {
		Connection con = null;

		String driver, url, user, password;
		driver = "com.mysql.cj.jdbc.Driver";
		url = "jdbc:mysql://localhost:3306/edb10614";
		user = "root";
		password = "root";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, user,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;

	

	}
	

}
