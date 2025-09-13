# Stage 1: Build the jar
FROM gradle:8.14.3-jdk21-jammy AS build

# Update system packages
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copy source code and build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test --no-daemon

# Stage 2: Production image
FROM openjdk:21-slim-bookworm AS production
EXPOSE 8080

# Update system packages and install fixed versions
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y --no-install-recommends \
        libc6 \
        util-linux \
    && apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Create non-root user with fixed UID/GID
RUN groupadd -r appgroup -g 10001 && \
    useradd -r -g appgroup -u 10001 appuser && \
    mkdir /app && \
    chown 10001:10001 /app

# Copy jar with specific name
COPY --from=build --chown=10001:10001 /home/gradle/src/build/libs/*.jar /app/companieshouse.jar

WORKDIR /app
USER 10001

# Security-focused Java options
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom \
    -Djava.awt.headless=true \
    -Dfile.encoding=UTF-8 \
    -XX:+ExitOnOutOfMemoryError \
    -XX:+UseContainerSupport \
    -XX:MaxRAMPercentage=75.0 \
    -Dspring.profiles.active=production \
    -Dserver.tomcat.accesslog.enabled=true"

# Add healthcheck
HEALTHCHECK --interval=30s --timeout=3s \
    CMD curl -f http://localhost:8080/companieshouse/actuator/health || exit 1

# Use specific jar name in entrypoint
ENTRYPOINT ["java", "-jar", "/app/companieshouse.jar"]
