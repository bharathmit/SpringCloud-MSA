# SpringCloud-MSA
Micro Service Architecture with Spring Boot and Spring Cloud

This is a [proof-of-concept application](https://jsoftgroup.wordpress.com/2017/05/09/micro-service-using-spring-cloud-and-netflix-oss/), which demonstrates [Microservice Architecture Pattern](http://martinfowler.com/microservices/) using Spring Boot, Spring Cloud and Docker.
With a pretty neat user interface, by the way.

### Build a Docker Image with Maven Plugin: (It will push automatically into the local docker image)

#### mvn install dockerfile:build

### Run The Docker Image :

Create the Network C2C call : docker network create spring-microservice-network


### 1 running docker image for Eureka Registry
#### docker run -d --network spring-microservice-network --name registry -p 8761:8761 bharathsimbu/registry

### 2 running docker image for Config Server
#### docker run -d --network spring-microservice-network --name config -p 8099:8099 bharathsimbu/config

### 3 running docker image for API Gateway
#### docker run -d --network spring-microservice-network --name gateway -p 8040:8040 bharathsimbu/gateway


### 4 running docker image for Product Service
#### docker run -d --network spring-microservice-network --name product -p 8010:8010 bharathsimbu/product

### 5 running docker image for inventory Service
#### docker run -d --network spring-microservice-network --name inventory -p 8020:8020 bharathsimbu/inventory

### 6 running docker image for review Service
#### docker run -d --network spring-microservice-network --name review -p 8030:8030 bharathsimbu/review
