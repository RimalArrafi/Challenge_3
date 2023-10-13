-- Database: java_challenge_3

-- DROP DATABASE IF EXISTS java_challenge_3;

CREATE DATABASE java_challenge_3
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_Indonesia.1252'
    LC_CTYPE = 'English_Indonesia.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
SELECT uuid_generate_v1();

CREATE TABLE users(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	username VARCHAR(20),
	email_address VARCHAR(320),
	password VARCHAR(20)
);

CREATE TABLE merchants(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	merchant_name VARCHAR(100),
	merchant_location TEXT,
	open BOOLEAN
);

CREATE TABLE products(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	product_name VARCHAR(100),
	price DECIMAL,
	merchant_id UUID NOT NULL,
	CONSTRAINT fk_merchant FOREIGN KEY(merchant_id) REFERENCES merchants(id)
);

CREATE TABLE orders(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	order_time TIMESTAMP DEFAULT now(),
	destination_address TEXT,
	completed BOOLEAN,
	user_id UUID NOT NULL,
	CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE order_details(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	order_id UUID,
	CONSTRAINT fk_order FOREIGN KEY(order_id) REFERENCES orders(id),
	product_id UUID,
	CONSTRAINT fk_product FOREIGN KEY(product_id) REFERENCES products(id),
	quantity INT,
	total_price DECIMAL
);

-- TESTING INSERT QUERIES
INSERT INTO users (username, email_address, password)
VALUES ('user-1', 'user1@gmail.com', 'admin');

INSERT INTO merchants (merchant_name, merchant_location, open)
VALUES ('store-1', 'Bekasi, Jawa Barat', true);

INSERT INTO products (product_name, price, merchant_id)
VALUES ('produk-1', 200, '0fe92566-ba2a-4ff8-bacd-7b86dd2e2d65');

INSERT INTO orders (destination_address, completed, user_id)
VALUES ('Surabaya, Jawa Timur', true, '67686666-09f1-449c-a116-f247bbf33cc2');

INSERT INTO order_details (order_id, product_id, quantity, total_price)
VALUES ('6ec000e3-cb73-4c38-a080-d41fd822e84f', 'a3c9445b-24c3-4c14-ab62-91b0cdc10647', 2, 400);

-- DISPLAY DATA 
SELECT * FROM users;
SELECT * FROM merchants;
SELECT * FROM products;
SELECT * FROM orders;
SELECT * FROM order_details;