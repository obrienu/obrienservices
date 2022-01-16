package com.obrien.clients.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto implements Serializable {
    private String message;
    private String sender;
    private String toCustomerEmail;
    private Integer toCustomerId;
    private LocalDateTime sentAt;
}
