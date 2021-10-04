create table if not exists public.transfer
(
    id                         varchar(255)          not null,
    amount                     numeric(19, 4)        not null,
    conversion_rate            numeric(19, 18)       not null,
    created_at                 timestamp             not null,
    currency                   varchar(255)          not null,
    description                varchar(100)          not null,
    destination_account_number bigint                not null,
    origin_account_number      bigint                not null,
    successful                 boolean default false not null,
    tax_rate                   numeric(19, 18)       not null,
    constraint transfer_pkey
        primary key (id),
    constraint transfer_account_account_number_fk
        foreign key (origin_account_number) references public.account (account_number)
);

