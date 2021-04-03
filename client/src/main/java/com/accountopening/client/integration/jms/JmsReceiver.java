package com.accountopening.client.integration.jms;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.UUID;

@Log4j2
@Component
public class JmsReceiver {

    @Qualifier("jmsConnectionFactory")
    @Autowired
    private ConnectionFactory connectionFactory;
    private JmsTemplate jmsTemplate;

    @PostConstruct
    public void init() {
        this.jmsTemplate = new JmsTemplate(connectionFactory);
    }

    public String receiveMessage(String queueName, UUID correlationId) {
        Message message = jmsTemplate.receiveSelected(queueName, "JMSCorrelationID='" + correlationId + "'");
        TextMessage textMessage = (TextMessage) message;
        try {
            String text = textMessage.getText();
            log.debug("Получено: {}. Очередь: {}.", text, queueName);
            return text;
        } catch (JMSException e) {
            log.error("Ошибка при получении сообщения", e);
        }
        return null;
    }
}
