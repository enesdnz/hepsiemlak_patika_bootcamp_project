package com.enesdeniz.updateAdvertStatusservice.service;

import com.enesdeniz.updateAdvertStatusservice.entities.Advert;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * this class listens for messages in rabbitmq queue and receives them in order
 */
@Service
public class RabbitMqListenerService {

    @Autowired
    private AdvertService advertService;

    @RabbitListener(queues = "hepsiemlak.queue")
    public void receiveMessage(Advert advert)  {

        advertService.updateAdvertStatus(advert);
    }

}
