package com.xebia.bcp7.jdbc;

import java.sql.*;

public class DatabaseConnection{// Initialize all the information regarding
	protected static Connection initializeDatabase()
			throws SQLException, ClassNotFoundException
	{
		// Initialize all the information regarding
		// Database Connection
		String url = "jdbc:mysql://bcp7-java-db-01.cs4gnvom3jcr.ap-south-1.rds.amazonaws.com:3306/kushagraw.bcp7javaee";

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,
				"kushagraw", 
				"bcp7javaee");
		return con;
	}
	
	public static void main(String args[]) {
		DatabaseConnection dbc = new DatabaseConnection();
		try {
			dbc.initializeDatabase();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
