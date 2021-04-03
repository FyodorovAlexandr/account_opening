package com.accountopening.client.enums;

public enum BankMessage {
    NOT_BANK_CLIENT("0", "Клиента с такими данными не обнаружено"),
    IS_BANK_CLIENT("1", "Клиент есть в банке");

    private String status;
    private String message;

    BankMessage(String status, String message) {
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
