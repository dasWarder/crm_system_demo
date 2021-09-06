CREATE TABLE todo_list(
    user_id BIGINT NOT NULL PRIMARY KEY,
    FOREIGN KEY (user_id) REFERENCES usr(id)
);