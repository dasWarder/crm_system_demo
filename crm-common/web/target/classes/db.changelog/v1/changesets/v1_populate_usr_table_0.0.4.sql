INSERT INTO usr(id, role_id, email, password, registration_date, enabled) VALUES
    (1, 100000, 'alex@gmail.com', '$2a$10$cTiwE6/krBErrzuEeL3TZuiqrgSQDi.faOVIYCmYhdPWrLxk2TXyC', '2021-09-01 00:00:00', true),
    (2, 100001, 'jack@gmail.com', '$2a$10$cTiwE6/krBErrzuEeL3TZuiqrgSQDi.faOVIYCmYhdPWrLxk2TXyC', '2021-09-01 00:00:00', true),
    (3, 100002, 'mike@gmail.com', '$2a$10$cTiwE6/krBErrzuEeL3TZuiqrgSQDi.faOVIYCmYhdPWrLxk2TXyC', '2021-09-01 00:00:00', true);

SELECT setval('usr_seq', max(id)) FROM usr;