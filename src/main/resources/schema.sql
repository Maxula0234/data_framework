create TABLE clients (
ID serial primary key
, first_name VARCHAR(60) NOT NULL
, last_name VARCHAR(40) NOT NULL
, third_name VARCHAR(40) NOT NULL
, phone_number VARCHAR(40) NOT NULL
, email VARCHAR(40) NOT NULL
, date_birth DATE
, reserve BOOLEAN NOT NULL DEFAULT false
);

create table if not exists cards (
ID serial primary key,
pan VARCHAR(60) NOT NULL,
owner VARCHAR(40) NOT NULL,
date_issued VARCHAR(40) NOT NULL,
card_product VARCHAR(40) NOT NULL,
reserve BOOLEAN NOT NULL DEFAULT false
);

create table if not exists accounts (
ID serial primary key,
number VARCHAR(60) NOT NULL,
owner VARCHAR(40) NOT NULL,
owner_id bigint NOT NULL,
reserved boolean NOT NULL,
amount numeric NOT NULL,
date_create VARCHAR(40) NOT NULL,
account_product VARCHAR(40) NOT NULL
);
