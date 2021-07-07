CREATE table countries_macros
(
    id      BIGSERIAL  NOT NULL PRIMARY KEY,
    real_interest_rate    DECIMAL(16,14),
    deposit_interest_rate    DECIMAL(16,14),
    lending_interest_rate    DECIMAL(16,14),
    gdp   DECIMAL(30,2),
    cpi   DECIMAL(10,4),
    wages DECIMAL(15,4),
    population BIGINT,
    unemployment DECIMAL(4,2),
    fdi DECIMAL(25,3),
    year VARCHAR(10),
    country_id BIGSERIAL,
    CONSTRAINT fk_country FOREIGN KEY (country_id) REFERENCES COUNTRIES(id)
);