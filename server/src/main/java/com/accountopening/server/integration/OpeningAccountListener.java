package com.accountopening.server.integration;

import com.accountopening.server.dto.AccountDTO;
import com.accountopening.server.enums.OpeningMessage;
import com.accountopening.server.service.impl.OpeningServiceImpl;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Log4j2
@Component("openingAccountListener")
public class OpeningAccountListener {

    private static final String OPENING_ACCOUNT_REQUEST_QUEUE = "openingAccountRequest.in";
    private static final String OPENING_ACCOUNT_RESPONSE_QUEUE = "openingAccountResponse.out";

    OpeningServiceImpl openingServiceImpl;

    @Autowired
    public OpeningAccountListener(OpeningServiceImpl openingServiceImpl) {
        this.openingServiceImpl = openingServiceImpl;
    }

    @JmsListener(destination = OPENING_ACCOUNT_REQUEST_QUEUE)
    @SendTo(OPENING_ACCOUNT_RESPONSE_QUEUE)
    public String receiveQueueMessage(final Message message) {
        OpeningMessage response = null;
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                AccountDTO accountDTO = new Gson().fromJson(textMessage.getText(), AccountDTO.class);
                response = openingServiceImpl.addAccount(accountDTO);
            }
        } catch (JMSException e) {
            log.error("Ошибка при получении сообщения из: " + OPENING_ACCOUNT_REQUEST_QUEUE, e);
        }
        return new Gson().toJson(response);
    }
}
