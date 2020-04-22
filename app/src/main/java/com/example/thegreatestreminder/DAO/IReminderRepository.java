package com.example.thegreatestreminder.DAO;

import com.example.thegreatestreminder.BusinessEntities.Reminder;

public interface IReminderRepository {
    void deleteReminder(Reminder reminder);

    void editReminder(Reminder reminder);

    void addReminder(Reminder reminder);
}
