DELETE FROM report;

ALTER SEQUENCE rep_seq RESTART WITH 1;

INSERT INTO report(id, topic, comment, status, create_at, status_changed, response, user_id) VALUES
    (1, 'VACATION', null, 'RECEIVED', '2021-09-05 13:44:00.000000', '2021-09-05 13:44:00.000000', null, 1),
    (2, 'LEAVE', null, 'RECEIVED', '2021-09-06 17:01:00.000000', '2021-09-05 13:44:00.000000', null, 1),
    (3, 'UNPAID', 'Have 1 day leave due to domestic troubles', 'APPROVED', '2021-09-07 12:00:00', '2021-09-08 13:47:00', null, 1);

ALTER SEQUENCE rep_seq RESTART WITH 4;