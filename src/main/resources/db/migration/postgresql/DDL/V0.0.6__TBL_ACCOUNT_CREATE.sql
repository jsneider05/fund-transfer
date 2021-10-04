create table if not exists public.account
(
    id             varchar(255)          not null,
    account_number bigint                not null,
    created_at     timestamp             not null,
    currency       varchar(255)          not null,
    enabled        boolean default false not null,
    total_fund     numeric(19, 4)        not null,
    user_id        varchar(255)          not null,
    constraint account_pkey
        primary key (id),
    constraint account_account_number_unique
        unique (account_number),
    constraint account_user_user_id_fk
        foreign key (user_id) references public."user"
);

