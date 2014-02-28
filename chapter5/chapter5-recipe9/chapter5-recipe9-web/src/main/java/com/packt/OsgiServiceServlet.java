package com.packt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ops4j.pax.cdi.api.OsgiService;

import com.packt.message.MessageService;

@WebServlet(urlPatterns = "/sample")
public class OsgiServiceServlet  extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject @OsgiService
	private MessageService messageService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Famous Quote of the day:</h1>");
		out.println("<h2>"+messageService.getRandomMessage()+"</h2>");
		out.println("</body>");
		out.println("</html>");
    }

}
