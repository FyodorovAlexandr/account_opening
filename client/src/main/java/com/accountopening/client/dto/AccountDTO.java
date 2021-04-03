package com.accountopening.client.dto;

import lombok.Builder;

@Builder
public class AccountDTO {
    String name;
    String surname;
    String patronymic;
    String passport;
    String address;
    String birthday;

    public AccountDTO(String name, String surname, String patronymic, String passport, String address, String birthday) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.passport = passport;
        this.address = address;
        this.birthday = birthday;
    }

    public AccountDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
