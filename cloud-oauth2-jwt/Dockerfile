FROM openjdk:8-jdk-alpine
LABEL maintainer="dickanirwansyah@gmail.com"
ADD target/cloud-oauth2.jar cloud-oauth2.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "cloud-oauth2.jar"]