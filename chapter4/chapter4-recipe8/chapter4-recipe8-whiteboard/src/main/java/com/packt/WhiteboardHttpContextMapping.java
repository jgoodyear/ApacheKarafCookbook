package com.packt;

import java.util.HashMap;
import java.util.Map;

import org.ops4j.pax.web.extender.whiteboard.HttpContextMapping;
import org.osgi.service.http.HttpContext;

public class WhiteboardHttpContextMapping implements HttpContextMapping {

	private final String contextId;
	private final String contextPath;
	private final Map<String, String> params;

	public WhiteboardHttpContextMapping(String httpContextId,
			String contextPath, Map<String, String> params) {
		this.contextId = httpContextId;
		this.contextPath = contextPath;
		this.params = new HashMap<String, String>(params);
	}

	public String getHttpContextId() {
		return contextId;
	}

	public String getPath() {
		return contextPath;
	}

	public Map<String, String> getParameters() {
		return params;
	}

	public HttpContext getHttpContext() {
		return null;
	}

}
