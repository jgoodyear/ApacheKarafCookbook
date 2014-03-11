package com.packt.message.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.packt.message.MessageService;


public class Activator implements BundleActivator {

	private BundleContext bundleContext;
	private ServiceRegistration<MessageService> registerService;

	/**
	 * Called when the OSGi framework starts our bundle
	 */
	public void start(BundleContext bc) throws Exception {
		bundleContext = bc;
		registerService = bundleContext.registerService(MessageService.class, new MessageServiceImpl(), null);
	}

	/**
	 * Called when the OSGi framework stops our bundle
	 */
	public void stop(BundleContext bc) throws Exception {
		registerService.unregister();
	}

}
