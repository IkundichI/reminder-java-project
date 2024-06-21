package com.technokratos.service;

import com.technokratos.model.Reminder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ReminderService {

    Reminder saveReminder(Reminder reminder);
    void deleteReminder(UUID id);
    Reminder updateReminder(Reminder reminder);


    Page<Reminder> filterReminders(String date, String time, Pageable pageable);
    Page<Reminder> listReminders(Pageable pageable);
    Page<Reminder> sortReminders(String by, Sort.Direction direction, Pageable pageable);
}
