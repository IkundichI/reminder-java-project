package com.technokratos.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReminderRequest (
        String title,
        String description,
        LocalDateTime reminderTime,
        UUID userId
) {}
