package com.packt;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloImage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body align='center'>");
		out.println("<h1>Hello Servlet, with Image</h1>");
		out.println("<img src='/whiteboardresources/karaf-logo.png' border='0'/>");
		out.println("</body>");
		out.println("</html>");
	}
}
