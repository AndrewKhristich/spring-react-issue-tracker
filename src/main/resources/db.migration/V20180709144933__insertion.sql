insert into users (username, password) values ('admin', '$2a$10$pWy1aRsO1I70vTLE96xwBunm2OahTJjiaRwDd2IdJzmIZtfJqku8y');
insert into users (username, password) values ('user1', '$2a$10$ehh1c1MQ2OPzK695IJLL7eKEjUgCiVd8hyNmY.vOdm9nn0XYB0R8q');
insert into users (username, password) values ('user2', '$2a$10$JRK311OxFPkLCpkKCLJzJOrcbT.ZHv2fH6zTL21n5gJwGKMZJURhe');

insert into user_roles (user_role, username) values ('USER', 'user1');
insert into user_roles (user_role, username) values ('USER', 'user2');
insert into user_roles (user_role, username) values ('ADMIN', 'admin');

insert into issues (description, issue_name, publish_date, status, author) values ('Some JAVA issue with concurrency', 'Java', now(), 'Created', 'user1');
insert into issues (description, issue_name, publish_date, status, author) values ('Some HIBERNATE issue with Lazy init', 'Hibernate', now(), 'Resolved', 'user2');

insert into issue_comments (description, publish_date, status, issue_id, author) values ('Have no idea', now(), null, 1, 'user2');
insert into issue_comments (description, publish_date, status, issue_id, author) values ('Have an idea', now(), 'Resolved', 2, 'user1');
insert into issue_comments (description, publish_date, status, issue_id, author) values ('Thx', now(), null, 2, 'user1');