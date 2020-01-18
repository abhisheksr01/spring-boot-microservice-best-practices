FROM openjdk:8-jdk
ADD build/libs/companieshouse-0.0.1-SNAPSHOT.jar companieshouse-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "companieshouse-0.0.1-SNAPSHOT.jar"]
