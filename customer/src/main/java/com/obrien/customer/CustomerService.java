package com.obrien.customer;

import com.obrien.clients.fraud.FraudCheckResponse;
import com.obrien.clients.fraud.FraudClient;
import com.obrien.clients.notification.NotificationClient;
import com.obrien.clients.notification.NotificationDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record CustomerService(CustomerRepository customerRepository, FraudClient fraudClient, NotificationClient notificationClient) {
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

        NotificationDto notificationDto = NotificationDto.builder()
                .toCustomerId(customer.getId())
                .toCustomerEmail(customer.getEmail())
                .message(String.format("Hello %s, welcome to the world", customer.getFirstName()))
                .sentAt(LocalDateTime.now())
                .sender("Obrien")
                .build();

        notificationClient.sendNotification(notificationDto);
    }
}
