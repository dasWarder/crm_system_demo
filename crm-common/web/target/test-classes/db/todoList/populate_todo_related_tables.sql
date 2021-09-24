DELETE FROM task;
DELETE FROM todo_list;
DELETE FROM password_reset_token;
DELETE FROM usr;
DELETE FROM authority;

ALTER SEQUENCE task_seq RESTART WITH 1;
ALTER SEQUENCE pass_res_seq RESTART WITH 1;
ALTER SEQUENCE usr_seq RESTART WITH 1;
ALTER SEQUENCE auth_seq RESTART WITH 100000;

INSERT INTO authority(id, authority) VALUES
    (100000, 'USER'),
    (100001, 'ADMIN');

ALTER SEQUENCE auth_seq RESTART WITH 100002;

INSERT INTO usr(id, email, password, enabled) VALUES
    (1, 'test@gmail.com', '12345', true),
    (2, 'test2@gmail.com', '12345', true);

ALTER SEQUENCE usr_seq RESTART WITH 3;

INSERT INTO todo_list(user_id) VALUES
    (1);

INSERT INTO task(id, title, description, startfrom, deadline, todo_list_id) VALUES
    (1, 'Create migration', 'It is necessary to create a brand new migration file', '2021-09-02 10:13:00', '2021-09-03 23:59:59', 1),
    (2, 'Create repository', 'Create a new repository and a service layer for Person entity', '2021-09-03 11:00:00', '2021-09-04 17:00:00', 1),
    (3, 'Update details information', 'Update a model and DTO classes for DetailsInformation', '2021-09-05 09:00:00', '2021-09-06 13:00:00', 1),
    (4, 'Create something', 'Create some stuff', '2200-09-11 12:00:00', '2200-09-12 12:00:00', 1);

ALTER SEQUENCE task_seq RESTART WITH 5;