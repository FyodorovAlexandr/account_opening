package com.accountopening.server.enums;

public enum BankMessage {
    NOT_BANK_CLIENT(0, "Клиента с такими данными не обнаружено"),
    IS_BANK_CLIENT(1, "Клиент есть в банке");

    private int status;
    private String message;

    BankMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus(BankMessage bankMessage) {
        return bankMessage.status;
    }

    public String getMessage(BankMessage bankMessage) {
        return bankMessage.message;
    }
}
