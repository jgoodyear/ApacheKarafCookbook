package com.packt;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(service = Servlet.class,
        scope= ServiceScope.PROTOTYPE,
        property= "osgi.http.whiteboard.servlet.pattern=/hello222")
public class HelloWorldServlet extends javax.servlet.http.HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().println("Hello World");
    }
}