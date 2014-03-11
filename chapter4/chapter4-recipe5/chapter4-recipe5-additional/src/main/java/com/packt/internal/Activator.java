package com.packt.internal;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;

import org.ops4j.pax.web.extender.whiteboard.ExtenderConstants;
import org.ops4j.pax.web.service.WebContainer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpContext;

import com.packt.filter.ServletFilter;

public class Activator implements BundleActivator {

	private BundleContext bundleContext;

	public void start(BundleContext context) throws Exception {
		
		//simplistic way of achieving the WebContainer as service
		ServiceReference<WebContainer> serviceReference = context.getServiceReference(WebContainer.class);
        
        while (serviceReference == null) {
        	serviceReference = context.getServiceReference(WebContainer.class);
        }
        
        WebContainer service = (WebContainer) context.getService(serviceReference);
		
		
		Collection<ServiceReference<HttpContext>> serviceReferences = context.getServiceReferences(HttpContext.class, "(httpContext.id=shared)");
		
		if (serviceReferences.size() > 1) {
			throw new RuntimeException("should only be one http shared context");
		}
		
		HttpContext httpContext = context.getService(serviceReferences.iterator().next());

		Dictionary<String, String> props;
		
		props = new Hashtable<String, String>();
		props.put("pattern", ".*");
		props.put(ExtenderConstants.PROPERTY_HTTP_CONTEXT_ID, "shared");
		
		service.registerFilter(new ServletFilter(), new String[] { "/*" }, null, props, httpContext);
        
	}

	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
