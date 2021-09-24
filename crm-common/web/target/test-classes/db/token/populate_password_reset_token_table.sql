DELETE FROM password_reset_token;

ALTER SEQUENCE pass_res_seq RESTART WITH 1;

INSERT INTO password_reset_token(id, token, expiry_date, user_id) VALUES
    (1, 'c1ac32f3-86ac-418f-8c02-72ab4d41ac2d', '2030-01-01 12:00:00', 1);

ALTER SEQUENCE pass_res_seq RESTART WITH 2;