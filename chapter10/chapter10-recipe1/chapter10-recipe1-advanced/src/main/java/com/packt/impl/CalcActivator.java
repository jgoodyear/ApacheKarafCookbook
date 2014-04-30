package com.packt.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.packt.Calculator;

public class CalcActivator implements BundleActivator {
	
	private BundleContext bundleContext;
	private ServiceRegistration<Calculator> registeredService;

	public void start(BundleContext context) throws Exception {
		bundleContext = context;
		registeredService = bundleContext.registerService(Calculator.class, new CalculatorImpl(), null);
	}

	public void stop(BundleContext context) throws Exception {
		registeredService.unregister();
	}

}
