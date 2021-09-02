CREATE SEQUENCE contact_seq START WITH 1;

CREATE TABLE contact (
    id BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('contact_seq'),
    first_name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128) NOT NULL,
    job_title VARCHAR(256),
    company VARCHAR(256),
    country VARCHAR(128),
    email VARCHAR(128) UNIQUE NOT NULL,
    mobile_phone VARCHAR(56) UNIQUE
);