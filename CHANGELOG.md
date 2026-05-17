# Changelog
## Bello Beauty Academy Platform

All notable changes to this project are documented in this file.

---

## [Assignment 13] — May 2026

### Added

**GitHub Actions CI/CD Pipeline: `.github/workflows/`**
- `ci.yml`: two-job GitHub Actions workflow. Job 1 runs all 153 tests on every push to any branch and on every pull request targeting main. Job 2 builds and uploads a release JAR artifact only when code is merged to main. Maven dependency caching is used to speed up pipeline runs. Test reports are uploaded as artifacts on every run.

**Documentation: `docs/`**
- `PROTECTION.md`: explains the branch protection rules configured on the main branch, why each rule matters, and how the pull request workflow enforces code quality and automated testing before any code reaches main.

### Updated

**Documentation**
- `README.md`: updated with CI/CD pipeline section, branch protection explanation, how to run tests locally, how the CI and CD pipelines work, how artifacts are generated, and a link to PROTECTION.md
- `CHANGELOG.md`: this entry

### Branch Protection

Branch protection rules were configured on the main branch:
- Require pull request before merging
- Require at least 1 approval
- Require CI/CD Pipeline status check to pass before merging
- Block direct pushes to main
- Enforce rules for administrators

### GitHub Project Board

A new **Assignment 13** milestone was created to track all work for this assignment.

Five issues were created on the Assignment 13 milestone and linked to Project Board 5:
- #84 Configure branch protection rules for main branch
- #85 Create CI workflow for automated test execution
- #86 Extend CI workflow with CD artifact generation on main
- #87 Write PROTECTION.md explaining branch protection rules
- #88 Update README and CHANGELOG for Assignment 13

All issues are assigned, linked to the Assignment 13 milestone, and moved to Done on the Kanban board.

---

## [Assignment 13] — May 2026

### Added

**CI/CD Pipeline: `.github/workflows/`**
- `ci.yml`: GitHub Actions workflow with two jobs — Build and Test runs all tests on every push to any branch and on every pull request to main; Build Release Artifact runs only on push to main and uploads the release JAR as a downloadable artifact

**Documentation: `docs/`**
- `PROTECTION.md`: explains all branch protection rules configured on main including PR reviews, status checks, and direct push blocking with real-world DevOps justification
- `docs/screenshots/cicd/branch-protection-rules-1.png`: screenshot of branch protection rule configuration part 1
- `docs/screenshots/cicd/branch-protection-rules-2.png`: screenshot of branch protection rule configuration part 2
- `docs/screenshots/cicd/branch-protection-blocked.png`: screenshot of direct push to main being blocked by branch protection
- `docs/screenshots/ci-pipeline-success.png`: screenshot of successful CI pipeline run
- `docs/screenshots/ci-artifact.png`: screenshot of generated JAR artifact

**Test Fixes**
- `tests/api/TestApplication.java`: test-only Spring Boot configuration class in the api package that gives @SpringBootTest the @SpringBootConfiguration it needs
- `tests/api/ApiIntegrationTest.java`: updated to use TestApplication.class instead of BelloBeautyAcademyApplication.class
- `tests/TestSingleton.java`: fixed cloningThrows test to use explicit try/catch block instead of method reference for protected clone() method
- `src/creational_patterns/singleton/DatabaseConnectionManager.java`: changed clone() access modifier from protected to public to allow test access

### Updated

**Documentation**
- `README.md`: updated with CI/CD pipeline section explaining how the CI and CD pipelines work, how to trigger the pipeline, and branch protection information
- `CHANGELOG.md`: this entry

### GitHub Project Board

A new **Assignment 13** milestone was created to track all work for this assignment.

Five issues were created on the Assignment 13 milestone and linked to Project Board 5:
- #84 Configure branch protection rules for main branch
- #85 Create CI workflow for automated test execution
- #86 Extend CI workflow with CD artifact generation on main
- #87 Write PROTECTION.md explaining branch protection rules
- #88 Update README and CHANGELOG for Assignment 13

All issues are assigned, linked to the Assignment 13 milestone, and moved to Done on the Kanban board.

---

