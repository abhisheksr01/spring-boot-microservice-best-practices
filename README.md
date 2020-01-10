# Spring Boot Micro Service Template (WIP)

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

* You must have [Java](https://www.oracle.com/technetwork/java/javaee/documentation/ee8-install-guide-3894351.html)
installed (min version 8).
* If you wish to run the application against the actual Companies House
API. You will need to [create a free account](https://developer.companieshouse.gov.uk/developer/signin)
and replace the apiKey in the application.yml.

### Installing and Getting Started

Let us get started by Cloning or Downloading repository in your local
workstation.

Once cloned/downloaded import the project in your favourite IDE (IntelliJ, Eclipse etc).

We are using [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) for dependency management so that you do not need to explicitly configure Gradle or Maven.

Execute below gradlew command to download all the dependencies specified in the gradle.build.

```bash
./gradlew clean build 
```

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

#### Jacoco

<!-- Explain what these tests test and why -->

```
WIP
```

### 5. Deployment

#### [Jenkins](https://jenkins.io/)
We are starting with our good old friend Jenkins for creating an Automate Pipeline.
[WIP]

<!-- Add additional notes about how to deploy this on a live system -->

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The REST framework
used
* [GRADLE](https://gradle.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions
available, see the
[tags on this repository](https://github.com/your/project/tags).

## Authors

* **Abhishek Singh Rajput** - *Initial work* -
[abhisheksr01](https://github.com/abhisheksr01)

## License

This project is licensed under the MIT License - see the
[LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* [Eugen Paraschiv](https://www.baeldung.com/)