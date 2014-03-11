package com.packt;

import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.camel.CamelContext;

import org.apache.commons.lang.StringUtils;

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
   private Map<String, HelloDispatcher> dispatchEngines = Collections.synchronizedMap(new HashMap<String, HelloDispatcher>());
    
   /**
    * Override ManagedServiceFactory interfaces.
    */
 
   @Override
   public String getName() {
       return configurationPid;
   }

   @Override
   public void updated(String pid, Dictionary dict) throws ConfigurationException { 
       log.info("updated " + pid + " with " + dict.toString());
       HelloDispatcher engine = null;

       if (dispatchEngines.containsKey(pid)) {
           engine = dispatchEngines.get(pid);

           if (engine != null) {
               destroyEngine(engine);
           }
           dispatchEngines.remove(pid);
       }
  
       //Verify dictionary contents before applying them to Hello
       if (dict.get(HelloConstants.HELLO_GREETING) != null && 
           !StringUtils.isEmpty(dict.get(HelloConstants.HELLO_GREETING).toString())) {
           log.info("HELLO_GREETING set to " + dict.get(HelloConstants.HELLO_GREETING));
       } else {
           throw new IllegalArgumentException("Missing HELLO_GREETING");
       }

       if (dict.get(HelloConstants.HELLO_NAME) != null &&
           !StringUtils.isEmpty(dict.get(HelloConstants.HELLO_NAME).toString())) {
           log.info("HELLO_NAME set to " + dict.get(HelloConstants.HELLO_NAME));
       } else {
           throw new IllegalArgumentException("Missing HELLO_NAME");
       }

       //Configuration was verified above, now create engine.
       engine = new HelloDispatcher();
       engine.setCamelContext(camelContext);
       engine.setGreeting(dict.get(HelloConstants.HELLO_GREETING).toString());
       engine.setName(dict.get(HelloConstants.HELLO_NAME).toString());
       
       dispatchEngines.put(pid, engine);
       log.debug("Start the engine...");
       if (engine == null) {
           log.debug("Engine was null, check configuration.");
       }
       engine.start();
   }
 
   @Override
   public void deleted(String pid) {
       if (dispatchEngines.containsKey(pid)) {
           HelloDispatcher engine = dispatchEngines.get(pid);

           if (engine != null) {
               destroyEngine(engine);
           }
           dispatchEngines.remove(pid);
       }
       log.info("deleted " + pid);
   }

   /**
    * Adding our own methods.
    */

   private void destroyEngine(HelloDispatcher engine) {
       engine.stop();
   }

   // We wire the init method in the blueprint file
   public void init() {
       log.info("Starting " + this.getName());
       Dictionary servProps = new Properties();
       servProps.put(Constants.SERVICE_PID, configurationPid);
       registration = bundleContext.registerService(ManagedServiceFactory.class.getName(), this, servProps);
       tracker = new ServiceTracker(bundleContext, ConfigurationAdmin.class.getName(), null);
       tracker.open();
       log.info("Started " + this.getName());
   }

   // We wire the destroy method in the blueprint file
   public void destroy() {
       log.info("Destroying hello factory " + configurationPid);
       registration.unregister();
       tracker.close();
   }

   // Wired in blueprint.
   public void setConfigurationPid(String configurationPid) {
       this.configurationPid = configurationPid;
   }

   // Wired in blueprint.
   public void setBundleContext(BundleContext bundleContext) {
       this.bundleContext = bundleContext;
   }

   // Wired in blueprint.
   public void setCamelContext(CamelContext camelContext) {
       this.camelContext = camelContext;
   }

}
