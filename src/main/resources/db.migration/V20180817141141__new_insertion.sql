insert into issues (description, issue_name, publish_date, status, author_id) values ('I`m done with React', 'React', now(), 'Created', 2);
insert into issues (description, issue_name, publish_date, status, author_id) values ('Hate my life cause of JWT', 'JWT', now(), 'Resolved', 3);

insert into issue_comments (description, publish_date, status, issue_id, author) values ('Same stuff', now(), null, 3, 'user2');
insert into issue_comments (description, publish_date, status, issue_id, author) values ('I made it', now(), 'Resolved', 4, 'user1');
insert into issue_comments (description, publish_date, status, issue_id, author) values ('Thx', now(), null, 4, 'user1');