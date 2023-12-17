# Testing

# Java Test Automation Framework

Ready-to-use UI Test Automation Architecture using Java and Selenium WebDriver.
## Getting started

To make it easy for you to get started with GitLab, here's a list of recommended next steps.

## Installation Steps

In order to use the framework:

Add files using the command line or push an existing Git repository with the following command:

###### `cd existing_repo`
###### `git push -uf origin main`
###### `git remote add origin https://gitlab.tool.mddinternship.com/book-sharing/testing.git`
###### `git branch -M main`

## Languages, Frameworks, Dependencies
The project uses the following:

- [Java 11](https://openjdk.org/projects/jdk/11/) as the programming language.
- [Selenium](https://www.selenium.dev/) for web application testing.
- [Log4j2](https://logging.apache.org/log4j/2.x/) and [Slf4j](https://www.slf4j.org/) for logging.
- [Hibernate](https://hibernate.org/) for database testing.
- [Rest Assured](https://rest-assured.io/) for API testing.
- [Cucumber](https://cucumber.io/) for BDD-style testing.
- [JUnit](https://junit.org/junit4/) as the testing framework.
- [Lombok](https://projectlombok.org/) to generate getters, setters and constructors.
- [IntelliJ](https://www.jetbrains.com/idea/) IDEA as the IDE.

## Configuration

- Log configuration is specified in the `log4j2.xml` file.
- Database configuration can be found in the `hibernate.cfg.xml` file.
- API endpoints and Selenium configurations are specified in respective classes or property files.
- Cucumber configurations are available in the `cucumber.properties` file.

## How to Run Tests

1. Clone the repository: `git clone https://gitlab.tool.mddinternship.com/book-sharing/testing.git`
2. Navigate to the project directory: `cd book-sharing-project-atf`
3. Build the project: `mvn clean install`
4. Run the tests: `mvn test`

## Maven Support

The project uses Maven for dependency management and build. The `pom.xml` file includes all necessary dependencies and configurations.

## Logging

- Log files are generated in the `target/logs` directory.
- Log files are named based on the scenario that was run, including date and time.

## Authors
- Begu Valentina
- Galca Andrei