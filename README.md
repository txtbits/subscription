# Subscription API

Development of a subscription REST-based API based on distributed microservices with Spring Boot
---
The main subscription service persists the subscription in the database and subsequently informs the mail service with it. Both are secure by basic authentication.
Another public subscription service with reduced public operations is also available connecting directly to the main subscription service.
All of them are documented with Swagger.

The project is made up of three independent modules/services:
- **Private Subscription Service**: Private and secure REST API with database persistence through Hibernate and Swagger playground. Its the main service of the system.
- **Public Subscription Service**: Public REST API, a public gateway to the Private subscription service.
- **Mail Service**: Private and secure REST API mail service. Called by Private subscription service to send a subscription email.

# Tools and frameworks

Tools needed to build, deploy and run the project:
- **Java JDK 8**. Recognized class-based, object-oriented programming language.
- **Gradle** (included in project with Gradle Wrapper). Open-source build automation tool that is designed to be flexible enough to build almost any type of software.
- **Docker** (optional). A set of platform as a service (PaaS) products that use OS-level virtualization to deliver software in packages called containers.
- **Jenkins** (optional). Open source automation server which enables developers around the world to reliably build, test, and deploy their software.
- **Kubernetes** (optional). Open-source system for automating deployment, scaling, and management of containerized applications.

