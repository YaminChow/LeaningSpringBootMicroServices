package com.programmingtechie.notifcationservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class NotifcationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotifcationServiceApplication.class, args);
    }

    @KafkaListener(topics="notificationTopic")
    public void handleNotification(OrderNotificationEvent orderNotificationEvent){
        //send out the email notification
        log.info("Received notification for order {}", orderNotificationEvent.getOrderMessage());
    }

}


