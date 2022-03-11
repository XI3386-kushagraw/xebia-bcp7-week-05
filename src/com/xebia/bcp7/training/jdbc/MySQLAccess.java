/**
 * 
 */
package com.xebia.bcp7.training.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * 
 * @author Sumith.Puri [Credits to www.vogella.com]
 *
 */

public class MySQLAccess {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public void readDataBase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");

			String url="jdbc:mysql://bcp7-java-db-01.cs4gnvom3jcr.ap-south-1.rds.amazonaws.com:3306/kushagraw.bcp7javaee";

			// Setup the connection with the DB
			connect = DriverManager.getConnection(url,"kushagraw","bcp7javaee");
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from comments");
			writeResultSet(resultSet);

			// PreparedStatements can use variables and are more efficient
			preparedStatement = connect.prepareStatement("insert into  comments values (default, ?, ?, ?, ? , ?,?)");
			// "myuser, webpage, datum, summary, COMMENTS from comments");
			// Parameters start with 1
			preparedStatement.setString(1, "Test");
			preparedStatement.setString(2, "TestEmail");
			preparedStatement.setString(3, "TestWebpage");
			preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
			preparedStatement.setString(5, "TestSummary");
			preparedStatement.setString(6, "TestComment");
			preparedStatement.executeUpdate();

			preparedStatement = connect
					.prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from comments");
			resultSet = preparedStatement.executeQuery();
			writeResultSet(resultSet);

			//			// Remove again the insert comment
			//			preparedStatement = connect.prepareStatement("delete from comments where myuser= ? ; ");
			//			preparedStatement.setString(1, "Test");
			//			preparedStatement.executeUpdate();

			resultSet = statement.executeQuery("select * from comments");
			writeResultSet(resultSet);

			System.out.println("\n [MetaData Details]");
			writeMetaData(resultSet);

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	private void writeMetaData(ResultSet resultSet) throws SQLException {
		// Now get some metadata from the database
		// Result set get the result of the SQL query

		System.out.println("The columns in the table are: ");

		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
			System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
		}
	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {

		System.out.println();
		System.out.println(">>>>>> Writing Result Set <<<<<<");
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String user = resultSet.getString("myuser");
			String website = resultSet.getString("webpage");
			String summary = resultSet.getString("summary");
			Date date = resultSet.getDate("datum");
			String comment = resultSet.getString("comments");
			System.out.println("User: " + user);
			System.out.println("Website: " + website);
			System.out.println("summary: " + summary);
			System.out.println("Date: " + date);
			System.out.println("Comment: " + comment);
		}
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

	public void updateDatabase() throws Exception{
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");

			String url="jdbc:mysql://bcp7-java-db-01.cs4gnvom3jcr.ap-south-1.rds.amazonaws.com:3306/kushagraw.bcp7javaee";

			// Setup the connection with the DB
			connect = DriverManager.getConnection(url,"kushagraw","bcp7javaee");
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			String sql = "UPDATE comments SET webpage = 'wadhwakushagra03' WHERE myuser='Test'";
			statement.executeUpdate(sql);


			System.out.println("After Database Updation");
			ResultSet rs1 = statement.executeQuery("select * from comments where id=8");
			while(rs1.next()) {
				//Display values
				String user = rs1.getString("myuser");
				String website = rs1.getString("webpage");
				String summary = rs1.getString("summary");
				Date date = rs1.getDate("datum");
				String comment = rs1.getString("comments");
				System.out.println("User: " + user);
				System.out.println("Website: " + website);
				System.out.println("summary: " + summary);
				System.out.println("Date: " + date);
				System.out.println("Comment: " + comment);
			}
			rs1.close();

		}
		catch(Exception e) {
			System.out.println("Exception Occured "+e);
		}
		finally {
			close();
		}
	}

	public static void main(String[] args) throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		dao.updateDatabase();
	}
}
