# SpringCloud-MSA
Micro Service Architecture with Spring Boot and Spring Cloud

This is a [proof-of-concept application](https://jsoftgroup.wordpress.com/2017/05/09/micro-service-using-spring-cloud-and-netflix-oss/), which demonstrates [Microservice Architecture Pattern](http://martinfowler.com/microservices/) using Spring Boot, Spring Cloud and Docker.
With a pretty neat user interface, by the way.

Build a Docker Image with Maven : # It will push automatically into local docker image
mvn install dockerfile:build

Run The Docker Image :
#1 running docker image for registry
docker run -d -p 8761:8761 bharathsimbu/registry


#2 running docker image for config
docker run -d -p 8099:8099 bharathsimbu/config
