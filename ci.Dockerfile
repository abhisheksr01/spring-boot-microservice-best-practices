# Docker file if we just want to create a Docker Images using the already built jar file
FROM openjdk:8-jre-alpine

WORKDIR /opt

EXPOSE 8080

RUN apk add --no-cache --upgrade libtasn1=4.14-r0 sqlite-libs=3.28.0-r3 musl-utils=1.1.20-r6 libjpeg-turbo=1.5.3-r6 \
openjdk8-jre=8.275.01-r0 openjdk8-jre-base=8.275.01-r0 openjdk8-jre-lib=8.275.01-r0 libx11=1.6.12-r0 freetype=2.9.1-r3

ARG APPJAR=build/libs/companieshouse-*.jar

COPY ${APPJAR} companieshouse-*.jar

ENTRYPOINT ["java","-jar","companieshouse-*.jar"]