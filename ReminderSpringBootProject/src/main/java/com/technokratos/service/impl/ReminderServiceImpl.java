package com.technokratos.service.impl;

import com.technokratos.dto.ReminderRequest;
import com.technokratos.dto.ReminderResponse;
import com.technokratos.mapper.ReminderMapper;
import com.technokratos.repository.ReminderRepository;
import com.technokratos.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final ReminderMapper reminderMapper;

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String REMINDER_NOT_FOUND = "Reminder not found";
    private static final String USER_NOT_FOUND = "User not found";


    @Override
    public ReminderResponse saveReminder(ReminderRequest reminder) {
        if (userRepository.findById(reminder.userId()).isEmpty()) {
            throw new RuntimeException(USER_NOT_FOUND);
        }
        var rem = reminderMapper.toReminder(reminder);
        reminderRepository.save(rem);
        return reminderMapper.toReminderResponse(rem);
    }

    @Override
    public void deleteReminder(UUID id) {
        reminderRepository.deleteById(id);
    }

    @Override
    public ReminderResponse updateReminder(UUID id, ReminderRequest reminder) {
        if (reminderRepository.findById(id).isPresent()) {
            var rem = reminderMapper.toReminder(reminder);
            rem.setId(id);
            reminderRepository.save(rem);
            return reminderMapper.toReminderResponse(rem);
        }
        throw new RuntimeException(REMINDER_NOT_FOUND);
    }

    @Override
    public Page<ReminderResponse> sortReminders(String by, Sort.Direction direction, int page, int size) {
        Pageable sortedPageable = PageRequest.of(page, size, Sort.by(direction, by));
        return reminderRepository.findAll(sortedPageable).map(reminderMapper::toReminderResponse);
    }

    @Override
    public Page<ReminderResponse> filterReminders(String date, String time, int page, int size) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        try {
            LocalDateTime dateTime = LocalDateTime.parse(date + " " + time, formatter);
            Pageable pageable = PageRequest.of(page, size);
            return reminderRepository.findAllByReminderTime(dateTime, pageable).map(reminderMapper::toReminderResponse);
        } catch (DateTimeParseException e) {
            return Page.empty(PageRequest.of(page, size));
        }
    }

    @Override
    public Page<ReminderResponse> listReminders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reminderRepository.findAll(pageable).map(reminderMapper::toReminderResponse);
    }
}
