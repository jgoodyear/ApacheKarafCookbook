package com.packt.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ServletFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		//nothing to do here ...
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		response.getWriter().println(
				"Filter was there before. Time: " + new Date().toString());
		chain.doFilter(request, response);
		response.getWriter().println(
				"Filter was there after. Time: " + new Date().toString());
		response.getWriter().close();
	}

	public void destroy() {
	}
}
