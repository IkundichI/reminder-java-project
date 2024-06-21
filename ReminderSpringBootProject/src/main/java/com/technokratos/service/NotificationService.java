package com.technokratos.service;

import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
     void sendSimpleEmail(String toAddress, String topic, String message);
}