package com.xebia.bcp7.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.xebia.bcp7.jdbc.DatabaseConnection;
/**
 * Servlet implementation class SecretKey
 */
@WebServlet("/SecretKey")
public class SecretKey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecretKey() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		try {
			// Initialize the database
			Connection con = DatabaseConnection.initializeDatabase();

			String s = request.getParameter("key");
			if(s.length()!=15) {
				// Get a writer pointer 
				// to display the successful result
				pw.println("<html><body><b>Wrong input"
						+ "</b></body></html>");
				return;
			}
			
			PreparedStatement p = con.prepareStatement("select * from secret where secretKey="+s);
			
			ResultSet rs= p.executeQuery();
			
			if(rs.next()) {

				// Get a writer pointer 
				// to display the successful result
				pw.println("<html><body><b>Welcome"
						+ "</b></body></html>");
			}
			else {

				// Get a writer pointer 
				// to display the successful result
				pw.println("<html><body><b>Wrong input"
						+ "</b></body></html>");
			}
			
			con.close();

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
