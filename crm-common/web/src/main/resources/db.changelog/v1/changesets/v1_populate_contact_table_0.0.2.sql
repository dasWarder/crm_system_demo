INSERT INTO contact(id, first_name, last_name, job_title, company, country, email, mobile_phone) VALUES
    (1, 'Alex', 'Davidson', 'CEO', 'Harper Corporation', 'USA', 'alex_davidson@gmail.com', '+12027995223'),
    (2, 'Jack', 'Smith', 'Architecture', 'Peterson & Davids', 'Canada', 'jack_smith@gmail.com', '+14378455569'),
    (3, 'Maria', 'Smirnova', 'Lead Designer', 'ReversePyramid LTD', 'UK', 'maria_smirnova@gmail.com', '+447911123456'),
    (4, 'Martin', 'Laanpere', 'Software Engineer', 'TLU', 'Estonia', 'martin_laanpere@gmail.com', '+3724444444'),
    (5, 'Viktor', 'Volochay', 'Product manager', 'Wildmap', 'Ukraine', 'viktor_volochay@gmail.com', '+3805543321');

SELECT setval('contact_seq', max(id)) FROM contact;