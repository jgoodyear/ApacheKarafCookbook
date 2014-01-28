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

For more help see the Apache Camel documentation

    http://camel.apache.org/

