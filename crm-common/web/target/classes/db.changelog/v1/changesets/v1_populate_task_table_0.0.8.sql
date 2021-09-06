INSERT INTO task(id, title, description, startfrom, deadline, todo_list_id) VALUES
    (1, 'Create migration', 'It is necessary to create a brand new migration file', '2021-09-10 10:13:00', '2021-11-10 23:59:59', 1),
    (2, 'Create repository', 'Create a new repository and a service layer for Person entity', '2021-09-11 11:00:00', '2021-09-11 17:00:00', 1),
    (3, 'Update details information', 'Update a model and DTO classes for DetailsInformation', '2021-09-12 09:00:00', '2021-09-12 13:00:00', 1);

SELECT setval('task_seq', max(id)) FROM task;