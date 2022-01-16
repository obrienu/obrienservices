package com.obrien;

import com.obrien.clients.notification.NotificationDto;
import com.obrien.clients.notification.NotificationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/notification")
@Slf4j
public record NotificationController(NotificationService notificationService) {

    @PostMapping()
    public NotificationResponse sendNotification(@RequestBody NotificationDto notificationDto){
        log.info("Sending notification to customer {}", notificationDto.getToCustomerId());
        log.info("NotificationInfo ===> {}", notificationDto);
        boolean isSent = notificationService.sendNotification(notificationDto);
        return new NotificationResponse(isSent);
    }
}
