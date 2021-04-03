package com.accountopening.client.integration.jms;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import java.util.UUID;

@Log4j2
@Component
public class JmsSender {
    @Qualifier("jmsConnectionFactory")
    @Autowired
    private ConnectionFactory connectionFactory;
    private JmsTemplate jmsTemplate;

    @PostConstruct
    public void init() {
        this.jmsTemplate = new JmsTemplate(connectionFactory);
    }

    public void sendMessage(String queueName, Object sending, UUID correlationId) {
        Gson gson = new Gson();
        String messageText = gson.toJson(sending);
        try {
            jmsTemplate.send(queueName, session -> {
                Message message = session.createTextMessage(messageText);
                message.setJMSCorrelationID(correlationId.toString());
                return message;
            });
        } catch (JmsException e) {
            log.error("Ошибка при отправке сообщения {}: {}", messageText, e);
        }
        log.debug("Послано: {}. Очередь: {}.", messageText, queueName);
    }
}
