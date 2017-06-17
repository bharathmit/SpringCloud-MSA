# SpringCloud-MSA
Micro Service Architecture with Spring Boot and Spring Cloud

This is a [proof-of-concept application](https://jsoftgroup.wordpress.com/2017/05/09/micro-service-using-spring-cloud-and-netflix-oss/), which demonstrates [Microservice Architecture Pattern](http://martinfowler.com/microservices/) using Spring Boot, Spring Cloud and Docker.
With a pretty neat user interface, by the way.

Build a Docker Image with Maven :
mvn package docker:build

Run The Docker Image :
docker run -p 8080:8080 -t bharathsimbu/config
