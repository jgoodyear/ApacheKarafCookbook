package com.packt;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.http.HttpContext;

/**
 * A http context that always blocks access to resources.
 */
public class WhiteboardContext implements HttpContext {

	public boolean handleSecurity(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {
		// Forbiden access!
		return false;
	}

	public URL getResource(final String name) {
		throw new IllegalStateException(
				"This method should not be possible to be called as the access is denied");
	}

	public String getMimeType(String s) {
		throw new IllegalStateException(
				"This method should not be possible to be called as the access is denied");
	}
}
