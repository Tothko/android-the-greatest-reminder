package com.example.thegreatestreminder.DAO;

import com.example.thegreatestreminder.BusinessEntities.Reminder;

import java.util.List;

public interface IReminderRepository {
    void deleteReminder(Reminder reminder);

    void editReminder(Reminder reminder);

    Reminder addReminder(Reminder reminder);

    List<Reminder> readAll();

    Reminder get(long reminderId);
}
