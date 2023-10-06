# Sample Dockerfile when we want to use an existing jar file for image creation
FROM openjdk:21-slim

WORKDIR /opt

EXPOSE 8080

ARG APPJAR=build/libs/companieshouse-*.jar

COPY ${APPJAR} companieshouse-*.jar

ENTRYPOINT ["java","-jar","companieshouse-*.jar"]

RUN groupadd --gid 10001 rungroup && useradd --uid 10001 --gid 10001 runuser
USER runuser:rungroup