package com.technokratos.service.impl;

import com.technokratos.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender emailSender;

    @Override
    public void sendSimpleEmail(String toAddress, String topic, String message) {
        try {
            val simpleMailMessage = createSimpleMailMessage(toAddress, topic, message);
            emailSender.send(simpleMailMessage);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email to " + toAddress, e);
        }
    }


    private SimpleMailMessage createSimpleMailMessage(String toAddress, String topic, String message) {
        val simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(topic);
        simpleMailMessage.setText(message);
        return simpleMailMessage;
    }
}
