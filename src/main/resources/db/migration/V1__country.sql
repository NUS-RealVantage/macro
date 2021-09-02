CREATE table countries
(
    id      BIGSERIAL  NOT NULL PRIMARY KEY,
    name    VARCHAR(100)  NOT NULL,
    iso_code    VARCHAR(3)  NOT NULL
);