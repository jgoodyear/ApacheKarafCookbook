package com.packt.internal;

import java.util.Dictionary;
import java.util.Hashtable;

import javax.servlet.ServletException;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import com.packt.HelloImage;
import com.packt.HelloServlet;

public class Activator implements BundleActivator, ServiceTrackerCustomizer<HttpService, HttpService> {

	private BundleContext bundleContext;

	private ServiceTracker<HttpService, HttpService> tracker;

	/**
	 * Called when the OSGi framework starts our bundle
	 */
	public void start(BundleContext bc) throws Exception {
		bundleContext = bc;
		tracker = 
				new ServiceTracker<HttpService, HttpService>(bc, HttpService.class, this);
		tracker.open();
	}

	/**
	 * Called when the OSGi framework stops our bundle
	 */
	public void stop(BundleContext bc) throws Exception {
		tracker.close();
	}

	/* (non-Javadoc)
	 * 
	 * called by the service tracker, at the time where the Http Service is available
	 * 
	 * @see org.osgi.util.tracker.ServiceTrackerCustomizer#addingService(org.osgi.framework.ServiceReference)
	 */
	public HttpService addingService(ServiceReference<HttpService> reference) {
		final HttpService httpService = (HttpService) bundleContext
				.getService(reference);
		if (httpService != null) {
			// create a default context to share between registrations
			final HttpContext httpContext = httpService.createDefaultHttpContext();
			
			final Dictionary<String, Object> initParams = new Hashtable<String, Object>();
			try {
				httpService.registerServlet("/hello", new HelloServlet(), initParams, httpContext);
				httpService.registerServlet("/hello/logo", new HelloImage(), initParams, httpContext);
				httpService.registerResources("/images", "/images", httpContext);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (NamespaceException e) {
				e.printStackTrace();
			}
		}
		return httpService;
	}

	public void modifiedService(ServiceReference<HttpService> reference,
			HttpService service) {
		// ignore
	}

	/* (non-Javadoc)
	 *
	 * called in the moment, where the Http Service is removed
	 * 
	 * @see org.osgi.util.tracker.ServiceTrackerCustomizer#removedService(org.osgi.framework.ServiceReference, java.lang.Object)
	 */
	public void removedService(ServiceReference<HttpService> reference,
			HttpService service) {
		try {
			service.unregister("/hello");
		} catch (Exception e) {
			//ignore
		}
	}

}
