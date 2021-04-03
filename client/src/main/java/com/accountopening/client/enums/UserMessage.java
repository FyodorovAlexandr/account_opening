package com.accountopening.client.enums;

public enum UserMessage {
    NOT_FOUND("", "Страница не найдена"),
    WRONG_FORMAT_NAME("", "Неправильный формат имени"),
    WRONG_FORMAT_LAST_NAME("","Неправильный формат фамилии"),
    WRONG_FORMAT_MIDDLE_NAME("","Неправильный формат отчества"),
    WRONG_FORMAT_OF_PASSPORT("","Паспортные данные введены некорректно"),
    WRONG_FORMAT_OF_BIRTHDATE("","Дата рождения введена некорректно"),
    MISS_NAME("","Заполните обязательное поле: Имя"),
    MISS_SURNAME("","Заполните обязательное поле: Фамилия"),
    MISS_BIRTHDAY("","Заполните обязательное поле: Дата рождения"),
    MISS_PASSPORT("","Заполните обязательное поле: Серия и номер паспорта"),
    MISS_ADDRESS("","Заполните обязательное поле: Адрес регистрации"),
    NOT_EIGHTEEN_YEARS("","Вам еще не исполнилось 18 лет");


    private final String value;
    private final String code;

    UserMessage(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
