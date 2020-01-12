# Spring Boot Micro Service Template

### Table of Content
----------------------------------
- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Installation and Getting Started](#installation-and-Getting-Started)
- [Microservice Structure](#microservice-structure)
- [Development Practice](#development-practice)
- [Integrations](#integrations)
    - [1. Testing](#1-testing)
        - [Unit Test](#unit-test)
        - [End to End Test](#end-to-end-test)
        - [Mutation Testing](#mutation-testing)
    - [2. API Documentation](#2-api-documentation)
    - [3. Development Accelerators](#3-development-accelerators)
        - [Mapstruct](#mapstruct)
        - [Lombok](#lombok)
    - [4. Code Coverage, Style tests, Code Vulnerabilities](#4-code-coverage,-style-tests,-code-vulnerabilities)
        - [Checkstyle](#checkstyle)
        - [Jacoco](#jacoco)
    - [5. Continuous Integration and Continuos Deployment](#5-Continuous-integration-and-continuos-deployment)
        - [Jenkins](#jenkins)
- [What to expect Next!](#what-to-expect-next!)
- [Versioning](#versioning)
- [Author](#author)
- [Contributors](#contributors)
- [License](#license)
- [Acknowledgments](#acknowledgments)

----------------------------------

### Introduction
This project is intended to bring arguably best practices and
integrations available for Spring Boot based Microservice in a single
repository.

Developers can use this repository as a template to build there own
Microservice by adding or removing dependencies as per requirement.

In the below section I will try to explain each integration we have made
and how to use.

At the moment the microservice exposes a GET API and expects the company
reference as path parameter then makes a call to the Companies House API
hence returning Company Details.

### Prerequisites

1. [ Installing and Getting Started ](#installation)
2. [ Microservice Structure ](#MSStructure)

* You must have [Java](https://www.oracle.com/technetwork/java/javaee/documentation/ee8-install-guide-3894351.html)
installed (min version 8).
* If you wish to run the application against the actual Companies House
API. You will need to [create a free account](https://developer.companieshouse.gov.uk/developer/signin)
and replace the apiKey in the application.yml.

### Installation and Getting Started

Let us get started by Cloning or Downloading repository in your local
workstation.

Once cloned/downloaded import the project in your favourite IDE (IntelliJ, Eclipse etc).

We are using [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) for dependency management so that you do not need to explicitly configure Gradle or Maven.

Execute below gradlew command to download all the dependencies specified in the gradle.build.

```bash
./gradlew clean build 
```
<a propertyName = "MSStructure"></a>
### Microservice Structure

We are following Classic Microservice "Separation of Concerns" pattern having Controller <--> Service <--> Connector layers.

The three different takes the responsibilities as below:

* Controller: Controller layer allows access and handles requests coming from the client. Then invoke a business class to process business-related tasks and then finally respond. Additionally Controller may take responsibility of validating the incoming request Payload thus ensuring that any invalid or malicious data do not pass this layer.

* Service: The business logic is implemented within this layer, thus keeping the logic separate and secure from the controller layer. This layer may further call a Connector or Repository/DAO layer to get the Data to process and act accordingly.

* Connector/Repository: The only responsibility of this layer is to fetch data which is required by the Service layer to perform the business logic to serve the request. When our Microservice makes a call to another Service we would like to name it as Connector (as in our case) layer whereas when interacting with a DB commonly it's known as Repository.

### Development Practice

At the core of the Cloud Native Practices in Software Engineering lies the Behavior Driven Development(BDD) and Test-Driven Development (TDD).
While writing this exercise I followed BDD first approach where we wrote a failing feature/acceptance criteria thus driving our development and then followed by TDD.

## Integrations

### 1. Testing

#### Unit Test
We are using JUnit 5 for running our unit test cases.
```
./gradlew test
```

#### End to End Test
We are using one of the most famous BDD implementation i.e., Cucumber.

Open Class ```CucumberTest``` in package ```com.uk.companieshouse.e2e``` and execute CucumberTest from the class.

Once the test execution completes you can see the Cucumber Test Report at :

```
../companies-house-microservice-template/reports/cucumber/index.html
```

#### Mutation Testing

Pitest is used for performing mutation testing.
To execute the mutation test run :

```bash
./gradlew pitest
```

once the test execution completes report should be accessible at:
```
../companies-house-microservice-template/build/reports/pitest/[TIMESTAMP]/index.html
```

### 2. API Documentation

We are using Swagger.
Once we start the application the Swagger UI document can be accessed by URL:

```
http://localhost:8080/swagger-ui.html
```
### 3. Development Accelerators

#### [Mapstruct](https://mapstruct.org/)

An excellent* library for converting VO to DAO objects and vice versa.

#### [Lombok](https://projectlombok.org/)

Provides excellent annotations based support for Auto generation of methods, logging, Builders, Validation etc.
We will be using below annotations during this exercise:
@Data: Auto generates setters, getters, hashcode and toString methods
@Slf4j: Just add this annotation on top of any Spring Bean and start using the log


### 4. Code Coverage, Style tests, Code Vulnerabilities

#### Checkstyle

```bash
./gradlew check
```

#### [Jacoco](https://www.jacoco.org/jacoco/trunk/index.html)

Code coverage is preliminary step to know whether our test covers all the scenarios we have developed so far.

Jacoco is a free Java code coverage library distributed under the Eclipse Public License.

```
WIP
```

### 5. Continuous Integration and Continuos Deployment


#### [Jenkins](https://jenkins.io/)
```Work in progress```

Jenkins is one of the most famous CI/CD Build Tool used by many Organizations especially within Enterprises so one needs to know the basics of this Mammoth Build Tool.

Installation Guide:

1. Jenkins Image (```Recommended```): If you have docker installed, the easiest way to get started is by getting the public Jenkins image by following the
[instructions](https://github.com/jenkinsci/docker/blob/master/README.md).

2. Jenkins War: Follow the [instructions](https://www.blazemeter.com/blog/how-to-install-jenkins-on-the-apache-tomcat-server/) to install the Jenkins and run on a Web Server.


<!-- Add additional notes about how to deploy this on a live system -->

### Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The REST framework
used
* [GRADLE](https://gradle.org/) - Dependency Management

### What to Expect Next!

As the world of software engineering is evolving so we do.
Listing down some of the exciting features am going to work on and update the github in coming days, they are:

* Docker
* Chaos Monkey
* Hystrix
* CORS (Cross Origin)
* Cloud Build - GCP
* Concourse Pipeline
* Associated Officers - Companies House API

### Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions
available, see the
[tags on this repository](https://github.com/your/project/tags).

### Author

* **Abhishek Singh Rajput** - *Initial work* -
[abhisheksr01](https://github.com/abhisheksr01)

### Contributors

As mentioned in the [Introduction](#introduction) through this project we would like to bring the Best Practices and Integration under one umbrella.

So you are most welcome to improve or add new features you could think of.

### License

This project is licensed under the MIT License - see the
[LICENSE.md](LICENSE.md) file for details

### Acknowledgments

* [Eugen Paraschiv](https://www.baeldung.com/) : For the wonderful tutorials