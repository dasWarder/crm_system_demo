INSERT INTO report(id, topic, status, create_at, user_id) VALUES
    (1, 'VACATION', 'RECEIVED', '2021-09-05 13:44:00', 1),
    (2, 'LEAVE', 'RECEIVED', '2021-09-06 17:01:00', 1);
INSERT INTO report(id, topic, comment, status, create_at, status_changed, user_id) VALUES
    (3, 'UNPAID', 'Have 1 day leave due to domestic troubles', 'APPROVED', '2021-09-07 12:00:00', '2021-09-08 13:47:00', 1);

SELECT setval('rep_seq', max(id)) FROM report;