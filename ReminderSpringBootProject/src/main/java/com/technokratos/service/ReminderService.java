package com.technokratos.service;

import com.technokratos.dto.ReminderRequest;
import com.technokratos.dto.ReminderResponse;
import com.technokratos.model.Reminder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ReminderService {

    ReminderResponse saveReminder(ReminderRequest reminder);
    void deleteReminder(UUID id);
    ReminderResponse updateReminder(UUID id, ReminderRequest reminder);


    Page<ReminderResponse> filterReminders(String date, String time, int page, int size);
    Page<ReminderResponse> listReminders(int page, int size);
    Page<ReminderResponse> sortReminders(String by, Sort.Direction direction, int page, int size);
}
