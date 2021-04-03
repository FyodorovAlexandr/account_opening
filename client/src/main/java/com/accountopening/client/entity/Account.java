package com.accountopening.client.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Long id;

    private String passport;

    private String name;

    private String surname;

    private String patronymic;

    private String registrationAddress;

    private Date dateOfBirth;
}
