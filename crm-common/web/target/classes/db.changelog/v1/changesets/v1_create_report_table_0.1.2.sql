CREATE SEQUENCE rep_seq START WITH 1;

CREATE TABLE report(
    id BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('rep_seq'),
    topic VARCHAR(128) NOT NULL,
    comment VARCHAR(256),
    status VARCHAR(128),
    create_at TIMESTAMP NOT NULL,
    status_changed TIMESTAMP,
    response VARCHAR(256),
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES usr(id)
);