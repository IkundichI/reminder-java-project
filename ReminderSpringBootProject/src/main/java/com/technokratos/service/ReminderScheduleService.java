
package com.technokratos.service;

import com.technokratos.model.Reminder;
import com.technokratos.repository.ReminderRepository;
import com.technokratos.service.impl.NotificationServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReminderScheduleService {

    private final ReminderRepository reminderRepository;
    private final NotificationServiceImpl notificationService;
    private final TelegramNotificationService telegramNotificationService;

    @Scheduled(fixedRate = 60000)
    public void checkReminders() {

        LocalDateTime now = LocalDateTime.now();
        List<Reminder> dueReminders = reminderRepository.findByReminderTimeBeforeAndIsSentFalse(now);

        for (Reminder reminder : dueReminders) {
            String time = reminder.getReminderTime().format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy"));
            String email = reminder.getUser().getEmail();
            String topic = reminder.getTitle();
            String message = String.format("Напоминание: %s. \nВремя: %s. \nОписание: %s", topic, time, reminder.getDescription());
            String tg = reminder.getUser().getTelegramChatId();
            notificationService.sendSimpleEmail(email, topic, message);
            telegramNotificationService.sendTelegramMessage(tg, message);

            setSent(reminder);
        }
    }

    private void setSent(Reminder reminder) {
        reminder.setIsSent(true);
        reminderRepository.save(reminder);
    }
}