## [Assignment 12] — May 2026

### Added

**Custom Exceptions: `src/exceptions/`**
- `StudentNotFoundException.java`: thrown when a student cannot be found by ID
- `CourseNotFoundException.java`: thrown when a course cannot be found by ID
- `EnrollmentNotFoundException.java`: thrown when an enrollment cannot be found by ID
- `DuplicateEnrollmentException.java`: thrown when a student tries to enroll in a course they are already enrolled in
- `CourseNotActiveException.java`: thrown when a student tries to enroll in an inactive course

**Service Layer: `src/services/`**
- `StudentService.java`: handles student registration, retrieval, update, and deletion with duplicate email validation
- `CourseService.java`: handles course creation, retrieval, update, and deactivation with duplicate ID and inactive state validation
- `EnrollmentService.java`: handles enrollment creation, activation, and cancellation with student existence, course activity, and duplicate enrollment validation

**REST API Controllers: `src/api/`**
- `StudentController.java`: exposes StudentService through five HTTP endpoints with proper status codes and exception handling
- `CourseController.java`: exposes CourseService through seven HTTP endpoints including active course and category filtering
- `EnrollmentController.java`: exposes EnrollmentService through six HTTP endpoints including enrollment activation

**Unit Tests: `tests/services/`**
- `StudentServiceTest.java`: 11 JUnit 5 and Mockito tests for StudentService
- `CourseServiceTest.java`: 14 JUnit 5 and Mockito tests for CourseService
- `EnrollmentServiceTest.java`: 15 JUnit 5 and Mockito tests for EnrollmentService

**Integration Tests: `tests/api/`**
- `ApiIntegrationTest.java`: 22 MockMvc integration tests covering all endpoints for Student, Course, and Enrollment including success, not found, conflict, and bad request responses

**API Documentation: `docs/`**
- `openapi.yaml`: OpenAPI 3.0 specification documenting all 18 REST endpoints with request schemas, response schemas, and error responses

### Updated

**Updated Model Classes**
- `src/models/EnrollmentStatus.java`: added `CANCELLED` status value to support enrollment cancellation in the service layer
- `src/models/Enrollment.java`: added `cancel()` state transition method and `cancelledAt` timestamp
- `src/models/Course.java`: added `deactivate()` state transition method

**Build Configuration**
- `pom.xml`: updated with Spring Boot 3.2.5 parent, spring-boot-starter-web, Mockito 5.11.0, spring-boot-starter-test, and springdoc-openapi-starter-webmvc-ui 2.5.0 dependencies
- `src/api/BelloBeautyAcademyApplication.java`: Spring Boot application entry point in the api package with component scan covering api, services, repositories, exceptions, and config packages

**Spring Configuration: `src/config/`**
- `AppConfig.java`: registers in-memory repository implementations as Spring beans using RepositoryFactory, enabling constructor injection into the service layer
- `OpenApiConfig.java`: configures the Swagger UI title, description, and version shown at the top of the API documentation page

**Documentation**
- `README.md`: updated with service layer section, REST API endpoint table, application run instructions, Swagger UI instructions, and screenshots folder in repository structure
- `docs/ARCHITECTURE.md`: updated to version 2.0 with Section 11 added showing Swagger UI screenshots for all three controllers
- `docs/CLASS_DIAGRAM.md`: updated to version 3.0 with Section 4 added showing the full three-layer architecture diagram
- `docs/screenshots/swagger-ui-overview.png`: Swagger UI overview showing all three controllers
- `docs/screenshots/swagger-ui-students-1.png`: Student controller endpoints part 1
- `docs/screenshots/swagger-ui-students-2.png`: Student controller endpoints part 2
- `docs/screenshots/swagger-ui-courses.png`: Course controller endpoints
- `docs/screenshots/swagger-ui-enrollments-1.png`: Enrollment controller endpoints part 1
- `docs/screenshots/swagger-ui-enrollments-2.png`: Enrollment controller endpoints part 2
- `CHANGELOG.md`: this entry

**Spring Boot Configuration**
- `src/config/AppConfig.java`: registers in-memory repository implementations as Spring beans via RepositoryFactory
- `src/config/OpenApiConfig.java`: configures Swagger UI title, description, and version

