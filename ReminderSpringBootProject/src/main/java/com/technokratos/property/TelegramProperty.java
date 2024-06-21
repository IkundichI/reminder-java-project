package com.technokratos.property;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class TelegramProperty {

    @Value("${spring.telegram.bot.name}")
    private String botName;

    @Value("${spring.telegram.bot.token}")
    private String botToken;
}
