insert into users (id, username, password) values (1 ,'admin', '$2a$10$pWy1aRsO1I70vTLE96xwBunm2OahTJjiaRwDd2IdJzmIZtfJqku8y');
insert into users (id, username, password) values (2 ,'user1', '$2a$10$ehh1c1MQ2OPzK695IJLL7eKEjUgCiVd8hyNmY.vOdm9nn0XYB0R8q');
insert into users (id, username, password) values (3 ,'user2', '$2a$10$JRK311OxFPkLCpkKCLJzJOrcbT.ZHv2fH6zTL21n5gJwGKMZJURhe');

insert into user_roles (user_role, user_id) values ('USER', 2);
insert into user_roles (user_role, user_id) values ('USER', 3);
insert into user_roles (user_role, user_id) values ('ADMIN', 1);

insert into issues (description, issue_name, publish_date, status, author_id) values ('Some JAVA issue with concurrency', 'Java', now(), 'Created', 2);
insert into issues (description, issue_name, publish_date, status, author_id) values ('Some HIBERNATE issue with Lazy init', 'Hibernate', now(), 'Resolved', 3);

insert into issue_comments (description, publish_date, status, issue_id, author) values ('Have no idea', now(), null, 1, 'user2');
insert into issue_comments (description, publish_date, status, issue_id, author) values ('Have an idea', now(), 'Resolved', 2, 'user1');
insert into issue_comments (description, publish_date, status, issue_id, author) values ('Thx', now(), null, 2, 'user1');