create table if not exists public.privilege
(
    id   varchar(255) not null,
    name varchar(255) not null,
    constraint privilege_pkey
        primary key (id)
);