package com.technokratos.controller;

import com.technokratos.dto.ReminderRequest;
import com.technokratos.dto.ReminderResponse;
import com.technokratos.mapper.ReminderMapper;
import com.technokratos.model.Reminder;
import com.technokratos.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reminder")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;
    private final ReminderMapper reminderMapper;

    @PostMapping("/create")
    public ReminderResponse createReminder(@RequestBody ReminderRequest reminderRequest) {
        Reminder reminder = reminderMapper.toReminder(reminderRequest);
        Reminder savedReminder = reminderService.saveReminder(reminder);
        return reminderMapper.toReminderResponse(savedReminder);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReminder(@PathVariable UUID id) {
        reminderService.deleteReminder(id);
    }

    @PutMapping("/update/{id}")
    public ReminderResponse updateReminder(@PathVariable UUID id, @RequestBody ReminderRequest reminderRequest) {
        Reminder reminder = reminderMapper.toReminder(reminderRequest);
        reminder.setId(id);
        Reminder updatedReminder = reminderService.updateReminder(reminder);
        return reminderMapper.toReminderResponse(updatedReminder);
    }

    @GetMapping("/sort")
    public Page<ReminderResponse> sortReminders(@RequestParam String by, Sort.Direction direction, Pageable pageable) {
        return reminderService.sortReminders(by, direction, pageable)
                .map(reminderMapper::toReminderResponse);
    }

    @GetMapping("/filter")
    public Page<ReminderResponse> filterReminders(@RequestParam String date, @RequestParam String time, Pageable pageable) {
        return reminderService.filterReminders(date, time, pageable)
                .map(reminderMapper::toReminderResponse);
    }

    @GetMapping("/list")
    public Page<ReminderResponse> listReminders(Pageable pageable) {
        return reminderService.listReminders(pageable)
                .map(reminderMapper::toReminderResponse);
    }
}
