package com.technokratos.configuration;

import com.technokratos.property.EmailProperty;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
@RequiredArgsConstructor
public class NotificationAppConfig {

    private final EmailProperty emailProperty;

    @Bean
    public JavaMailSender javaMailSender() {
        val mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailProperty.getHOST());
        mailSender.setPort(emailProperty.getPORT());
        mailSender.setUsername(emailProperty.getUsername());
        mailSender.setPassword(emailProperty.getPassword());

        val props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", emailProperty.getProtocol());
        props.put("mail.smtp.auth", emailProperty.getAuth());
        props.put("mail.smtp.starttls.enable", emailProperty.getStarttls());
        props.put("mail.debug", emailProperty.getDebug());

        return mailSender;
    }


}
