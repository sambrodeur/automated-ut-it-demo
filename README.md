# Automated UT IT Demo

A demonstration of step-by-step test-driven development practices. This project shows how to create comprehensive unit
tests and integration tests using a modern approach while leveraging established testing patterns. Built on a Spring
Boot application that provides a RESTful API for managing companies and their associated contacts.

## Technologies Used

- **Spring Boot 4.0.6**: Framework for building the application
- **Java 25**: Programming language
- **H2 Database**: In-memory relational database
- **Spring Web**: For RESTful web services
- **Spring JDBC**: For database operations
- **SpringDoc OpenAPI**: For API documentation
- **Lombok**: For reducing boilerplate code
- **Maven**: Build tool

## Test Technologies Used

- **Spring Boot Starter Test**: Includes JUnit 6, Mockito, AssertJ, and other testing libraries
- **Instancio**: For generating random test data and fixtures

## Prerequisites

- Java 25 or higher
- Maven 3.6+ (or use the included Maven wrapper)
- Gradle (can be used with tasks but not shown in this example)

## How to implement the tests solution

### Step 1 - Settings up the project for testing

#### 1. **Add Properties**
- **What Changed**: Added properties for test dependencies version and maven skip test flag in `pom.xml`
- **Purpose**: Centralizes version management for test dependencies and provides a flag to skip tests when needed
- **Code Change**:
  ```xml
  <properties>
  ...
    <maven.test.skip>false</maven.test.skip>
    <instancio.version>5.5.1</instancio.version>
  ...
  </properties>
  ```

#### 2. **Add Test Dependencies**
- **What Changed**: Added necessary test dependencies in `pom.xml` for Spring Boot testing, JUnit, and Instancio (optional)
- **Purpose**: Provides the required libraries for writing and executing unit and integration tests
- **Code Change**:
  ```xml
  <!-- Test Dependencies -->
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <scope>test</scope>
  </dependency>  
  <dependency>
    <groupId>org.instancio</groupId>
    <artifactId>instancio-core</artifactId>
    <version>${instancio.version}</version>
    <scope>test</scope>
  </dependency>
  ```
- **Benefit**: Enables the use of JUnit 5 for testing, Mockito for mocking dependencies, AssertJ for fluent assertions,
  and Instancio for generating test data, which helps in writing comprehensive and maintainable tests with less
  boilerplate code.

#### 3. **Add JaCoCo Plugin for Test Coverage**
- **What Changed**: Configured the JaCoCo plugin in `pom.xml` to generate test coverage reports
- **Purpose**: Provides insights into how much of the code is covered by tests, helping to identify untested areas
- **Code Change**:
  ```xml
  <plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>${jacoco.version}</version>
    <executions>
      <execution>
        <goals>
          <goal>prepare-agent</goal>
        </goals>
      </execution>
      <execution>
        <id>report</id>
        <phase>test</phase>
        <goals>
          <goal>report</goal>
        </goals>
      </execution>
    </executions>
  </plugin>
  ```
- **Benefit**: Generates detailed reports on test coverage, allowing developers to ensure that critical parts of the
  codebase are adequately tested and to improve overall code quality.

#### 4. **Add Integration Test Maven Profile**

- **What Changed**: Created a Maven profile for running integration tests separately from unit tests
- **Purpose**: Allows developers to run integration tests independently, which can be more time-consuming than unit
  tests
- **Code Change**:
  ```xml
    <profile>
        <id>itest</id>

        <build>
            <testResources>
                <testResource>
                    <directory>src/integration-test/resources</directory>
                </testResource>
            </testResources>

            <plugins>
                <plugin>
                    <groupId>org.codehaus.mojo</groupId>
                    <artifactId>build-helper-maven-plugin</artifactId>
                    <version>3.6.1</version>
                    <executions>
                        <execution>
                            <id>add-integration-test-sources</id>
                            <phase>generate-test-sources</phase>
                            <goals>
                                <goal>add-test-source</goal>
                            </goals>
                            <configuration>
                                <sources>
                                    <source>src/integration-test/java</source>
                                </sources>
                            </configuration>
                        </execution>
                    </executions>
                </plugin>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <configuration>
                        <includes>
                            <include>**/*IntegrationTest.java</include>
                        </includes>
                        <skipTests>${skipTests}</skipTests>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    </profile>
    ```

- **Benefit**: Provides flexibility in the testing process, allowing developers to focus on unit tests during
  development and run integration tests as needed, improving efficiency and test management.

#### 5. **Integration Test Package Structure**

- **What to place where**: Put integration test Java sources under `src/integration-test/java` and test resources
  (test-specific configuration, test data) under `src/integration-test/resources`.
- **Package layout**: Use the same package root as the main code (recommended) so tests mirror production packages,
  e.g. `src/test/java/io/github/sambrodeur/resource/server/demo/ResourceServerDemoApplicationIntegrationTest.java`
- **Naming & discovery**: Keep the naming convention `*IntegrationTest.java` so the Maven Surefire configuration above
  picks them up (`**/*IntegrationTest.java`).
- **Example structure**:

```
  src/integration-test/java/
  └─ io/github/sambrodeur/resource/server/demo/
     └─ ResourceServerDemoApplicationIntegrationTest.java

  src/integration-test/resources/
  └─ application-itest.yml
```

- **Benefit**: Mirroring the main package structure keeps tests organized and makes it easy to find corresponding
  integration tests for production classes. Tests run with the itest profile will have the test resources on the
  classpath and follow the configured naming conventions.


## License

This project is licensed under the [Apache 2.0 License](LICENSE-2.0.txt).
