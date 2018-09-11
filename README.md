======= HOW TO INSTALL ======= 
This instalation it was in Linux Ubuntu 18.04.

= Requeriments 
* MySQL >= 5.7.23 
* Java 1.8

= Tecnologies used 
* Spring Boot for develop. 
* JPA Repository for persistence. 
* Dozer for mapping entities. 
* JUnit and Mockito for testing.

= Steps 
== Install MySQL 
1.- Type in command line: 
sudo apt install mysql-server

2.- (Only if you have problems with default root user) Create a new user with all privilegies. 
Type in command line:
sudo mysql -u root -p 
Press enter. 
Then in mysql console type:

CREATE USER 'username'@'localhost' IDENTIFIED BY 'password';

Where username is the name of your new user and password the new pass.
We give him all privilegies with:

GRANT ALL PRIVILEGES ON *.* TO 'username'@'localhost' IDENTIFIED BY 'password';

3.- (You can do this step using Mysql Workbench or another Mysql client manager if you prefer) Create new database and table for countries. In Mysql command line type:

CREATE DATABASE countries;
USE countries;
CREATE TABLE 'countries'.'countries' ('id' INT NOT NULL AUTO_INCREMENT, 'name' VARCHAR(45) NULL, 'population' INT NULL, PRIMARY KEY ('id'));

== Install Java 1.8 
1.- Type Linux in command line:
sudo apt install openjdk-8-jdk

== Run the project 
1.- Download sources from here.

2.- Install maven sudo apt install maven

3.- Run the project (from the project folder). Type in command line:
mvn spring-boot:run

4.- Start to invoke Rest API