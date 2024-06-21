package com.technokratos.controller;

import com.technokratos.dto.UserRequest;
import com.technokratos.model.User;
import com.technokratos.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public Boolean updateTelegramContact(@PathVariable UUID id, @RequestBody UserRequest userRequest) {
        return userService.updateTelegramContact(id, userRequest);
    }

}
