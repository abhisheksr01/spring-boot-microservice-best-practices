# The below Docker code creates an executable jar and then creates an Docker Image out of it.
FROM gradle:7.6.1-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test -x checkstyleTest --no-daemon

FROM openjdk:17-jre-alpine
RUN apk add --no-cache --upgrade libtasn1=4.14-r0 sqlite-libs=3.28.0-r3 musl-utils=1.1.20-r6 libjpeg-turbo=1.5.3-r6 \
libx11=1.6.12-r0 freetype=2.9.1-r3
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/companieshouse-*.jar
ENTRYPOINT ["java","-jar","companieshouse-*.jar"]
RUN groupadd --gid 10001 rungroup && useradd --uid 10001 --gid 10001 runuser
USER runuser:rungroup