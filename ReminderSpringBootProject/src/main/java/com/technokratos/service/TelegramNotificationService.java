package com.technokratos.service;

import com.technokratos.property.TelegramProperty;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Service
@RequiredArgsConstructor
public class TelegramNotificationService extends TelegramLongPollingBot {

    private final UserService userService;
    private final TelegramProperty telegramProperty;

    private static final String REGISTRATION_SUCCESSFUL = "Registration successful";
    private static final String REGISTRATION_FAILED = "Registration failed";
    private static final String REGISTRATION_COMMAND = "/registration";

    @PostConstruct
    public void registerBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendTelegramMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            if (REGISTRATION_COMMAND.equals(message.getText())) {
                String chatId = String.valueOf(message.getChatId());
                if (saveChatId(message.getFrom().getUserName(), chatId)) {
                    sendTelegramMessage(chatId, REGISTRATION_SUCCESSFUL);
                } else {
                    sendTelegramMessage(chatId, REGISTRATION_FAILED);
                }
            }
        }
    }

    private Boolean saveChatId(String username, String chatId) {
        return userService.saveTelegramUserId(username, chatId);
    }

    @Override
    public String getBotUsername() {
        return telegramProperty.getBotName();
    }

    @Override
    public String getBotToken() {
        return telegramProperty.getBotToken();
    }
}
