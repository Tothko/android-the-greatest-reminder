package com.example.thegreatestreminder.BusinessLogic;

import android.content.Context;

import com.example.thegreatestreminder.BusinessEntities.Reminder;
import com.example.thegreatestreminder.BusinessEntities.ReminderFilter;
import com.example.thegreatestreminder.DAO.IReminderRepository;

import java.util.ArrayList;
import java.util.Date;

public class ReminderService {

    private Context ctx;
    IReminderRepository reminderRepository;
    ReminderAlarmService alarmService;

    public ReminderService(Context ctx,IReminderRepository reminderRepository){
        this.ctx = ctx;
        this.reminderRepository = reminderRepository;
        this.alarmService = new ReminderAlarmService(ctx);
    }

    private void validateReminder(Reminder reminder){
        Date triggerDate = reminder.getTriggerDateTime();
        long current = System.currentTimeMillis();
        long selected = triggerDate.getTime();
        if(selected < current)
            throw new IllegalArgumentException("Reminder date is smaller than current date");
    }

    public Reminder addReminder(Reminder reminder){

        validateReminder(reminder);

        Reminder newReminder = this.reminderRepository.addReminder(reminder);
        this.alarmService.addReminder(newReminder);

        return newReminder;
    }

    public Reminder getReminder(long reminderId) {
        return reminderRepository.get(reminderId);
    }

    public ArrayList<Reminder> getAllReminders(ReminderFilter filter){
        return this.reminderRepository.readAll(filter);
    }

    public void deleteReminder(long reminderId){
        this.alarmService.deleteReminder(reminderId);
        this.reminderRepository.deleteReminder(reminderId);
    }
}
