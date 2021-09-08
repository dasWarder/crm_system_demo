INSERT INTO usr(id, role_id, email, password, enabled) VALUES
    (1, 100000, 'alex@gmail.com', '12345', true),
    (2, 100001, 'jack@gmail.com', '12345', true),
    (3, 100002, 'mike@gmail.com', '12345', true);

SELECT setval('usr_seq', max(id)) FROM usr;