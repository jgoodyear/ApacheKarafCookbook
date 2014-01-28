package com.packt;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloDispatcher {

    private String greeting;
    private String name;
    private RouteBuilder rb;
    private CamelContext cc;

    private Logger log = LoggerFactory.getLogger(HelloDispatcher.class);

    HelloDispatcher() { }

    public void start() {
        try {
            rb = buildHelloRouter();
            log.info("Route " + rb + " starting..."); 
            cc.start();
            cc.addRoutes(rb);
        } catch (Exception ex) {
            log.error("Could not process Hello " + ex); 
        }
    }

    protected RouteBuilder buildHelloRouter() throws Exception {
    
        return new RouteBuilder() {

            @Override
            public void configure() throws Exception {

                from("timer://helloTimer?fixedRate=true&period=10000").
                    routeId("Hello " + name).
                    log(greeting + " " + name);            
            }
        };

    }

    public void stop() {
        if (rb != null) { 
            try {
                cc.removeRoute(rb.toString());
            } catch (Exception e) {
                log.error("Could not remove route " + rb + " " + e);
            }
        }
    }

    public void setCamelContext(CamelContext cc) {
        this.cc = cc;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public void setName(String name) {
        this.name = name;
    }
}
