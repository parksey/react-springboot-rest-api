DROP TABLE IF EXISTS owner;
CREATE TABLE owner
(
    owner_id    BIGINT       AUTO_INCREMENT,
    owner_no    VARCHAR(10)  NOT NULL,
    phone       VARCHAR(11)  NOT NULL ,
    email       VARCHAR(50)  NOT NULL ,
    create_at   TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_owner_no UNIQUE(owner_no),
    CONSTRAINT pk_owner_id PRIMARY KEY (owner_id)
);

INSERT INTO owner (owner_no, phone, email)
VALUES
    ('1234568790', '01011112222', 'psy@naver.com'),
    ('4567890123', '01014725836', 'psy2@naver.com');

DROP TABLE IF EXISTS shared_space;
CREATE TABLE shared_space
(
    space_id    BIGINT      AUTO_INCREMENT,
    title       VARCHAR(50) NOT NULL,
    description TEXT,
    location    VARCHAR(100) NOT NULL,
    capacity    INTEGER      NOT NULL,
    amount      BIGINT       NOT NULL,
    start_at    TIMESTAMP    NOT NULL,
    end_at      TIMESTAMP    NOT NULL,
    owner_id    BIGINT       NOT NULL,

    PRIMARY KEY (space_id)
);

INSERT INTO shared_space (title, description, location, capacity, amount, start_at, end_at, owner_id)
VALUES
    ('title1', '설명1', '서울특별시', 5, 29000, '2023-07-23 13:00', '2023-07-25 18:00', 1),
    ('제목', '설명2', '인천', 90, 10000, '2023-07-23 13:00', '2023-07-25 18:00', 2);


DROP TABLE IF EXISTS customer;
CREATE TABLE customer
(
    customer_id BIGINT       AUTO_INCREMENT,
    email       VARCHAR(50)  NOT NULL,
    name        VARCHAR(50)  NOT NULL,
    password    VARCHAR(255) NOT NULL,
    phone       VARCHAR(11)  NOT NULL,
    create_at   TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (customer_id)
);

INSERT INTO customer(email, name, password, phone)
VALUES
    ('psy@naver.com', 'psy', '1234', '01054555555'),
    ('psy2@naver.com', 'psy2', '5678', '01099998888');