DELETE FROM contact;

ALTER SEQUENCE contact_seq RESTART WITH 1;


INSERT INTO contact(id, first_name, last_name, job_title, company, country, email, mobile_phone, user_id) VALUES
(1, 'Alex', 'Davidson', 'CEO', 'Harper Corporation', 'USA', 'alex_davidson@gmail.com', '+12027995223', 1),
(2, 'Jack', 'Smith', 'Architecture', 'Peterson & Davids', 'Canada', 'jack_smith@gmail.com', '+14378455569', 2);

ALTER SEQUENCE contact_seq RESTART WITH 3;