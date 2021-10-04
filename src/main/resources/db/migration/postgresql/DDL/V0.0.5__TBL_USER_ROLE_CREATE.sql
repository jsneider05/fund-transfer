create table if not exists public.user_role
(
    user_id varchar(255) not null,
    role_id varchar(255) not null,
    constraint user_role_pkey
        primary key (user_id, role_id),
    constraint user_role_role_id_fk
        foreign key (role_id) references public.role,
    constraint user_role_user_id_fk
        foreign key (user_id) references public."user"
);

