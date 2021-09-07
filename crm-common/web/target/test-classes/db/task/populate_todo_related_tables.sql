DELETE FROM task;

ALTER SEQUENCE task_seq RESTART WITH 1;

/*
 .id(4L)
                                                        .title("Create something")
                                                        .description("Create some stuff")
                                                        .startFrom(LocalDateTime.now().plusHours(2))
                                                        .deadline(LocalDateTime.now().plusDays(1).plusHours(2))
                                                        .build();
 */

INSERT INTO task(id, title, description, startfrom, deadline) VALUES
    (1, 'Create migration', 'It is necessary to create a brand new migration file', '2021-09-02 10:13:00', '2021-09-03 23:59:59'),
    (2, 'Create repository', 'Create a new repository and a service layer for Person entity', '2021-09-03 11:00:00', '2021-09-04 17:00:00'),
    (3, 'Update details information', 'Update a model and DTO classes for DetailsInformation', '2021-09-05 09:00:00', '2021-09-06 13:00:00'),
    (4, 'Create something', 'Create some stuff', '2200-09-11 12:00:00', '2200-09-12 12:00:00');
ALTER SEQUENCE task_seq RESTART WITH 5;