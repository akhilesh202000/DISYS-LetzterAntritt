package org.postalservice.restapi.queue;

import lombok.RequiredArgsConstructor;
import org.jdbc.entities.Delivery;
import org.jdbc.entities.Letter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producer {

    @Autowired
    private final JmsTemplate jms;
    @Value("${activemq.packagequeue}")
    private String packagePath;
    @Value("${activemq.letterqueue}")
    private String letterPath;

    public void send(Delivery delivery) {
        jms.convertAndSend((delivery instanceof Letter) ? letterPath : packagePath, delivery.getUuid().toString());
    }
}
