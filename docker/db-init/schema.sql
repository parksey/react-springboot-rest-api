DROP TABLE IF EXISTS owner;
CREATE TABLE owner (
    owner_id    BIGINT       AUTO_INCREMENT,
    owner_no    VARCHAR(10)  NOT NULL,
    phone       VARCHAR(11)  NOT NULL ,
    email       VARCHAR(50)  NOT NULL ,
    create_at   TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_owner_no UNIQUE(owner_no),
    CONSTRAINT pk_owner_id PRIMARY KEY (owner_id)
);