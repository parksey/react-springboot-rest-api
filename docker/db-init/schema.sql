DROP TABLE IF EXISTS products;
CREATE TABLE products (
                          product_id BINARY(16) PRIMARY KEY,
                          product_name VARCHAR(20) NOT NULL,
                          category VARCHAR(50) NOT NULL,
                          price BIGINT NOT NULL,
                          description VARCHAR(500) DEFAULT NULL,
                          create_at DATETIME(6) NOT NULL,
                          update_at DATETIME(6) DEFAULT NULL
);

DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
                        order_id BINARY(16),
                        email VARCHAR(50) NOT NULL,
                        address VARCHAR(200) NOT NULL,
                        postcode VARCHAR(200) NOT NULL,
                        order_status VARCHAR(50) NOT NULL,
                        create_at  DATETIME(6) NOT NULL,
                        update_at DATETIME(6) DEFAULT NULL,
                        PRIMARY KEY (order_id)
);

DROP TABLE IF EXISTS order_items;
CREATE TABLE order_items
(
    seq BIGINT  NOT NULL AUTO_INCREMENT,
    order_id    BINARY(16) NOT NULL,
    product_id  BINARY(16) NOT NULL,
    category    VARCHAR(50) NOT NULL,
    price       BIGINT      NOT NULL,
    quantity    INT         NOT NULL,
    create_at   DATETIME(6) NOT NULL,
    update_at   DATETIME(6) DEFAULT NULL,
    PRIMARY KEY (seq),
    INDEX(order_id),
    CONSTRAINT fk_order_items_to_order FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    CONSTRAINT fk_order_items_to_product FOREIGN KEY (product_id) REFERENCES products(product_id)
);


INSERT INTO products(product_id, product_name, category, price, description, create_at, update_at)
values (
        UUID_TO_BIN('79694c72-13e6-48bd-8fb2-08fec5425470'),
        'coffee',
        'COFFEE_BEAN',
        1000,
        'flavor',
        CURRENT_TIMESTAMP(6),
        CURRENT_TIMESTAMP(6));