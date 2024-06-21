package com.technokratos.service.impl;

import com.technokratos.model.Reminder;
import com.technokratos.repository.ReminderRepository;
import com.technokratos.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;

    @Override
    public Reminder saveReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    @Override
    public void deleteReminder(UUID id) {
        reminderRepository.deleteById(id);
    }

    @Override
    public Reminder updateReminder(Reminder reminder) {
        if (reminderRepository.findById(reminder.getId()).isPresent()) {
            return reminderRepository.save(reminder);
        }
        throw new RuntimeException("Reminder not found");
    }

    @Override
    public Page<Reminder> sortReminders(String by, Sort.Direction direction, Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(direction, by));
        return reminderRepository.findAll(sortedPageable);
    }

    @Override
    public Page<Reminder> filterReminders(String date, String time, Pageable pageable) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(date + " " + time, formatter);
            return reminderRepository.findAllByReminderTime(dateTime, pageable);
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse date and time: " + e.getMessage());
        }
        return Page.empty(pageable);
    }

    @Override
    public Page<Reminder> listReminders(Pageable pageable) {
        return reminderRepository.findAll(pageable);
    }
}
