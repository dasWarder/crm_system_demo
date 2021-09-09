CREATE SEQUENCE token_seq START WITH 1;

CREATE TABLE refresh_token (
    id BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('token_seq'),
    subject VARCHAR(256) NOT NULL,
    token VARCHAR(256) NOT NULL,
    expiry_date TIMESTAMP NOT NULL
);