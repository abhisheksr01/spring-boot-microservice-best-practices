# Sample Dockerfile when we want to use an existing jar file for image creation
FROM openjdk:21-slim

RUN apt-get update && apt-get upgrade -y && apt-get clean && rm -rf /var/lib/apt/lists/*

WORKDIR /opt

EXPOSE 8080

ARG APPJAR=build/libs/companieshouse-*.jar

COPY ${APPJAR} companieshouse-*.jar

ENTRYPOINT ["java","-jar","companieshouse-*.jar"]

RUN groupadd --gid 10001 rungroup && useradd --uid 10001 --gid 10001 runuser
USER runuser:rungroup