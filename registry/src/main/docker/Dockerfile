FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER Bharath Mannaperumal <bharathkumar.feb14@gmail.com>
ADD registry-0.0.1-SNAPSHOT.jar registry.jar
ENTRYPOINT ["java", "-jar", "/registry.jar"]
EXPOSE 8761