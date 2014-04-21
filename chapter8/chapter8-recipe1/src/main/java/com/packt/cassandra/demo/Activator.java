package com.packt.cassandra.demo;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        System.out.println("Cassandra Demo Bundle starting...");
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Cassandra Demo Bundle stopping...");
    }
}
