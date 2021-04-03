package com.accountopening.client.enums;

public enum MVDAttribute {
    IN_THE_LIST("0", "Есть в списке"),
    NOT_ON_THE_LIST("1", "Нет в списке");

    private final String status;
    private final String message;

    MVDAttribute(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
