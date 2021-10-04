create table if not exists public.role
(
    id   varchar(255) not null,
    name varchar(255) not null,
    constraint role_pkey
        primary key (id)
);
