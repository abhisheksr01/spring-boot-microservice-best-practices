# Docker file if we just want to create a Docker Images using the already creatd jar file
#FROM openjdk:8-jre-alpine
#WORKDIR /opt
#ENV PORT 8080
#EXPOSE 8080
#ARG APPJAR=build/libs/companieshouse-0.0.1-SNAPSHOT.jar
#COPY ${APPJAR} companieshouse-0.0.1-SNAPSHOT.jar
#ENTRYPOINT exec java $JAVA_OPTS -jar companieshouse-0.0.1-SNAPSHOT.jar

# The below Docker code creates an executable jar and then creates an Docker Image out of it.
FROM gradle:6.3.0-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test --no-daemon

FROM openjdk:8-jre-slim

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/companieshouse-0.0.1-SNAPSHOT.jar

ENTRYPOINT exec java $JAVA_OPTS -jar /app/companieshouse-0.0.1-SNAPSHOT.jar