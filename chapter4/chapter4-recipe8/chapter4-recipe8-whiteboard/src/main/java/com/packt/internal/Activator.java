package com.packt.internal;

import java.util.HashMap;
import java.util.Hashtable;

import javax.servlet.Servlet;

import org.ops4j.pax.web.extender.whiteboard.ExtenderConstants;
import org.ops4j.pax.web.extender.whiteboard.HttpContextMapping;
import org.ops4j.pax.web.extender.whiteboard.runtime.DefaultHttpContextMapping;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.packt.HelloServlet;
import com.packt.WhiteboardHttpContextMapping;

public class Activator implements BundleActivator {

	private BundleContext bundleContext;

	private ServiceRegistration<Servlet> servletReg;

	private ServiceRegistration<HttpContextMapping> contextMappingReg;

	/**
	 * Called when the OSGi framework starts our bundle
	 */
	public void start(BundleContext bc) throws Exception {
		bundleContext = bc;
		
		//preparing special HTTP Context with HTTP Connector
		Hashtable<String, String> props = new Hashtable<String, String>();
		props.put( ExtenderConstants.PROPERTY_HTTP_CONTEXT_ID, "httpConnector" );
		HashMap<String,String> contextMappingParams = new HashMap<String,String>();
		contextMappingParams.put(ExtenderConstants.PROPERTY_HTTP_VIRTUAL_HOSTS, "localhost");
		contextMappingParams.put(ExtenderConstants.PROPERTY_HTTP_CONNECTORS, "alternateConnector");
		contextMappingReg = bundleContext.registerService( HttpContextMapping.class, new WhiteboardHttpContextMapping("httpConnector", "whiteboard", contextMappingParams), props );
		
		//Registering Servlet
		props.put(ExtenderConstants.PROPERTY_ALIAS, "/connector");
		servletReg = bundleContext.registerService(Servlet.class, new HelloServlet(), props);
		
	}

	/**
	 * Called when the OSGi framework stops our bundle
	 */
	public void stop(BundleContext bc) throws Exception {
		servletReg.unregister();
		contextMappingReg.unregister();
	}

}
