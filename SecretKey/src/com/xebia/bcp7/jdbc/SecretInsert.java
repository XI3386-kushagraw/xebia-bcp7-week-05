package com.xebia.bcp7.jdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class SecretInsert
 */
@WebServlet("/SecretInsert")
public class SecretInsert extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		try {
			// Initialize the database
			
			Connection con = DatabaseConnection.initializeDatabase();

			// Create a SQL query to insert data into demo table
			// demo table consists of two columns, so two '?' is used
			PreparedStatement st = con
					.prepareStatement("insert into secret values(?)");

			// For the first parameter,
			// get the data using request object
			// sets the data to st pointer
			String s = request.getParameter("keyValue");
			if(s.length()!=15) {
				pw.println("<html><body><b>Error!"
						+ "</b></body></html>");
				return;
			}
			st.setString(1, s);


			// Execute the insert command using executeUpdate()
			// to make changes in database
			st.executeUpdate();

			// Close all the connections
			st.close();
			con.close();

			pw.println("<html><body><b>Successfully Inserted"
					+ "</b></body></html>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
