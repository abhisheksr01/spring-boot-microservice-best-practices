# Docker file, if we just want to create a Docker Image using the already built jar file
FROM openjdk:17

WORKDIR /opt

EXPOSE 8080

ARG APPJAR=build/libs/companieshouse-*.jar

COPY ${APPJAR} companieshouse-*.jar

ENTRYPOINT ["java","-jar","companieshouse-*.jar"]

RUN groupadd --gid 10001 rungroup && useradd --uid 10001 --gid 10001 runuser
USER runuser:rungroup