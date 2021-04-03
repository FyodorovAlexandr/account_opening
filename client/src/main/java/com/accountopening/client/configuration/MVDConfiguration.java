package com.accountopening.client.configuration;

import com.accountopening.client.integration.soap.MVDClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class MVDConfiguration {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.accountopening.client.wsdl");
        return marshaller;
    }

    @Bean
    public MVDClient mvdClient(Jaxb2Marshaller marshaller) {
        MVDClient client = new MVDClient();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}

