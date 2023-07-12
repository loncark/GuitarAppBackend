create table if not exists guitar (
    id identity,
    code varchar(100) not null unique,
    name varchar(100) not null,
    body varchar(100) not null,
    neck varchar(100) not null,
    stock int not null,
    price numeric not null
);

create table if not exists userinfo (
    id identity,
    name varchar(100) not null,
    password varchar(100) not null,
    roles varchar(100) not null
);