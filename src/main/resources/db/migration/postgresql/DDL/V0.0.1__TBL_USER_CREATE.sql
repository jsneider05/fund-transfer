create table if not exists public."user"
(
    id        varchar(255) not null,
    email     varchar(255) not null,
    enabled   boolean      not null,
    password  varchar(255) not null,
    user_name varchar(255) not null,
    constraint user_pkey
        primary key (id),
    constraint user_user_name_unique
        unique (user_name),
    constraint user_email_unique
        unique (email)
);