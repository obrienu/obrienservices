package com.obrien.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.obrien.clients.notification.NotificationDto;
import org.springframework.stereotype.Service;

@Service
public record NotificationService(NotificationRepository notificationRepository, ObjectMapper objectMapper) {
    public boolean sendNotification(NotificationDto notificationDto){
        Notification notification = objectMapper.convertValue(notificationDto, Notification.class);
        notificationRepository.saveAndFlush(notification);
        return true;
    }
}
