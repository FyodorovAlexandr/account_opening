CREATE TABLE mvd
(
    id                   BIGSERIAL primary key,
    passport             varchar(10) unique not null,
    name                 varchar(50)        not null,
    surname              varchar(50)        not null,
    patronymic           varchar(50)        null,
    registration_address varchar(200)       not null,
    date_of_birth        date               not null
);

INSERT INTO mvd (passport, name, surname, patronymic, registration_address, date_of_birth)
VALUES ('2323232323', 'Petr', 'Petrov', 'Petrovich', 'г. Астрахань, ул. Добрая, д. 10, кв. 23', '1999-01-08');