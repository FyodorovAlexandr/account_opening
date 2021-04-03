package com.accountopening.client.integration;

import com.accountopening.client.dto.AccountDTO;
import com.accountopening.client.enums.BankMessage;
import com.accountopening.client.enums.OpeningMessage;
import com.accountopening.client.integration.jms.JmsReceiver;
import com.accountopening.client.integration.jms.JmsSender;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataProvider {
    private static final String ACCOUNT_REQUEST_QUEUE = "accountRequest.in";
    private static final String ACCOUNT_RESPONSE_QUEUE = "accountRequest.out";
    private static final String OPENING_ACCOUNT_REQUEST_QUEUE = "openingAccountRequest.in";
    private static final String OPENING_ACCOUNT_RESPONSE_QUEUE = "openingAccountResponse.out";

    @Autowired
    private JmsReceiver receiver;
    @Autowired
    private JmsSender sender;

    public BankMessage getAccountInfo(String passport) {
        UUID correlationID = UUID.randomUUID();
        sender.sendMessage(ACCOUNT_REQUEST_QUEUE, passport, correlationID);
        return new Gson().fromJson(
                receiver.receiveMessage(ACCOUNT_RESPONSE_QUEUE, correlationID),
                BankMessage.class);
    }

    public OpeningMessage addAccount(AccountDTO accountDTO) {
        UUID correlationID = UUID.randomUUID();
        sender.sendMessage(OPENING_ACCOUNT_REQUEST_QUEUE, accountDTO, correlationID);
        return new Gson().fromJson(
                receiver.receiveMessage(OPENING_ACCOUNT_RESPONSE_QUEUE, correlationID),
                OpeningMessage.class);
    }
}
