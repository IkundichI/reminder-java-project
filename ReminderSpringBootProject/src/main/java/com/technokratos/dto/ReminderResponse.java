package com.technokratos.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReminderResponse (
        UUID id,
        String title,
        String description,
        LocalDateTime reminderTime,
        UUID userId
) {}
