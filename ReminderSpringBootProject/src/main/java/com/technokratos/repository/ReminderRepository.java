package com.technokratos.repository;

import com.technokratos.model.Reminder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReminderRepository extends JpaRepository<Reminder, UUID> {

    Optional<Reminder> findById(UUID id);

    Page<Reminder> findAllByReminderTime(LocalDateTime reminderTime, Pageable pageable);

    List<Reminder> findByReminderTimeBeforeAndIsSentFalse(LocalDateTime time);

}
