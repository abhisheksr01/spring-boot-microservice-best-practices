# Spring Boot Micro Service Template

### Table of Content

---

- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Installation and Getting Started](#installation-and-Getting-Started)
- [Microservice Structure](#microservice-structure)
- [Development Practice](#development-practice)
- [Integrations](#integrations)
  - [1. Testing](#1-testing)
    - [Unit Test](#unit-test)
    - [End to End Test (Cucumber)](#end-to-end-test)
    - [Mutation Testing](#mutation-testing)
  - [2. Development Accelerators](#2-development-accelerators)
    - [Mapstruct](#mapstruct)
    - [Lombok](#lombok)
    - [WireMock](#wiremock)
  - [3. Code Coverage, Style tests, Code Vulnerabilities](#3-code-coverage,-style-tests,-code-vulnerabilities)
    - [Checkstyle](#checkstyle)
    - [Jacoco](#jacoco)
  - [4. Swagger API Documentation](#4-swagger-api-documentation)
  - [5. Continuous Integration and Continuos Delivery/Deployment](#5-Continuous-integration-and-continuos-delivery/deployment)
    - [5.1 Docker Containerization](#5.1-docker-containerization)
    - [5.2 CI and CD Pipeline Tools](#5.2-ci-and-cd-pipeline-tools)
      - [CircleCI](#circleci)
      - [Jenkins](#jenkins)
      - [Google Cloud Build](#google-cloud-build)
  - [6. Platforms](#6-platforms)
    - [6.1 Kubernetes](#kubernetes)
    - [6.2 Google Cloud Run](#6.2-gcp-cloud-run)
    - [6.3 Cloud Foundry](#6.3-cloud-foundry)
- [What to expect Next!](#what-to-expect-next!)
- [Versioning](#versioning)
- [Author](#author)
- [Contributors](#contributors)
- [License](#license)
- [Acknowledgments](#acknowledgments)

---

### Introduction

This project is intended to bring arguably best practices and integrations available for Spring Boot based Microservice
in a single repository.

Developers can use this repository as a template to build there own Microservice by adding or removing dependencies as
per requirement.

In the below section I will try to explain each integration we have made and how to use.

At the moment the microservice exposes a GET API and expects the company reference as path parameter then makes a call
to the Companies House API hence returning Company Details.

### Prerequisites

1. [ Installing and Getting Started ](#installation)
2. [ Microservice Structure ](#MSStructure)

- You must have [Java](https://www.oracle.com/technetwork/java/javaee/documentation/ee8-install-guide-3894351.html)
  installed (min version 8).
- If you wish to run the application against the actual Companies House
  API. You will need to [create a free account](https://developer.companieshouse.gov.uk/developer/signin)
  and replace the apiKey in the application.yml.

### Installation and Getting Started

Let us get started by Cloning or Downloading repository in your local
workstation.

Once cloned/downloaded import the project in your favourite IDE (IntelliJ, Eclipse etc).

We are using [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) for dependency management
so that you do not need to explicitly configure Gradle or Maven.

Execute below gradlew command to download all the dependencies specified in the gradle.build.

```bash
./gradlew clean build
```

<a propertyName = "MSStructure"></a>

### Microservice Structure

We are following Classic Microservice "Separation of Concerns" pattern having Controller <--> Service <--> Connector layers.

The three different takes the responsibilities as below:

- Controller: Controller layer allows access and handles requests coming from the client. Then invoke a business class to process business-related tasks and then finally respond. Additionally Controller may take responsibility of validating the incoming request Payload thus ensuring that any invalid or malicious data do not pass this layer.

- Service: The business logic is implemented within this layer, thus keeping the logic separate and secure from the controller layer.
  This layer may further call a Connector or Repository/DAO layer to get the Data to process and act accordingly.

- Connector/Repository: The only responsibility of this layer is to fetch data which is required by the Service layer to perform the business logic to serve the request. When our Microservice makes a call to another Service we would like to name it as Connector (as in our case) layer whereas when interacting with a DB commonly it's known as Repository.

### Development Practice

At the core of the Cloud Native Practices in Software Engineering lies the Behavior Driven Development(BDD) and
Test-Driven Development (TDD).
While writing this exercise I followed BDD first approach where we wrote a failing feature/acceptance criteria thus
driving our development and then followed by TDD.

## Integrations

### 1. Testing

#### Unit Test

We are using JUnit 5 for running our unit test cases.

```
./gradlew test
```

#### End to End Test

We are using one of the most famous BDD implementation i.e., Cucumber.

Open Class `CucumberTest` in package `com.uk.companieshouse.e2e` and execute CucumberTest from the class.

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

### 2. Development Accelerators

#### [Mapstruct](https://mapstruct.org/)

An excellent\* library for converting VO to DAO objects and vice versa.

#### [Lombok](https://projectlombok.org/)

Provides excellent annotations based support for Auto generation of methods, logging, Builders, Validation etc.
We will be using below annotations during this exercise:
@Data: Auto generates setters, getters, hashcode and toString methods
@Slf4j: Just add this annotation on top of any Spring Bean and start using the log

### 3. Code Coverage, Style tests, Code Vulnerabilities

#### Checkstyle

```bash
./gradlew check
```

#### [Jacoco](https://www.jacoco.org/jacoco/trunk/index.html)

Code coverage is a preliminary step to know whether our test covers all the scenarios we have developed so far.

Jacoco is a free Java code coverage library distributed under the Eclipse Public License.

```bash
./gradlew test -Pexcludee2e=**/true*
./gradlew jacocoTestReport
./gradlew jacocoTestCoverageVerification
```

### 4. Swagger API Documentation

We are using Swagger.
To test it locally start the application to and the Swagger UI document can be accessed by URL:

```
http://localhost:8080/companieshouse/swagger-ui.html
```

![](images/swagger-ui.png)
Once application is deployed in a Platform the same documentation will be accessible by below URL:

```
http://[HOST_URL]/companieshouse/swagger-ui.html
```

where "companieshouse" is the context path.

### 5. Continuous Integration and Continuos Delivery/Deployment

**Continuous Integration**: It's a software development practise where members of a team integrate their work frequently, usually each person integrates at least daily - leading to multiple integrations per day. Each integration is verified by an automated build (including test) to detect integration errors as quickly as possible.

Continuous Integration is a key step to digital transformation.

**Continuous Delivery**: It's software engineering approach in which teams produce software in short cycles, ensuring that the software can be reliably released at any time and, when releasing the software, doing so **manually**.

**Continuous Deployment**: It means that every change goes through the pipeline and **automatically** gets put into production, resulting in many production deployments every day. To do Continuous Deployment you must be doing Continuous Delivery.

Pictorial representation of the above two approaches:
![](images/continuous-delivery-deployment.png)

Reference :

- https://martinfowler.com/articles/continuousIntegration.html
- https://martinfowler.com/bliki/ContinuousDelivery.html
- https://dzone.com/articles/continuous-delivery-vs-continuous-deployment-an-ov

Now let us look at the key building blocks for achieving CI/CD.

#### 5.1 Docker Containerization

#### 5.2 CI and CD Pipeline Tools

- #### [CircleCI](https://circleci.com/)

  CircleCI is a Software as a Service (SaaS) CI/CD pipeline tool chain in the DevOps world. Personally I found it quite easy to integrate with the GitHub repo and start building my CI/CD pipeline. CircleCI runs each job in a separate container or VM. That is, each time your job runs CircleCI spins up a container or VM to run the job in.

  At the time of writing this article CircleCI's free-tier provides 2500 free credits to run GitHub & Bit Bucket repositories based pipelines.

  It is highly recommended to go through [CircleCI's official Getting Started](https://circleci.com/docs/2.0/getting-started/#section=getting-started).

  To start using CircleCI follow below steps:

  1. [Sign UP](https://circleci.com/docs/2.0/first-steps/)
  2. Create a folder **.circle** and a pipeline config file inside it as **config.yml** (beware the extension should strictly be .yml rather than .yaml, i hope CircleCI fixes this in upcoming releases).
  3. CircleCI's basic building blocks are:

  - [Jobs](https://circleci.com/docs/2.0/jobs-steps/#jobs-overview): Where individual stages in our pipeline is executed.
  - [Steps](https://circleci.com/docs/2.0/jobs-steps/#steps-overview): A collection of executable commands which are run during a job.
  - [Workflows](https://circleci.com/blog/modernizing-federal-devops-circleci-becomes-first-continuous-integration-tool-with-fedramp-authorization/): It orchestrate the jobs as a pipeline.
  - [Executors](): Underlying technology or environment in which to run a job. Example: Docker images, Linux virtual machine (VM) image, MacOS VM image, Windows VM image
  - [Orbs](https://circleci.com/docs/2.0/jobs-steps/#orbs-overview): Reusable & Shareable packages of configs which can be imported to ease the pipeline configurable.

  4. Click [here](.circleci/config.yml) to open the CircleCI config file for this project. When this config runs for the "workflow-all-jobs" the output pipeline is shown below and deploys the app to AWS EKS Cluster.
     ![](images/circleci-pipeline.png)
  5. If you wish to use this config file in your project you must create a context "credentials" and add below Environment Variables to it with appropriate values.

  Follow [this link](https://circleci.com/docs/2.0/env-vars/?utm_medium=SEM&utm_source=gnb&utm_campaign=SEM-gb-DSA-Eng-ni&utm_content=&utm_term=dynamicSearch-&gclid=EAIaIQobChMIm_2blLze6QIVQeztCh3FGwh0EAAYASAAEgITlPD_BwE#setting-an-environment-variable-in-a-context) to learn how to do it.

  ```
  AWS_ACCESS_KEY_ID
  AWS_DEFAULT_REGION
  AWS_SECRET_ACCESS_KEY
  DOCKER_USER (Your docker username)
  DOCKER_PASS (Your docker user's password)
  EKS_CLUSTER_NAME (Kubernetes cluster name)
  DOCKER_IMAGE (Docker Image name)
  EKS_NAMESPACE (Kubernetes namespace to which the aws user has access to and you would like to deploy)
  HEALTH_ENDPOINT (Health Endpoint of your app)
  ```

- #### [Jenkins](https://jenkins.io/)

  `Work in progress`

  Jenkins is one of the most widely used CI/CD Build Tool preferred by many Organizations especially within Enterprises so one needs
  to know the basics of this Mammoth Build Tool.

  Installation Guide:

  1. Jenkins Image (`Recommended`): If you have docker installed, the easiest way to get started is by getting the
     public Jenkins image by following the
     [instructions](https://github.com/jenkinsci/docker/blob/master/ README.md).

  2. Jenkins War: Follow the [instructions](https://www.blazemeter. com/blog/how-to-install-jenkins-on-the-apache-tomcat-server/)
     to install the Jenkins and run on a Web Server.

- #### Google Cloud Build

  [WIP]

  ##### [Cloud Build:](https://cloud.google.com/cloud-build/docs/)

  Cloud Build is a service that executes your builds on Google Cloud Platform infrastructure.
  Cloud Build can import source code from Google Cloud Storage, Cloud Source Repositories, GitHub, or Bitbucket,
  execute a build to your specifications, and produce artefacts such as Docker containers or Java archives.

  Cloud Build executes your build as a series of build steps, where each build step is run in a Docker container.

  We can either trigger Cloud Build through `gcloud` CLI or by declaring the steps in `cloudbuild.yaml`.

  #### Exercise:

  To perform this exercise you must have signed for [Google Cloud Platform account](https://console.cloud.google.com/ free trial/signup/tos)
  and [gcloud SDK configured](https://cloud.google.com/sdk/docs/ quickstarts).

  We are going to deploy our Microservice container to Cloud Run (Fully Managed through Cloud Build).

  Execute below command to use local `cloudbuild.yaml` to perform steps

  - Create a docker image & store it in Container Registry
  - Deploy the image to Cloud Run (Fully Managed)

  ```bash
  gcloud builds submit
  ```

#### 5.3 Kubernetes

### 6. Platforms

#### 6.1 Kubernetes

#### 6.2 [Google Cloud Run](https://cloud.google.com/run/)

    Cloud Run is a fully managed to compute platform that automatically scales your stateless containers.
    Cloud Run is Serverless: it abstracts away all infrastructure management, so you can focus on what matters most—building great applications.

    Cloud Run is available in below two flavours:
    * Cloud Run Fully Managed
    * Cloud Run on Anthos, which supports both Google Cloud and on‐premises environments.

#### 6.3 Cloud Foundry

     [WIP]

### Built With

- [Spring Boot](https://spring.io/projects/spring-boot) - The REST framework
  used
- [GRADLE](https://gradle.org/) - Dependency Management

### What to Expect Next!

As the world of software engineering is evolving so we do.
Listing down some of the exciting features am going to work on and update the GitHub in coming days, they are:

- Chaos Monkey
- Hystrix
- CORS (Cross-Origin)
- Concourse Pipeline
- Associated Officers - Companies House API

### Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions
available, see the
[tags on this repository](https://github.com/your/project/tags).

### Author

- **Abhishek Singh Rajput** - _Initial work_ -
  [abhisheksr01](https://github.com/abhisheksr01)

### Contributors

As mentioned in the [Introduction](#introduction) through this project we would like to bring the Best Practices and Integration under one umbrella.

So you are most welcome to improve or add new features you could think of.

### License

This project is licensed under the MIT License - see the
[LICENSE.md](LICENSE.md) file for details

### Acknowledgments

- [Eugen Paraschiv](https://www.baeldung.com/) : For wonderful tutorials
