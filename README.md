Petshop-Product
Project: a simple Java / Maven backend that manages product data for a petshop. The application uses Spring (Spring Data / Spring DI) to provide CRUD operations for Product entities and a small timing utility to measure operation durations.
Repository: https://github.com/buffo2fs/Petshop-Product.git (branch: main)
Technologies
Java (JDK 11+ or 17+ recommended)
Maven (build and dependency management)
Spring Framework / Spring Data JPA (dependency injection, repositories, transaction management)
Jakarta Transactions (jakarta.transaction.Transactional)
Project layout follows standard Maven layout under src/main/java
What the project does
Stores and manages products used in a petshop catalog.
Exposes a service layer that implements create, read (all and by id), update and delete operations for Product.
Uses ProductRepository (Spring Data) for database access.
Wraps mutating operations in transactions and measures execution time via a Timer utility.
Key files / packages
src/main/java/com/lucas/petshop/model - domain model such as Product.
src/main/java/com/lucas/petshop/repository - Spring Data repositories (for example ProductRepository).
src/main/java/com/lucas/petshop/service - business logic, e.g. ProductServiceImpl (CRUD + timing + transactions).
src/main/java/com/lucas/petshop/util - helper classes such as Timer.
src/main/resources/application.properties or application.yml - data source and Spring configuration (configure a database here).
How it works (brief)
Controllers (if present) receive HTTP requests and call service methods.
ProductServiceImpl delegates persistence to ProductRepository.
Mutating methods (create, update, possibly delete) are annotated with @Transactional to ensure DB consistency.
Each service method records start time and calls Timer.measure to log operation duration.
Build and run (Windows)
Build the project:
mvn clean package
Run with Maven (if using Spring Boot):
mvn spring-boot:run
Or run the generated jar:
java -jar target\<artifact-name>.jar
Configuration notes
Configure database access in src/main/resources/application.properties or application.yml (JDBC URL, username, password, JPA properties).
Ensure the JDK version matches the project settings in pom.xml.
