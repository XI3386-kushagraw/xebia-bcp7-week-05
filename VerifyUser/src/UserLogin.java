

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		
		String name=request.getParameter("uname");
		String pass=request.getParameter("pass");
		
		PrintWriter out = response.getWriter();
		
		if(pass.equals("kushagra")) {
			out.write("Welcome here "+name);
		}
		else {
			out.write("Try again later");
		}
	}

}
