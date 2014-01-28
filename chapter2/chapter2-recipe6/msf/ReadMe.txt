Camel Router Project for Blueprint (OSGi)
=========================================

To build this project use

    mvn install

This sample requires Commons-Lang to be installed to the OSGi 
Container. Use the following to install the dependency:

   karaf@root()> install mvn:commons-lang/commons-lang/2.6
   Bundle ID: 119
   karaf@root()> start 119
   karaf@root()>

To deploy the project in OSGi. For example using Apache ServiceMix
or Apache Karaf. You can run the following command from its shell:

    osgi:install -s mvn:com.packt/msf/1.0.0-SNAPSHOT

Configuration of the MSF should reside in KARAF_BASE/etc. Create a pair
of files com.packt.hellofactory-test1.cfg and com.packt.hellofactory-test2.cfg.
Each file should contain content as below:

HELLO_GREETING=hello
HELLO_NAME=jamie

A route will be instantiated for each configuration provided.

Executing camel:route-list will display each configured instance 
of the Hello route. 

 karaf@root()> camel:route-list 
  Context        Route          Status   
  -------        -----          ------   
  helloContext   Hello jamie    Started  
  helloContext   Hello laura    Started  
 karaf@root()> 

For more help see the Apache Camel documentation

    http://camel.apache.org/

