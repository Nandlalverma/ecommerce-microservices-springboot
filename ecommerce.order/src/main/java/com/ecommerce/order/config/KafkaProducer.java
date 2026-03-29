package com.ecommerce.order.config;

import com.ecommerce.order.entity.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    public KafkaTemplate<String,Object> kafkaTemplate;

    public void sendOrderEvent(Order order){
      kafkaTemplate.send("order-topic",order);
    }

}
