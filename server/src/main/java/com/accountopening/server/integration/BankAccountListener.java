package com.accountopening.server.integration;

import com.accountopening.server.enums.BankMessage;
import com.accountopening.server.service.impl.BankAccountServiceImpl;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Log4j2
@Component("bankListener")
public class BankAccountListener {

    private static final String ACCOUNT_REQUEST_QUEUE = "accountRequest.in";
    private static final String ACCOUNT_RESPONSE_QUEUE = "accountRequest.out";

    BankAccountServiceImpl bankAccountServiceImpl;

    @Autowired
    public BankAccountListener(BankAccountServiceImpl bankAccountServiceImpl) {
        this.bankAccountServiceImpl = bankAccountServiceImpl;
    }

    @JmsListener(destination = ACCOUNT_REQUEST_QUEUE)
    @SendTo(ACCOUNT_RESPONSE_QUEUE)
    public String receiveQueueMessage(final Message message) {
        BankMessage response = null;
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String passport = textMessage.getText().replace("\"", "");
                response = bankAccountServiceImpl.checkIsBankAccount(passport);
            }
        } catch (JMSException e) {
            log.error("Ошибка при получении сообщения из: " + ACCOUNT_REQUEST_QUEUE, e);
        }
        return new Gson().toJson(response);
    }
}
