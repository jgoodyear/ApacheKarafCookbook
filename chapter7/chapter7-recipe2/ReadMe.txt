

 Installing transaction support:

 karaf@root()> feature:install transaction 
 karaf@root()>


 Feature contents:

 karaf@root()> feature:info transaction 
 Feature transaction 1.0.1
 Description:
   OSGi Transaction Manager
 Details:
   JTA implementation provided by Apache Aries Transaction 1.0.1
 Feature configuration:
   org.apache.aries.transaction
 Feature has no configuration files
 Feature has no dependencies.
 Feature contains followed bundles:
   mvn:org.apache.geronimo.specs/geronimo-jta_1.1_spec/1.1.1 start-level=30
   mvn:org.apache.aries.transaction/org.apache.aries.transaction.blueprint/1.0.1 start-level=30
   mvn:org.apache.aries.transaction/org.apache.aries.transaction.manager/1.1.0 start-level=30
 Feature has no conditionals.
 karaf@root()> 
