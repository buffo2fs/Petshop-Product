CREATE TABLE tb_products (
product_id BIGSERIAL PRIMARY KEY,
name VARCHAR(255) NOT NULL,
type VARCHAR(30) NOT NULL,
animal_type VARCHAR(30),
brand VARCHAR(30),
description TEXT NOT NULL,
stock INTEGER NOT NULL,
price DECIMAL(5,2) NOT NULL,
size_weight DECIMAL(5,2)
);

CREATE TABLE tb_rating (
rating_id BIGSERIAL PRIMARY KEY,
product_id BIGINT NOT NULL,
stars DECIMAL(2,1) NOT NULL,
client VARCHAR(50) NOT NULL,
comments VARCHAR(255),
date_time TIMESTAMP NOT NULL,
CONSTRAINT fk_product_rating FOREIGN KEY (product_id) REFERENCES tb_products (product_id)
);

CREATE TABLE tb_orders (
order_id BIGSERIAL PRIMARY KEY,
quantity INTEGER NOT NULL,
client VARCHAR(50) NOT NULL,
date_time TIMESTAMP NOT NULL,
status  VARCHAR(20) NOT NULL
);

CREATE TABLE tb_products_orders (
po_id BIGSERIAL PRIMARY KEY,
product_id BIGINT NOT NULL,
order_id BIGINT NOT null,
CONSTRAINT fk_product_order FOREIGN KEY (product_id) REFERENCES tb_products (product_id),
CONSTRAINT fk_order_product FOREIGN KEY (order_id) REFERENCES tb_orders (order_id)
);



