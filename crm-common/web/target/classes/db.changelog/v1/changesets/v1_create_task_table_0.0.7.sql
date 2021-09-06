/*
     Long id;
     String title;
     String description;
     LocalDateTime startFrom;
     LocalDateTime deadline;
    @JoinColumn(name = "todo_list_id")
     TodoList todoList;
 */

CREATE SEQUENCE task_seq START WITH 1;

CREATE TABLE task(
    id BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('task_seq'),
    title VARCHAR(128) NOT NULL,
    description VARCHAR(256),
    startFrom TIMESTAMP NOT NULL,
    deadline TIMESTAMP,
    todo_list_id BIGINT NOT NULL,
    FOREIGN KEY (todo_list_id) REFERENCES todo_list(user_id)
);