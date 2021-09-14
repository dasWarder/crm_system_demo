INSERT INTO authority VALUES
    (100000, 'USER'),
    (100001, 'MANAGER'),
    (100002, 'SUPER_ADMIN');

SELECT setval('auth_seq', max(id)) FROM authority;