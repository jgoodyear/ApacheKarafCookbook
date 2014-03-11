package com.packt.internal;

import java.util.Dictionary;
import java.util.Hashtable;

import javax.servlet.Servlet;

import org.ops4j.pax.web.extender.whiteboard.ExtenderConstants;
import org.ops4j.pax.web.service.WebContainer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.http.HttpContext;

import com.packt.HelloServlet;

public class Activator implements BundleActivator {

	private ServiceRegistration<HttpContext> httpContextReg;
	private ServiceRegistration<Servlet> registerService;

	public void start(BundleContext context) throws Exception {
		ServiceReference<WebContainer> serviceReference = context
				.getServiceReference(WebContainer.class);

		while (serviceReference == null) {
			serviceReference = context.getServiceReference(WebContainer.class);
		}

		WebContainer service = (WebContainer) context
				.getService(serviceReference);

		HttpContext httpContext = service.getDefaultSharedHttpContext();

		Dictionary<String, String> props;

		// register a custom http context for sharing
		props = new Hashtable<String, String>();
		props.put(ExtenderConstants.PROPERTY_HTTP_CONTEXT_ID, "shared");
		httpContextReg = context.registerService(HttpContext.class,
				httpContext, props);
		
		// and an servlet that cannot be accessed due to the above context
		props = new Hashtable<String, String>();
		props.put( ExtenderConstants.PROPERTY_ALIAS, "/extfilter" );
		props.put("servlet-name", "FilteredServlet");
		props.put(ExtenderConstants.PROPERTY_HTTP_CONTEXT_ID, "shared");
		registerService = context.registerService( Servlet.class, new HelloServlet(), props );
	}

	public void stop(BundleContext context) throws Exception {
		registerService.unregister();
		httpContextReg.unregister();
	}

}
