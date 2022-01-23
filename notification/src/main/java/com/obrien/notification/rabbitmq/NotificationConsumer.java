package com.obrien.notification.rabbitmq;

import com.obrien.clients.notification.NotificationDto;
import com.obrien.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationDto notificationRequest){
        log.info("Consumed {} from queue", notificationRequest);
        notificationService.sendNotification(notificationRequest);
    }
}
