package com.enesdeniz.commonservice.queue;

import com.enesdeniz.commonservice.config.RabbitMqConfig;
import com.enesdeniz.commonservice.entities.Advert;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService implements QueueService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private RabbitMqConfig config;

    @Override
    public void updateAdvertStatus(Advert advert) {
        rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), advert);

    }

}
