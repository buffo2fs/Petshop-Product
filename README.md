# Petshop-Product

Simple REST API to manage products for a pet shop built with Spring Boot and Maven.

## Project structure
\- `src/main/java/com/lucas/petshop/` \- application code (controllers, models, repositories, exceptions)  
\- `src/test/java/` \- unit/integration tests  
\- `pom.xml` \- Maven build file

## Tech stack
\- Java (11\+ recommended)  
\- Spring Boot (Web, Data JPA)  
\- Maven  
\- H2 / any JDBC datasource (configurable)

## Features
\- CRUD operations for products via a REST API  
\- Basic exception handling for missing products

## Prerequisites
\- Java JDK 11 or newer  
\- Maven  
\- Windows (development environment)

## Build & run
Build the project:

Default server port: `8080` (configurable in `application.properties`)

## API Endpoints
Base path: `/products`

\- `GET /products`  
  \- Returns list of products (HTTP 200)

\- `GET /products/{productId}`  
  \- Returns product by id (HTTP 200) or not found

\- `POST /products`  
  \- Create a new product (HTTP 201)  
  \- Body: JSON representation of a product

\- `PUT /products/{productId}`  
  \- Update existing product (HTTP 204 on success)  
  \- If product not found, a `ProductNotFoundException` is thrown (mapped to appropriate error)

\- `DELETE /products/{productId}`  
  \- Delete product by id (HTTP 204)

  ## Database modeling

The project uses the following tables to model products, ratings and orders:

- `tb_products` (products)
  - `product_id` BIGSERIAL PRIMARY KEY
  - `name` VARCHAR(255)
  - `type` VARCHAR(30)
  - `animal_type` VARCHAR(30)
  - `brand` VARCHAR(30)
  - `description` TEXT
  - `stock` INT
  - `price` DECIMAL(5,2)
  - `size_weight` DECIMAL(5,2)

- `tb_rating` (product ratings)
  - `rating_id` BIGSERIAL PRIMARY KEY
  - `product_id` BIGINT FOREIGN KEY -> `tb_products(product_id)`
  - `stars` DECIMAL(2,1)
  - `client` VARCHAR(50)
  - `comments` VARCHAR(255)
  - `date_time` TIMESTAMP

  Relationship: one `tb_products` record can have many `tb_rating` records (1:N).

- `tb_orders` (orders)
  - `order_id` BIGSERIAL PRIMARY KEY
  - `quantity` INT
  - `client` VARCHAR(50)
  - `date` TIMESTAMP
  - `status` VARCHAR(20)

- `tb_products_orders` (join table for products and orders)
  - `po_id` BIGSERIAL PRIMARY KEY
  - `product_id` BIGINT FOREIGN KEY -> `tb_products(product_id)`
  - `order_id` BIGINT FOREIGN KEY -> `tb_orders(order_id)`

  Relationship: products and orders are associated via `tb_products_orders` allowing a many-to-many mapping between `tb_products` and `tb_orders`.

Notes and JPA mapping suggestions:
- Map each table to an `@Entity` with `@Table(name = "tb_xxx")`. Use `Long` for BIGSERIAL/BIGINT and `BigDecimal` for DECIMAL columns.
- Model `tb_rating` as a `@OneToMany` on `Product`:
  - `@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)`
- Model orders and products via an explicit join entity `ProductOrder` corresponding to `tb_products_orders` (since it has its own PK):
  - `ProductOrder` contains `@ManyToOne` to `Product` and `@ManyToOne` to `Order`.
- Keep fetch and cascade settings explicit to avoid unexpected loads; prefer `LAZY` for collections.
- Add proper indexes on foreign keys (`product_id`, `order_id`) for performance.


## Example Product JSON
```json
{
  "id": 1,
  "name": "Dog Food",
  "description": "Premium dry food",
  "price": 29.99
}

Error handling
- ProductNotFoundException is used when attempting to update or access a non-existent product. Ensure a global exception handler maps this to HTTP 404.

