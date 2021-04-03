package com.accountopening.server.enums;

public enum CreditAttribute {

    NO_DEBT("0", "Оплата без просрочек"),
    BAD_DEBT("1", "Безнадежный долг"),
    DATA_NOT_FOUND("2","Данные в системе не обнаружены");

    private final String status;
    private final String message;

    CreditAttribute(String status, String message) {
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
