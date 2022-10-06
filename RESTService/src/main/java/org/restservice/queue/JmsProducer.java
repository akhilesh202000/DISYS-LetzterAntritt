package org.restservice.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JmsProducer {

    private final JmsTemplate jms;
    @Value("${active-mq.package-path}")
    private String packagePath;
    @Value("${active-mq.letter-path}")
    private String letterPath;


}
