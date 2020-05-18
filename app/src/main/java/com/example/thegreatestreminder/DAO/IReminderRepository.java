package com.example.thegreatestreminder.DAO;

import com.example.thegreatestreminder.BusinessEntities.Reminder;
import com.example.thegreatestreminder.BusinessEntities.ReminderFilter;

import java.util.ArrayList;
import java.util.List;

public interface IReminderRepository {
    void deleteReminder(long reminderId);

    void editReminder(Reminder reminder);

    Reminder addReminder(Reminder reminder);

    ArrayList<Reminder> readAll(ReminderFilter filter);

    Reminder get(long reminderId);
}
