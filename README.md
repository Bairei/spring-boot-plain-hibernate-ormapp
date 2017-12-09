# spring-boot-plain-hibernate-ormapp
A web application created with Spring Boot, with plain Hibernate.

Deploying an app:
 - Having Maven 3 installed and configured is recommended (but not necessary), as well as MySQL server deployed at localhost:3306 address.
 - in terminal prompt execute command: ```mvn spring-boot:run``` (or ```mvn.cmd spring-boot:run``` / ```.\mvn.cmd spring-boot:run``` for Windows, or ```./mvn spring-boot:run ``` for Linux; this way a Maven wrapper will download the 'stand-alone' version and continue deploying application), in main directory of this app (that is in the same directory as src/ and pom.xml file)
 - if database server was configured properly, the application should start listening on port 8080 (localhost:8080).
