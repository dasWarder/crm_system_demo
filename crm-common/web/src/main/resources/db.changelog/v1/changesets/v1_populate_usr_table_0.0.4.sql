INSERT INTO usr(id, email, password, enabled) VALUES
    (1, 'alex@gmail.com', '12345', true),
    (2, 'jack@gmail.com', '12345', true),
    (3, 'mike@gmail.com', '12345', true);

SELECT setval('usr_seq', max(id)) FROM usr;