### Bug Fixes

Two bug issues were created and resolved during development:
- Fix: Add CANCELLED status to EnrollmentStatus enum
- Fix: Add deactivate() to Course and cancel() to Enrollment model classes

### GitHub Project Board

A new **Assignment 12** milestone was created to track all work for this assignment.

Eleven issues were created on the Assignment 12 milestone and linked to Project Board 5:
- #69 Add custom exceptions for service layer
- #70 Implement StudentService with business logic
- #71 Implement CourseService with business logic
- #72 Implement EnrollmentService with business logic
- #73 Write unit tests for service layer
- #74 Implement StudentController REST API
- #75 Implement CourseController REST API
- #76 Implement EnrollmentController REST API
- #77 Write integration tests for REST API
- #78 Add OpenAPI/Swagger documentation
- #79 Update README and CHANGELOG for Assignment 12

All issues are assigned, linked to the Assignment 12 milestone, and moved to Done on the Kanban board.

---

## [Assignment 11] — May 2026

### Added

**Repository Interfaces: `src/repositories/`**
- `Repository.java`: generic repository interface with four CRUD operations using type parameters T and ID
- `StudentRepository.java`: extends Repository with findByEmail() and findActiveStudents()
- `CourseRepository.java`: extends Repository with findActiveCourses() and findByCategory()
- `EnrollmentRepository.java`: extends Repository with findByStudentId(), findByCourseId(), and findByStatus()
- `CertificateRepository.java`: extends Repository with findByStudentId() and findByCertificateNumber()

**In-Memory Implementations: `src/repositories/inmemory/`**
- `InMemoryStudentRepository.java`: HashMap-based implementation of StudentRepository
- `InMemoryCourseRepository.java`: HashMap-based implementation of CourseRepository
- `InMemoryEnrollmentRepository.java`: HashMap-based implementation of EnrollmentRepository
- `InMemoryCertificateRepository.java`: HashMap-based implementation of CertificateRepository

**Stub Implementations**
- `src/repositories/database/DatabaseStudentRepository.java`: stub for a future PostgreSQL-backed implementation
- `src/repositories/filesystem/FileSystemCourseRepository.java`: stub for a future JSON filesystem-backed implementation

**Factory**
- `src/factories/RepositoryFactory.java`: returns the correct repository implementation based on a storage type string (MEMORY, DATABASE, FILESYSTEM)

**Unit Tests: `tests/`**
- `TestInMemoryRepositories.java`: 44 JUnit 5 tests covering save, findById, findAll, delete, all entity-specific query methods, null inputs, empty repository behaviour, and duplicate saves across all four in-memory repositories

### Updated

**Documentation**
- `docs/CLASS_DIAGRAM.md`: updated to version 3.0 with Section 4 added showing the full three-layer architecture diagram including Repository Layer, Service Layer, REST API Layer, dependency flow, and layer responsibility explanations
- `README.md`: updated with repository layer section, updated repository structure tree, and updated documentation table
- `CHANGELOG.md`: this entry

### GitHub Project Board

A new **Assignment 11** milestone was created to track all work for this assignment.

Seven issues were created on the Assignment 11 milestone and linked to Project Board 5:
- #61 Design generic Repository interface
- #62 Add entity-specific repository interfaces
- #63 Implement in-memory repositories with HashMap
- #64 Implement RepositoryFactory for storage abstraction
- #65 Add database and filesystem stub implementations
- #66 Write unit tests for repository layer
- #67 Update README and CHANGELOG for Assignment 11

All issues are assigned, linked to the Assignment 11 milestone, and moved to Done on the Kanban board.

---

## [Assignment 10] — May 2026

### Added

