create table accounts
(
    id             bigserial primary key,
    client_id      bigint,
    account_number varchar(16),
    balance        numeric(6, 2),
    created_at     timestamp,
    updated_at     timestamp
);

create table clients
(
    id  bigserial primary key,
    fio varchar(255)
);

create table transfers
(
    id  bigserial       primary key,
    sum             numeric(6, 2),
    transfer_status     varchar(16),
    source_client_id    bigserial,
    source_account_id   bigserial,
    target_client_id    bigserial,
    target_account_id   bigserial,
    create_date         timestamp,
    update_date         timestamp
);

insert into clients (fio)
values ('A A A');

insert into accounts (client_id, account_number, balance)
values (1, '1234123412341234', 1000), (1, '99999999', 1000);
