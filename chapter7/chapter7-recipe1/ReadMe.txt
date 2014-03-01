

 Installing OpenJPA 2.2.2:

 karaf@root()> feature:install openjpa/2.2.2
 karaf@root()> list -t 0 | grep -i open
 107 | Active   |  29 | 2.2.2                 | OpenJPA Aggregate Jar                                             
 karaf@root()>

 
 Feature contents:

 karaf@root()> feature:info openjpa 2.2.2
 Feature openjpa 2.2.2
 Description:
   Apache OpenJPA 2.2.x persistence engine support
 Details:
   Enable Apache OpenJPA as persistence engine.
 Feature has no configuration
 Feature has no configuration files
 Feature depends on:
   jpa 0.0.0
 Feature contains followed bundles:
   mvn:org.apache.geronimo.specs/geronimo-annotation_1.0_spec/1.1.1
   mvn:org.apache.geronimo.specs/geronimo-jpa_2.0_spec/1.1
   mvn:org.apache.geronimo.specs/geronimo-servlet_2.5_spec/1.2
   mvn:org.apache.geronimo.specs/geronimo-el_1.0_spec/1.0.1
   mvn:org.apache.geronimo.specs/geronimo-jta_1.1_spec/1.1.1
   mvn:commons-lang/commons-lang/2.6
   mvn:commons-collections/commons-collections/3.2.1
   mvn:commons-pool/commons-pool/1.6
   mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.commons-dbcp/1.4_3
   mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.ant/1.7.0_6
   mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.serp/1.14.1_1
   mvn:org.apache.geronimo.specs/geronimo-jms_1.1_spec/1.1.1
   mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.asm/3.3_2
   mvn:org.apache.openjpa/openjpa/2.2.2 start-level=29
 Feature has no conditionals.
 karaf@root()> 