**Source Code: `/src/models`**
- `User.java`: base class with shared identity and authentication attributes for all user roles
- `Student.java`: extends `User` with student-specific attributes and enrollment behaviour
- `Trainer.java`: extends `User` with trainer-specific attributes and session behaviour
- `Administrator.java`: extends `User` with administrator privileges and management methods
- `Course.java`: training course entity with category, capacity, cost, and status management
- `Enrollment.java`: central joining entity between `Student` and `Course` with full status lifecycle
- `Certificate.java`: digital PDF credential entity with unique certificate number and generation metadata
- `CourseCategory.java`: enum defining the four course categories: `LASH`, `BROW`, `NAIL`, `MAKEUP`
- `EnrollmentStatus.java`: enum defining enrollment lifecycle states
- `UserRole.java`: enum defining the three platform roles: `STUDENT`, `TRAINER`, `ADMIN`

**Creational Patterns: `/src/creational_patterns`**
- `simple_factory/UserFactory.java`: centralised factory that creates `Student`, `Trainer`, or `Administrator` instances based on a role string
- `factory_method/NotificationCreator.java`: abstract creator defining the `createNotification()` factory method
- `factory_method/EnrollmentNotificationCreator.java`: concrete creator producing `EnrollmentNotification` instances
- `factory_method/PaymentConfirmedNotificationCreator.java`: concrete creator producing `PaymentConfirmedNotification` instances
- `factory_method/CertificateReadyNotificationCreator.java`: concrete creator producing `CertificateReadyNotification` instances
- `abstract_factory/UIComponentFactory.java`: abstract factory interface for creating UI component families
- `abstract_factory/StandardUIFactory.java`: concrete factory producing standard UI components
- `abstract_factory/AccessibleUIFactory.java`: concrete factory producing accessible high-contrast UI components
- `prototype/CertificateTemplate.java`: cloneable certificate template with deep copy implementation
- `prototype/CertificateTemplateRegistry.java`: registry storing and cloning pre-configured certificate templates
- `singleton/DatabaseConnectionManager.java`: thread-safe singleton managing the PostgreSQL connection pool
- `builder/CourseConfig.java`: fluent builder for constructing `CourseConfig` objects with mandatory field validation

**Unit Tests: `/tests`**
- `TestCoreModels.java`: tests for `Student`, `Course`, `Enrollment`, and `Certificate` model classes
- `TestSimpleFactory.java`: tests for `UserFactory` including all three roles and invalid input handling
- `TestFactoryMethod.java`: tests for all three `NotificationCreator` subclasses and correct notification type dispatch
- `TestAbstractFactory.java`: tests for both `StandardUIFactory` and `AccessibleUIFactory` component families
- `TestBuilder.java`: tests for `CourseConfig.Builder` including mandatory fields, optional defaults, overrides, and edge cases
- `TestPrototype.java`: tests for `CertificateTemplate` deep copy behaviour and `CertificateTemplateRegistry` cloning
- `TestSingleton.java`: tests for `DatabaseConnectionManager` including single instance guarantee, thread safety, and connection pool operations

**Build Configuration**
- `pom.xml`: Maven project setup with JUnit 5 dependency for unit test execution

**Documentation**
- `CHANGELOG.md`: this file
- Updated `README.md` with language choice justification, pattern rationale section, updated repository structure, and instructions for running tests with Maven

### GitHub Project Board

A new **Assignment 10** milestone was created to track all work for this assignment, with a description summarising the implementation of core domain classes, all six creational patterns, and unit tests.

Nine new issues were created on the Assignment 10 milestone and linked to Project Board 5:
- #50 Implement core model classes from Class Diagram
- #51 Implement Simple Factory: UserFactory
- #52 Implement Factory Method: NotificationCreator hierarchy
- #53 Implement Abstract Factory: UIComponentFactory families
- #54 Implement Builder: CourseConfig fluent builder
- #55 Implement Prototype: CertificateTemplate and registry
- #56 Implement Singleton: DatabaseConnectionManager
- #57 Write unit tests for all creational patterns
- #58 Add CHANGELOG.md and update README for Assignment 10

Two bug issues were created and resolved during testing:
- #59 Fix: Remove untestable cloningThrows test from TestSingleton
- #60 Fix: Resolve package name and test sources root configuration

All issues are assigned, linked to the Assignment 10 milestone, and moved to Done on the Kanban board.

---

## Prior Assignments

Changes from Assignments 3 through 9 are documented in the commit history and the corresponding documents in the `/docs` directory.