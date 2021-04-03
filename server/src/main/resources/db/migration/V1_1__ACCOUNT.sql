CREATE TABLE account
(
    id                   BIGSERIAL primary key,
    passport             varchar(10) unique not null,
    name                 varchar(50)        not null,
    surname              varchar(50)        not null,
    patronymic           varchar(50)        null,
    registration_address varchar(200)       not null,
    date_of_birth        date               not null
);

INSERT INTO account (passport, name, surname, patronymic, registration_address, date_of_birth)
VALUES ('1234567891', 'Ivan', 'Ivanov', 'Ivanovich', 'г. Москва, ул. Московская, д. 7, кв. 11', '1954-03-02');