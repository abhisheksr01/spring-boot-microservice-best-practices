# Docker file if we just want to create a Docker Images using the already built jar file
FROM openjdk:8-jre-alpine

WORKDIR /opt

EXPOSE 8080

RUN apk update

RUN apk add --upgrade libtasn1 sqlite-libs musl-utils libjpeg-turbo \
openjdk8-jre openjdk8-jre-base openjdk8-jre-lib libx11

ARG APPJAR=build/libs/companieshouse-0.0.1-SNAPSHOT.jar

COPY ${APPJAR} companieshouse-0.0.1-SNAPSHOT.jar

ENTRYPOINT exec java $JAVA_OPTS -jar companieshouse-0.0.1-SNAPSHOT.jar