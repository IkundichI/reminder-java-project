package com.technokratos.service.impl;

import com.technokratos.dto.UserRequest;
import com.technokratos.model.User;
import com.technokratos.repository.UserRepository;
import com.technokratos.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void processOAuthPostLogin(String email, String username) {
        if (userRepository.findByEmail(email).isEmpty()) {
            var result = User.builder()
                    .email(email)
                    .username(username)
                    .build();
            userRepository.save(result);
        }
    }

    @Override
    public User findById(UUID id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new RuntimeException("User not found");
        } else {
            return userRepository.findById(id).get();
        }
    }

    @Override
    public Boolean updateTelegramContact(UUID uuid, UserRequest userRequest) {
        var user = userRepository.findById(uuid);
        if (user.isEmpty()) {
            return false;
        } else {
            user.get().setTelegramContact(userRequest.telegramContact());
            userRepository.save(user.get());
            return true;
        }
    }

    @Override
    public Boolean saveTelegramUserId(String username, String chatId) {
        var user = userRepository.findByTelegramContact(username);
        if (user.isPresent()) {
            user.get().setTelegramChatId(chatId);
            userRepository.save(user.get());
            return true;
        }
        return false;
    }
}
