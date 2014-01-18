package com.packt.chapter1.recipe4.moduleB;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) {
        System.out.println("Apache Karaf starting moduleB bundle");
    }

    public void stop(BundleContext context) {
        System.out.println("Apache Karaf stopping moduleB bundle");
    }

}
