# SpringCloud-MSA
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fbharathmit%2FSpringCloud-MSA.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fbharathmit%2FSpringCloud-MSA?ref=badge_shield)

https://app.fossa.com/reports/279cb3c9-0901-4505-b9db-0c10afa83480

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
#### docker run -d --network spring-microservice-network --name product --link gateway -p 8010:8010 bharathsimbu/product

### 5 running docker image for inventory Service
#### docker run -d --network spring-microservice-network --name inventory -p 8020:8020 bharathsimbu/inventory

### 6 running docker image for review Service
#### docker run -d --network spring-microservice-network --name review -p 8030:8030 bharathsimbu/review


docker ps -a 

docker rmi bharathsimbu/product

docker-compose up -d

docker-compose down


#SCALE
docker-compose up --scale product=2 product

#remove all unused volume
docker volume prune



### Changing the Logging Level at the Runtime for a Spring Boot Application
#### access our log API: http://localhost:8010/actuator/loggers
#### To change the logging level, we can issue a POST request to the /loggers endpoint.
#### POST request http://localhost:8080/actuator/loggers/com.jsoftgroup {"configuredLevel":"TRACE","effectiveLevel":"TRACE"}



## License
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fbharathmit%2FSpringCloud-MSA.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Fbharathmit%2FSpringCloud-MSA?ref=badge_large)
