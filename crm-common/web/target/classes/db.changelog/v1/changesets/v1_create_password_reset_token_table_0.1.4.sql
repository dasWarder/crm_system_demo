CREATE SEQUENCE pass_res_seq START WITH 1;

CREATE TABLE password_reset_token(
    id BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('pass_res_seq'),
    token VARCHAR(120) NOT NULL UNIQUE,
    expiry_date TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES usr(id)
);