CREATE TABLE param_dictionary (
    id          BIGINT PRIMARY KEY,
    code        VARCHAR(50)  NOT NULL,
    label       VARCHAR(100) NOT NULL
);

CREATE TABLE products (
    id          BIGINT PRIMARY KEY,
    code        VARCHAR(32) NOT NULL,
    name        VARCHAR(255) NOT NULL,
    price       DECIMAL(10,2) NOT NULL,
    quantity    INTEGER NOT NULL
);

CREATE TABLE product_params (
    id          BIGINT PRIMARY KEY,
    product_id  BIGINT NOT NULL,
    param_id    BIGINT NOT NULL,
    param_value VARCHAR(255) NOT NULL,

    CONSTRAINT fk_product
        FOREIGN KEY (product_id) REFERENCES products(id),
    CONSTRAINT fk_param
        FOREIGN KEY (param_id) REFERENCES param_dictionary(id)
);

