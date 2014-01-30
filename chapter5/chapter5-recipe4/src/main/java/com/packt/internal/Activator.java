package com.packt.internal;

import java.util.Hashtable;

import javax.servlet.Servlet;

import org.ops4j.pax.web.extender.whiteboard.ExtenderConstants;
import org.ops4j.pax.web.extender.whiteboard.ResourceMapping;
import org.ops4j.pax.web.extender.whiteboard.runtime.DefaultResourceMapping;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.http.HttpContext;

import com.packt.HelloImage;
import com.packt.HelloServlet;
import com.packt.WhiteboardContext;

public class Activator implements BundleActivator {

	private BundleContext bundleContext;

	private ServiceRegistration<Servlet> servletReg;

	private ServiceRegistration<ResourceMapping> resourcesReg;

	private ServiceRegistration<Servlet> logoServletReg;

	private ServiceRegistration<HttpContext> httpContextReg;

	private ServiceRegistration<Servlet> forbiddenServletReg;

	/**
	 * Called when the OSGi framework starts our bundle
	 */
	public void start(BundleContext bc) throws Exception {
		bundleContext = bc;
		
		//Registering Servlet
		Hashtable<String, String> props = new Hashtable<String, String>();
		props.put(ExtenderConstants.PROPERTY_ALIAS, "/whiteboard");
		servletReg = bundleContext.registerService(Servlet.class, new HelloServlet(), props);
		
		//Registering resource
		DefaultResourceMapping resourceMapping = new DefaultResourceMapping();
		resourceMapping.setAlias("/whiteboardresources");
		resourceMapping.setPath("/images");
		resourcesReg = bundleContext.registerService(ResourceMapping.class,
				resourceMapping, null);
		
		//Servlet using the resource
		props = new Hashtable<String, String>();
		props.put("alias", "/whiteboard/logo");
		logoServletReg = bundleContext.registerService(Servlet.class, new HelloImage(), props);
		
		// register a custom http context that forbids access
		props = new Hashtable<String, String>();
		props.put(ExtenderConstants.PROPERTY_HTTP_CONTEXT_ID, "forbidden");
		httpContextReg = bundleContext.registerService(HttpContext.class,
				new WhiteboardContext(), props);
		// and an servlet that cannot be accessed due to the above context
		props = new Hashtable<String, String>();
		props.put(ExtenderConstants.PROPERTY_ALIAS, "/forbidden");
		props.put(ExtenderConstants.PROPERTY_HTTP_CONTEXT_ID, "forbidden");
		forbiddenServletReg = bundleContext.registerService(Servlet.class,
				new HelloServlet(), props);
	}

	/**
	 * Called when the OSGi framework stops our bundle
	 */
	public void stop(BundleContext bc) throws Exception {
		servletReg.unregister();
		resourcesReg.unregister();
		logoServletReg.unregister();
	}

}
