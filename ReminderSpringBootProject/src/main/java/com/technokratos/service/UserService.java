package com.technokratos.service;

import com.technokratos.dto.UserRequest;
import com.technokratos.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {

    void processOAuthPostLogin(String email, String username);
    User findById(UUID id);
    Boolean updateTelegramContact(UUID uuid, UserRequest userRequest);
    Boolean saveTelegramUserId(String username, String chatId);
}
