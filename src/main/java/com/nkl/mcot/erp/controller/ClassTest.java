
package com.nkl.mcot.erp.controller; 

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ClassTest {

	public void init() {


	}
	private void testServlet(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("link.php").include(request, response);
		HttpSession session = request.getSession();
		session.invalidate();
		out.print("You are successfully logged out!");
		out.close();

	}
}
