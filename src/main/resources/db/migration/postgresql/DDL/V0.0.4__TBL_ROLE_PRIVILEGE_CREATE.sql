create table if not exists public.role_privilege
(
    role_id      varchar(255) not null,
    privilege_id varchar(255) not null,
    constraint role_privilege_pkey
        primary key (role_id, privilege_id),
    constraint role_privilege_privilege_id_fk
        foreign key (privilege_id) references public.privilege,
    constraint role_privilege_role_id_fk
        foreign key (role_id) references public.role
);