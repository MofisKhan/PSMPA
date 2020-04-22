package com.aklc.psmpa.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLUtility {

	public static Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/PSMPADB", "root", "root");
		}catch (Exception e) {
			throw e;
		} 
	}
}