The development startup has been done using the utility [Spring Initializr](https://start.spring.io/) to initialize Spring Boot projects with some selected dependencies. The technologies used in the project are:
- **Spring Boot**: Open source Java-based framework used to create a micro Services.
- **Spring Web**: Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
- **Spring Data JPA**: Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.
- **Spring Security**: Additional framework that provides authentication, authorization, and protection to Spring Boot.
- **Swagger**: Toolset to design, build, document, and use RESTful web services. Includes automated documentation, code generation and test-case generation.
- **HyperSQL Database**: Lightweight 100% Java SQL Database Engine for fast test for development deployment.
- **Mysql 5.7 Database and Mysql driver**: MySQL Database docker image and neccesary JDBC and R2DBC driver for docker deployment.
- **Spring Configuration Processor**: Generate metadata for developers to offer contextual help and "code completion" when working with custom configuration keys (ex.application.properties/.yml files).
- **Lombok**: Java annotation library which helps to reduce boilerplate code.
- **Mockito**: JAVA-based library that is used for effective unit testing of JAVA applications.
- **MailHog**: MailHog is an email testing tool (SMTP delivery) for developers.

# Build, deploy and run

The project has two different building profiles:
- **Dev**: To easily and quickly deploy the project only using Java. HyperSQL Database is used for this profile, simple file system based database for testing without additional configuration.
- **Pro**: To deploy the full system using Docker images through Docker Compose, Jenkins pipeline for CI/CD and proper orchestration with Kubernetes by _k8s.yaml_ files.

## Building and running for `dev` profile
To build the project you must use gradle. You must go into the directory of each module and execute:
```
gradle clean build -x test
```
The process must be repeated in each directory.

After that, you can launch each application executable:

- Private Subscription Service _./subscriptionservice_
```
java -jar build/libs/subscriptionservice-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```
- Public Subscription Service _./publicservice_
```
java -jar build/libs/publicservice-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```
- Mail Service _./mailservice_
```
java -jar build/libs/mailservice-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

* To access to the Private Subscription API Swagger Playground: [http://localhost:8886/swagger-ui.html](http://localhost:8886/swagger-ui.html)
    * You may add a new subscription record making a POST HTTP request to [http://localhost:8886/subscription/](http://localhost:8886/subscription/). You can do it easily throught Swagger Playground populating body parameter and clicking at _'Try it out!'_ button. Also using CURL, Postman, etc.
    * There are **three** available rest methods in private zone:
      * GET List all subscriptions
      * PUT Create new subscription to database
      * DELETE Delete subscription from database by identifier (only in private zone)
* * To access to the Public Subscription API Swagger Playground: [http://localhost:8885/swagger-ui.html](http://localhost:8885/swagger-ui.html)
  * * There are **two** available rest methods in public zone:
    * GET List all subscritions from database
    * PUT Create new subscription to database
* * To access to the Mail API Swagger Playground: [http://localhost:8887/swagger-ui.html](http://localhost:8887/swagger-ui.html)

If you want to check SMTP delivery with MailHog in `dev` profile, run its docker image:
```
docker run -d -p 8025:8025 -p 1025:1025 mailhog/mailhog
```

## Building and running for `pro` profile

There are one `Jenkinsfile` CI/CD pipeline in each module ir order to automate the entire building and deployment process for `pro` profile. I've used [Gradle Docker plugin by Palantir](https://github.com/palantir/gradle-docker) to do docker image building process more easy.

Otherwise, you can do it manually:

To build the project with Docker, you must first build the docker images using the Dockerfile file included in each directory.
```
docker build -t txtbits/subscription:0.0.1-SNAPSHOT .
```
```
docker build -t txtbits/public:0.0.1-SNAPSHOT .
```
```
docker build -t txtbits/mail:0.0.1-SNAPSHOT .
```
The Dockerfile files of each module already have the `spring.profiles.active=pro` parameter when executing the jar file.
The docker images must have the same names for the Docker Compose Tool to recognize them and to create the bundle correctly.
The _docker-compose.yml_ file contains all the necessary configuration to launch the containers simultaneously and run the dockerized environment.

To launch the bundle you must run:
```
docker compose up -d
```
In the same way as in the dev profile:
* To access to the Private Subscription API Swagger Playground: [http://localhost:8886/swagger-ui.html](http://localhost:8886/swagger-ui.html)
    * You may add a new subscription record making a POST HTTP request to [http://localhost:8886/subscription/](http://localhost:8886/subscription/). You can do it easily throught Swagger Playground populating body parameter and clicking at _'Try it out!'_ button. Also using CURL, Postman, etc.
    * There are **three** available rest methods in private zone:
      * GET All subscriptions
      * PUT Create new subscription to database
      * DELETE Delete subscription from database by identifier
* * To access to the Public Subscription API Swagger Playground: [http://localhost:8885/swagger-ui.html](http://localhost:8885/swagger-ui.html)
  * * There are **two** available rest methods in public zone:
    * GET List all subscritions from database
    * PUT Create new subscription to database
* * To access to the Mail API Swagger Playground: [http://localhost:8887/swagger-ui.html](http://localhost:8887/swagger-ui.html)

To stop the instance and remove all containers, use:
```
docker compose down
```

# Extra parts

## CI/CD Pipeline

As an example project, I put all modules in the same repository to access more easy, but the most common way to do would be to have a repository for each module and a main pipeline that would manage the pipelines of each module.

As mentioned before, a Jenkinsfile pipeline is included in each service module. The steps are detailed below:
- **Github**: Through the Jenkins Github plugin, download the application project from origin repository. As an example, the repository is public, but it would be best to use some credentials.
  - Note: Added pollSCM trigger to poll the git repository every 30 minutes for changes to it.
- **Build**: Assemble the entire project and obtain an executable jar.
- **Test**: Execution of project tests.
- **Build Docker image**: Build docker image from DockerFile via docker gradle task.
- **Push Docker image**: Push docker image to Docker Hub repository and run a local instance from the generated image.
- **K8s deployment** Deploy in Kubernetes using k8s.yaml config file.

Another extra steps would be "nice to have" like code inspection (SonarQube), alerts and notifications (Grafana, Prometheus, etc.)

## Kubernetes config

I cannot verify the correct operation in Kubernetes since I have encountered some communication problems between services that I have not had time to solve, but it would be close to a starting point.
- [Subscription service k8s](./subscriptionservice/k8s/)
- [Public service k8s](./publicservice/k8s/)
- [Mail service k8s]((./mailservice/k8s/))