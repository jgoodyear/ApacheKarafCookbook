package com.packt;

import java.util.Dictionary;
import java.util.Properties;

import org.apache.camel.CamelContext;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedServiceFactory;
import org.osgi.util.tracker.ServiceTracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloFactory implements ManagedServiceFactory {

   private BundleContext bundleContext;
   private CamelContext camelContext;
   private ServiceRegistration registration;
   private ServiceTracker tracker;
   private Logger log = LoggerFactory.getLogger(HelloFactory.class);
   private String configurationPid;

   /**
    * Override ManagedServiceFactory interfaces.
    */
 
   @Override
   public String getName() {
       System.out.println("Getting Factory Name... " + configurationPid);
       return configurationPid;
   }

   @Override
   public void updated(String id, Dictionary dict) {
       System.out.println("updated " + id + " with " + dict.toString());
   }
 
   @Override
   public void deleted(String id) {
       System.out.println("deleted " + id);
   }

   /**
    * Adding our own methods.
    */

   public void init() {
       log.info("Starting " + this.getName());
       Dictionary servProps = new Properties();
       servProps.put(Constants.SERVICE_PID, configurationPid);
       registration = bundleContext.registerService(ManagedServiceFactory.class.getName(), this, servProps);
       tracker = new ServiceTracker(bundleContext, ConfigurationAdmin.class.getName(), null);
       tracker.open();
       log.info("Started " + this.getName());
   }

   public void destroy() {
       System.out.println("Destroying hello factory " + configurationPid);
       registration.unregister();
       tracker.close();
   }

   public void setConfigurationPid(String configurationPid) {
       this.configurationPid = configurationPid;
   }

   public void setBundleContext(BundleContext bundleContext) {
       this.bundleContext = bundleContext;
   }

   public void setCamelContext(CamelContext camelContext) {
       this.camelContext = camelContext;
   }

}
