create table users
(
  id                      bigint constraint users_pkey
  primary key,
  username                varchar(20) not null unique,
  non_expired             boolean not null default true,
  non_locked              boolean not null default true,
  credentials_non_expired boolean not null default true,
  enabled                 boolean not null default true,
  password                varchar(255) not null
);

create table user_roles
(
  id        serial       not null
    constraint user_roles_pkey
    primary key,
  user_role varchar(255) not null,
  username  varchar(255) not null
    constraint fkcdp2dxqcsdh6rnh6o64rgtcir
    references users
);

create table issues
(
  id           bigserial not null
    constraint issues_pkey
    primary key,
  description  varchar(255),
  issue_name   varchar(255),
  publish_date timestamp,
  status       varchar(255),
  author       varchar(255)
    constraint fk4ntdml4qaeo21o666tm756cbf
    references users
);

create table issue_comments
(
  id           bigserial not null
    constraint issue_comments_pkey
    primary key,
  description  varchar(255),
  publish_date timestamp,
  status       varchar(255),
  author       varchar (20),
  issue_id     bigint
    constraint fkh6kukf0ta5nqx9m1vwsyvy6ja
    references issues
);