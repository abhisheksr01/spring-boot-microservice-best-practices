# The below Docker code creates an executable jar and then creates an Docker Image out of it.
FROM gradle:7.6.1-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test --no-daemon

FROM openjdk:17
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/companieshouse-*.jar
ENTRYPOINT ["java","-jar","app/companieshouse-*.jar"]