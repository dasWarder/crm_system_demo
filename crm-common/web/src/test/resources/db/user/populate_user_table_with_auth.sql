DELETE FROM usr;
DELETE FROM authority;

ALTER SEQUENCE usr_seq RESTART WITH 1;
ALTER SEQUENCE auth_seq RESTART WITH 100000;

INSERT INTO authority(id, authority) VALUES
(100000, 'USER'),
(100001, 'ADMIN');

ALTER SEQUENCE auth_seq RESTART WITH 100002;

INSERT INTO usr(id, email, password, registration_date, enabled, role_id) VALUES
(1, 'test@gmail.com', '$2a$10$cTiwE6/krBErrzuEeL3TZuiqrgSQDi.faOVIYCmYhdPWrLxk2TXyC', '2021-01-01', true, 100000),
(2, 'test2@gmail.com', '$2a$10$cTiwE6/krBErrzuEeL3TZuiqrgSQDi.faOVIYCmYhdPWrLxk2TXyC', '2021-01-01',true, 100001);

ALTER SEQUENCE usr_seq RESTART WITH 3;