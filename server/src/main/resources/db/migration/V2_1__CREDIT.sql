CREATE TABLE credit
(
    id                   BIGSERIAL primary key,
    passport             varchar(10) unique not null,
    name                 varchar(50)        not null,
    surname              varchar(50)        not null,
    patronymic           varchar(50)        null,
    registration_address varchar(200)       not null,
    date_of_birth        date               not null,
    late_payment         boolean            not null
);

INSERT INTO credit (passport, name, surname, patronymic, registration_address, date_of_birth, late_payment)
VALUES ('1112223334', 'Anna', 'Mihalkova', 'Mihailovna', 'г. Сызрань, ул. Сызраньская, д. 2, кв. 43', '1949-02-11', true),
('3332221111', 'Sergey', 'Sergeev', 'Sergeevich', 'г. Томск, ул. Томская, д. 12, кв. 1', '1992-04-10', false);