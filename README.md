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

### Step 2 - Implementing Unit Tests with Mocking and Test Data Generation

#### 1. **Overview**
Step 2 focuses on implementing comprehensive unit tests using established testing patterns. This step demonstrates how to:
- Use **Mockito** for mocking dependencies
- Use **Instancio** for generating realistic test data
- Implement tests across all application layers (DAO, Service, Controller)
- Organize tests using base test classes for shared test data
- Follow the **AAA pattern** (Arrange, Act, Assert)

#### 2. **Base Test Classes for Shared Test Data**

- **What Changed**: Created abstract base test classes (`*Test.java`) that provide common test data generation methods
- **Purpose**: Centralizes test data creation and eliminates duplication across related tests
- **Example**: `CompanyDAOImplTest`, `CompanyServiceImplTest`, `CompanyControllerTest`
- **Code Pattern**:
  ```java
  abstract class CompanyDAOImplTest {
    Company getCompany() {
      return Instancio.of(Company.class).create();
    }
  }
  ```
- **Benefit**: Tests extend these base classes to inherit data generation methods, ensuring consistency and reducing boilerplate. Each test file focuses on testing a single method.

#### 3. **Instancio for Test Data Generation**

- **What Changed**: Replaced manual test data creation with Instancio's `Instancio.of(Class.class).create()`
- **Purpose**: Automatically generates realistic, random test data for complex objects
- **Example Usage**:
  ```java
  Company company = Instancio.of(Company.class).create();
  CRUDCompanyModel model = Instancio.of(CRUDCompanyModel.class).create();
  ```
- **Benefit**: Reduces boilerplate code, generates realistic data with valid defaults, and makes tests more maintainable when model fields change.

#### 4. **Mockito Extension for Dependency Injection**

- **What Changed**: Used `@ExtendWith(MockitoExtension.class)` annotation to enable Mockito's JUnit 5 integration
- **Purpose**: Automatically initializes mocks and injected dependencies at the start of each test
- **Code Pattern**:
  ```java
  @ExtendWith(MockitoExtension.class)
  class FindByIdTest extends CompanyDAOImplTest {
    
    @InjectMocks
    private CompanyDAOImpl companyDAO;
    
    @Mock
    private JdbcClient jdbcClient;
  }
  ```
- **Benefit**: Simplifies test setup by automatically creating mock objects and injecting them into the class under test using `@InjectMocks`.

#### 5. **Layered Testing Strategy**

Tests are organized into three layers, each testing a specific responsibility:

##### **DAO Layer Tests** (Data Access Objects)
- **Location**: `src/test/java/io/github/sambrodeur/resource/server/demo/company/dao/`
- **Focus**: Tests database operations (CRUD operations)
- **Mocking**: Mocks `JdbcClient`, `ResultSet`, and JDBC-related objects
- **Example Methods**: `FindByIdTest`, `SaveTest`, `UpdateTest`, `DeleteByIdTest`, `FindAllTest`
- **Pattern**: Mocks the entire JDBC chain (`statementSpec`, `mapQuerySpec`) to verify SQL operations
- **Benefit**: Verifies data persistence logic without requiring a real database

##### **Service Layer Tests** (Business Logic)
- **Location**: `src/test/java/io/github/sambrodeur/resource/server/demo/company/service/`
- **Focus**: Tests business logic and orchestration
- **Mocking**: Mocks the DAO layer to isolate service logic
- **Example Methods**: `CreateCompanyTest`, `GetCompanyByIdTest`, `UpdateCompanyTest`, `DeleteCompanyTest`
- **Pattern**: Mocks dependencies and verifies service methods call the correct DAO methods with expected parameters
- **Benefit**: Tests business rules in isolation from data access and external dependencies

##### **Controller/REST API Layer Tests** (Request Handling)
- **Location**: `src/test/java/io/github/sambrodeur/resource/server/demo/rest/api/company/controller/`
- **Focus**: Tests REST endpoint handling and request/response mapping
- **Mocking**: Mocks service and mapper layers
- **Example Methods**: `CreateCompanyTest`, `GetAllCompaniesTest`, `UpdateCompanyTest`, `DeleteCompanyTest`
- **Pattern**: Mocks the service and mapper dependencies to verify controllers handle requests correctly
- **Benefit**: Verifies REST endpoints without invoking actual services or databases

#### 6. **Test Organization by Method**

- **What Changed**: Each public method in a class has a dedicated test file (e.g., `FindByIdTest.java`, `SaveTest.java`)
- **Purpose**: Follows Single Responsibility Principle, making tests focused and easy to understand
- **Structure**: One test class per method, with one or more test cases within it
- **Naming Convention**: `<MethodName>Test.java` (e.g., `FindByIdTest.java` for `findById()` method)
- **Benefit**: Makes it easy to locate tests for specific methods and keeps test files maintainable and readable.

#### 7. **Comprehensive API Testing**

Tests cover all CRUD operations and special queries:

**Company Management:**
- DAO: Create, Read, Update, Delete, FindAll, FindByCompanyType
- Service: CreateCompany, GetCompanyById, GetAllCompanies, UpdateCompany, DeleteCompany, GetCompaniesByType
- Controller: CreateCompany, GetCompanyById, GetAllCompanies, UpdateCompany, DeleteCompany, GetCompaniesByType

**Contact Management:**
- DAO: Create, Read, Update, Delete, FindAll, FindByCompanyId
- Service: CreateContact, GetContactById, GetAllContacts, UpdateContact, DeleteContact, GetContactsByCompanyId
- Controller: CreateContact, GetContactById, GetAllContacts, UpdateContact, DeleteContact

**Mappers:**
- ContactModelMapper: Maps between entity and model representations
- CompanyModelMapper: Maps between entity and model representations

#### 8. **AAA Pattern (Arrange, Act, Assert)**

All tests follow this pattern:
```java
@BeforeEach
void setUp() {
  // Arrange: Set up test data and mock expectations
  Company company = getCompany();
  when(companyService.getCompanyById(1)).thenReturn(company);
}

@Test
void getCompanyByIdSuccess() {
  // Act: Execute the method under test
  Company company = companyController.getCompanyById(1);
  
  // Assert: Verify the expected outcome
  assertNotNull(company);
}
```

- **Arrange**: Create test data using Instancio and configure mocks in `@BeforeEach`
- **Act**: Call the method under test
- **Assert**: Verify the result using JUnit assertions
- **Benefit**: Makes test structure clear and intentions explicit

#### 9. **Test Coverage and Verification**

- **What Changed**: JaCoCo generates test coverage reports after each test run
- **How to Run**: 
  ```bash
  mvn clean test
  ```
  Coverage reports are generated in `target/site/jacoco/index.html`
- **Purpose**: Identifies untested code paths and ensures critical functionality is covered
- **Benefit**: Provides visibility into code quality and test completeness

#### 10. **Running Unit Tests**

```bash
mvn clean test
```

```bash
mvn clean install -DskipTests
```

## License

This project is licensed under the [Apache 2.0 License](LICENSE-2.0.txt).
