
 Making a custom Apache Karaf command.
 =====================================

 WARNING: Apache Karaf 3.0.0 has a custom command bug out
 of the box. This should be resolved for 3.0.1 release.

 The below Maven Archetype will generate a project structure
 for the development of an Apache Karaf command. You should
 select the archetypeVersion to match the Karaf version.

 mvn archetype:generate \
  -DarchetypeGroupId=org.apache.karaf.archetypes \
  -DarchetypeArtifactId=karaf-command-archetype \
  -DarchetypeVersion=3.0.0 \
  -DgroupId=com.mycompany \
  -DartifactId=com.mycompany.command \
  -Dversion=1.0.0-SNAPSHOT \
  -Dpackage=com.mycompany.package

 When the archetype is executed you will be presented
 with several choices as below:

Define value for property 'command': : sample
Define value for property 'description': : a sample custom command
Define value for property 'scope': : cookbook

 Above we provided a command name "sample", a description of
 our sample command, and a command family scope of "cookbook".

 Sample Invocation:
 ------------------

 mvn archetype:generate \
  -DarchetypeGroupId=org.apache.karaf.archetypes \
  -DarchetypeArtifactId=karaf-command-archetype \
  -DarchetypeVersion=3.0.0 \
  -DgroupId=com.packt.chapter1 \
  -DartifactId=command \
  -Dversion=1.0.0-SNAPSHOT \ 
  -Dpackage=com.packt
