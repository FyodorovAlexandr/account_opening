package com.accountopening.client.enums;

public enum OpeningMessage {
    ERROR_ACCOUNT_OPENING("0", "Ошибка открытия счета"),
    SUCCESSFUL_OPENING("1", "Счет успешно создан");

    private final String status;
    private final String message;

    OpeningMessage(String status, String message) {
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