create table if not exists guitar (
    id identity,
    code varchar(100) not null unique,
    name varchar(100) not null,
    body varchar(100) not null,
    neck varchar(100) not null,
    stock int not null,
    price numeric not null
);