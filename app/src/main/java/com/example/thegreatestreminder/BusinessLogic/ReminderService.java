package com.example.thegreatestreminder.BusinessLogic;

import com.example.thegreatestreminder.BusinessEntities.Reminder;
import com.example.thegreatestreminder.DAO.IReminderRepository;

public class ReminderService {

    IReminderRepository reminderRepository;

    public ReminderService(IReminderRepository reminderRepository){
        this.reminderRepository = reminderRepository;
    }

    public Reminder addReminder(Reminder reminder){
        return this.reminderRepository.addReminder(reminder);
    }
}
