package com.technokratos.controller;

import com.technokratos.dto.ReminderRequest;
import com.technokratos.dto.ReminderResponse;
import com.technokratos.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reminder")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;

    @PostMapping("/create")
    public ReminderResponse createReminder(@RequestBody ReminderRequest reminderRequest) {

        return reminderService.saveReminder(reminderRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReminder(@PathVariable UUID id) {

        reminderService.deleteReminder(id);
    }

    @PutMapping("/update/{id}")
    public ReminderResponse updateReminder(@PathVariable UUID id,
                                           @RequestBody ReminderRequest reminderRequest) {

        return reminderService.updateReminder(id, reminderRequest);
    }

    @GetMapping("/sort")
    public Page<ReminderResponse> sortReminders(@RequestParam String by,
                                                @RequestParam Sort.Direction direction,
                                                @RequestParam int page,
                                                @RequestParam int size) {

        return reminderService.sortReminders(by, direction, page, size);
    }

    @GetMapping("/filter")
    public Page<ReminderResponse> filterReminders(@RequestParam String date,
                                                  @RequestParam String time,
                                                  @RequestParam int page,
                                                  @RequestParam int size) {
        return reminderService.filterReminders(date, time, page, size);
    }

    @GetMapping("/list")
    public Page<ReminderResponse> listReminders(@RequestParam int page,
                                                @RequestParam int size) {
        return reminderService.listReminders(page, size);
    }
}
