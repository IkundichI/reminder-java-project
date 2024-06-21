package com.technokratos.mapper;

import com.technokratos.dto.ReminderRequest;
import com.technokratos.dto.ReminderResponse;
import com.technokratos.model.Reminder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReminderMapper {

    @Mapping(target = "userId", source = "user.id")
    ReminderResponse toReminderResponse(Reminder reminder);

    @Mapping(target = "user.id", source = "userId")
    Reminder toReminder(ReminderRequest reminderRequest);
}
