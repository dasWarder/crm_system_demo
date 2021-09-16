
CREATE SEQUENCE usr_seq START WITH 1;

CREATE TABLE usr (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('usr_seq'),
    role_id BIGINT NOT NULL,
    email VARCHAR(128) NOT NULL UNIQUE,
    password varchar(128) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    registration_date TIMESTAMP NOT NULL,
    FOREIGN KEY (role_id) REFERENCES authority(id)
);