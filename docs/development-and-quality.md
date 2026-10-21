# Development and Quality

[Back to the project README](../README.md)

This page brings together the README's testing, development accelerator, analysis and quality check, and API documentation sections.

## Contents

- [1. Testing](#1-testing)
- [2. Development Accelerators](#2-development-accelerators)
- [3. Analysis and Quality Checks](#3-analysis-and-quality-checks)
- [4. API Documentation](#4-api-documentation)

## Integrations

### 1. Testing

#### 1.1 Unit Test

We are using JUnit 5 for running our unit test cases.

```bash
./gradlew test
```

Once executed a report as below will be generated at local path

```bash
build/reports/tests/test/index.html
```

![](images/unit-test-report.png)

#### 1.2 Cucumber End-to-End Test

We are using one of the most famous BDD implementation i.e., Cucumber.

Open Class `CucumberTest` in package `com.uk.companieshouse.e2e` and execute CucumberTest from the class.

Once the test execution completes you can see the Cucumber Test Report at :

```bash
../build/reports/cucumber/index.html
```

#### 1.3 Mutation Testing

Pitest is used for performing mutation testing.
To execute the mutation test run :

```bash
./gradlew pitest
```

once the test execution completes report should be accessible at:

```bash
../build/reports/pitest/[TIMESTAMP]/index.html
```

![](images/pitest-report.png)

#### 1.4 Local Testing

The application can be started in the IDE through [CompaniesHouseApplication.java](../src/main/java/com/uk/companieshouse/CompaniesHouseApplication.java) or by executing below command in the terminal

```bash
./gradlew bootRun
```

Once the application starts the application can be accessed through below URL:

```
curl --location 'http://localhost:8080/companieshouse/search/abhishek'
```

If valid Rest API Key is added in `authUserName` then we should receive all the companies details that has `abhishek` in their registered **title**

### 2. Development Accelerators

#### [2.1 Mapstruct](https://mapstruct.org/)

An excellent\* library for converting VO to DAO objects and vice versa.

#### [2.2 Lombok](https://projectlombok.org/)

Provides excellent annotations based support for Auto generation of methods, logging, Builders, Validation etc.
We will be using below annotations during this exercise:<br>
@Data: Auto generates setters, getters, hashcode and toString methods<br>
@Slf4j: Just add this annotation on top of any Spring Bean and start using the log

#### [2.3 WireMock](http://wiremock.org/)

**Updating instructions WIP**

Click [here](../src/test/java/com/uk/companieshouse/e2e/WireMockService.java) to see implementation.

### 3. Analysis and Quality Checks

#### 3.1 Checkstyle

Checkstyle is a static code analysis tool used in software development for checking if Java source code complies with coding rules.

<details>
  <summary><em><ins>Click here to explore Checkstyle implementation.</ins></em></summary><br>

The following is an example Checkstyle setup for Gradle; the current repository [build.gradle](../build.gradle) does not apply the Checkstyle plugin.

```bash
plugins {
    id 'checkstyle'
}
checkstyle {
    toolVersion '7.8.1'
}
tasks.withType(Checkstyle) {
    reports {
        xml.enabled false
        html.enabled true
        html.stylesheet resources.text.fromFile('config/xls/checkstyle-style.xsl')
    }
}
```

and checkstyle [config at this location](../config/checkstyle).

The command below runs this repository's configured Gradle checks; it does not run Checkstyle unless the plugin is applied.

```bash
./gradlew check
```

Once successfully executed Checkstyle reports will be generated at:

```bash
build/reports/checkstyle/main.html
build/reports/checkstyle/test.html
```

![](images/checkstyle-report.png)

</details>

#### [3.2 Jacoco](https://www.jacoco.org/jacoco/trunk/index.html)

Code coverage is a preliminary step to know whether our test covers all the scenarios we have developed so far.

Jacoco is a free Java code coverage library distributed under the Eclipse Public License.

<details>
  <summary><em><ins>Click here to explore Jacoco implementation.</ins></em></summary><br>

Add below configuration in [build.gradle](../build.gradle) to enable Jacoco in your project.

```bash
plugins{
  id 'jacoco'
}
ext{
  jacocoVersion = "0.8.9"
}
// Jacoco for Code Coverage
jacoco {
    toolVersion = "${jacocoVersion}"
    reportsDir = file("$buildDir/customJacocoReportDir")
}

// Runs Jacoco tasks when build task is executed
build {
    finalizedBy jacocoTestReport
    finalizedBy jacocoTestCoverageVerification
}
// Setting custom parameters when executing jacocoTestReport task
jacocoTestReport {
    reports {
        xml.enabled false
        csv.enabled false
        html.destination file("${buildDir}/reports/jacocoHtml")
    }
}

// Setting custom parameters when executing jacocoTestCoverageVerification task for setting rules
jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = 1.0
            }
        }

        rule {
            enabled = false
            element = 'CLASS'
            includes = ['org.gradle.*']

            limit {
                counter = 'LINE'
                value = 'TOTALCOUNT'
                maximum = 1.0
            }
        }
    }
}
```

Execute below commands to generate and test code coverage(jacoco tasks depends on test task).

```bash
./gradlew test -Pexcludee2e=true
./gradlew jacocoTestReport
./gradlew jacocoTestCoverageVerification
```

Once successfully executed a report as shown below will be generated at path

```bash
build/reports/jacocoHtml/index.html
```

![](images/jacoco-report.png)

</details>

#### [3.3 Hadolint](https://github.com/hadolint/hadolint)

A smarter Dockerfile linter that helps you build best practice Docker images.

<details>
  <summary><em><ins>Click here to explore Hadolint implementation.</ins></em></summary><br>

Running Locally:

You may wish to install the tooling locally [click here](https://github.com/hadolint/hadolint#install) to see the available options.

Once installed just run below command:
```
hadolint DOCKER_FILE
```

Example: Execute from the root of this repo as we have 2 dockerfiles in this repo.
```bash
hadolint testhadolinttest.Dockerfile
```

or you may directly run using the docker as below:
```bash
docker run --rm hadolint/hadolint < testhadolinttest.Dockerfile
```

Expected Output:
```agsl
testhadolinttest.Dockerfile:8 DL3018 warning: Pin versions in apk add. Instead of `apk add <package>` use `apk add <package>=<version>`
testhadolinttest.Dockerfile:8 DL3019 info: Use the `--no-cache` switch to avoid the need to use `--update` and remove `/var/cache/apk/*` when done installing packages

```

Fix:  Pin versions in apk add and Use the `--no-cache` as shown below:
```agsl
RUN apk add --no-cache --upgrade libtasn1=4.14-r0 sqlite-libs=3.28.0-r3 musl-utils=1.1.20-r6 libjpeg-turbo=1.5.3-r6 \
libx11=1.6.12-r0 freetype=2.9.1-r3
```

In pipeline:

We can use hadolint to lint dockerfile in the pipeline before building the images, [click here](https://github.com/abhisheksr01/spring-boot-microservice-best-practices/blob/8a78f5c4939f2637832ed0d2f9ee6f70a351577b/.circleci/config.yml#L162) to see how we can configure in CircleCI.

Hadolint allows us to add configuration file to configure rules to ignore errors, trusted registries etc., [click here](https://github.com/hadolint/hadolint#configure) to learn more.

</details>

### 4. API Documentation

With the latest version of [SpringDoc-API](https://springdoc.org/v2/) you just need to include a single dependency as below & that's it.
```bash
implementation "org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4"
```

This library supports:
- OpenAPI 3
- Spring-boot v3 (Java 21 & Jakarta EE 9)
- JSR-303, specifically for @NotNull, @Min, @Max, and @Size.
- Swagger-ui
- OAuth 2
- GraalVM native images

To test it locally start the application then Swagger UI documentation can be accessed by URL:

```bash
http://localhost:8080/companieshouse/swagger-ui/index.html
```

![](images/swagger-ui.png)
Once application is deployed in a Platform the same documentation will be accessible by below URL:

```bash
http://[HOST_URL]/companieshouse/swagger-ui/index.html
```

where "companieshouse" is the context path.
