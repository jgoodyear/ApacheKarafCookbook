package com.packt;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.apache.camel.impl.DefaultCamelContext;

public class Activator implements BundleActivator {

    DefaultCamelContext camelContext;

    public void start(BundleContext context) {
        System.out.println("Starting the bundle");
        camelContext = new DefaultCamelContext();
        try {
           camelContext.addRoutes(new MyRouteBuilder());
           camelContext.start();
        } catch (Exception ex) {
           System.out.println("Exception occured! " + ex.getMessage());
        }
    }

    public void stop(BundleContext context) {
        System.out.println("Stopping the bundle");
        if (camelContext != null) {
           try { 
              camelContext.stop();
           } catch (Exception ex) {
              System.out.println("Exception occured during stop context.");
           }
        }
    }

}
