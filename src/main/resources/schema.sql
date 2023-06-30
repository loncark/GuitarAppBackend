DROP TABLE IF EXISTS guitar;
CREATE TABLE guitar
(
    id    INT AUTO_INCREMENT,
    code  VARCHAR(100)   NOT NULL UNIQUE,
    name  VARCHAR(100)   NOT NULL,
    body  VARCHAR(100)   NOT NULL,
    neck  VARCHAR(100)   NOT NULL,
    stock INT            NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
);

