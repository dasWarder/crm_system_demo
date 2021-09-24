DELETE FROM refresh_token;

ALTER SEQUENCE token_seq RESTART WITH 1;

INSERT INTO refresh_token(id, subject, token, expiry_date) VALUES
    (1, 'test@gmail.com', 'c1ac32f3-86ac-418f-8c02-72ab4d41ac2d', '2030-01-01 12:00:00');