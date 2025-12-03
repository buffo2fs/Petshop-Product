# Petshop\-Product

Project: a simple Java / Maven backend that manages product data for a petshop. The application uses Spring (Spring Data / Spring DI) to provide CRUD operations for `Product` entities and a small timing utility to measure operation durations.

Repository: `https://github.com/buffo2fs/Petshop-Product.git` (branch: `main`)

## Technologies
1. Java (JDK 11+ or 17+ recommended)  
2. Maven (build and dependency management)  
3. Spring Framework / Spring Data JPA (dependency injection, repositories, transaction management)  
4. Jakarta Transactions (`jakarta.transaction.Transactional`)  
5. Project layout follows standard Maven layout under `src/main/java`

## What the project does
1. Stores and manages products used in a petshop catalog.  
2. Exposes a service layer that implements create, read (all and by id), update and delete operations for `Product`.  
3. Uses `ProductRepository` (Spring Data) for database access.  
4. Wraps mutating operations in transactions and measures execution time via a `Timer` utility.

## Key files / packages
1. `src/main/java/com/lucas/petshop/model` \- domain model such as `Product`.  
2. `src/main/java/com/lucas/petshop/repository` \- Spring Data repositories (for example `ProductRepository`).  
3. `src/main/java/com/lucas/petshop/service` \- business logic, e.g. `ProductServiceImpl` (CRUD + timing + transactions).  
4. `src/main/java/com/lucas/petshop/util` \- helper classes such as `Timer`.  
5. `src/main/resources/application.properties` or `application.yml` \- data source and Spring configuration (configure a database here).

## How it works (brief)
1. Controllers (if present) receive HTTP requests and call service methods.  
2. `ProductServiceImpl` delegates persistence to `ProductRepository`.  
3. Mutating methods (`create`, `update`, possibly `delete`) are annotated with `@Transactional` to ensure DB consistency.  
4. Each service method records start time and calls `Timer.measure` to log operation duration.

## Build and run (Windows)
1. Build the project:
```bash
mvn clean package
```
2. Run with Maven (if using Spring Boot):
```bash
mvn spring-boot:run
```
3. Or run the generated jar:
```bash
java -jar target\<artifact-name>.jar
```

## Configuration notes
1. Configure database access in `src/main/resources/application.properties` or `application.yml` (JDBC URL, username, password, JPA properties).  
2. Ensure the JDK version matches the project settings in `pom.xml`.

