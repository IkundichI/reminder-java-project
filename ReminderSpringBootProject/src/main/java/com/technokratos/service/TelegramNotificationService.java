package com.technokratos.service;

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
public class TelegramNotificationService extends TelegramLongPollingBot{

    private final UserService userService;

    private final String BOT_NAME = "reminder_java_test_bot";

    private final String BOT_TOKEN = "6960216768:AAHxef8ij67NtV53f5I5wjhAnrcLwhPKniQ";

    private String chatId;

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
            if (message.getText().equals("/registration")) {
                String chatId = String.valueOf(message.getChatId());
                if (saveChatId(message.getFrom().getUserName(), chatId)) {
                    sendTelegramMessage(chatId, "Registration successful");
                } else {
                    sendTelegramMessage(chatId, "Registration failed");
                }
            }
        }
    }

    private Boolean saveChatId(String username, String chatId) {
        return userService.saveTelegramUserId(username, chatId);
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
