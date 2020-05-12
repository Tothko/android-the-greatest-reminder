package com.example.thegreatestreminder.BusinessLogic;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.thegreatestreminder.BusinessEntities.Reminder;
import com.example.thegreatestreminder.DAO.IReminderRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReminderService {

    private Context ctx;
    IReminderRepository reminderRepository;

    public ReminderService(Context ctx,IReminderRepository reminderRepository){
        this.ctx = ctx;
        this.reminderRepository = reminderRepository;
        getReminder(2);
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
        AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(ctx, AlarmReceiver.class);
        intent.putExtra("reminderId",reminder.getId());
        PendingIntent alarmIntent = PendingIntent.getBroadcast(ctx,(int)reminder.getId(),intent,0);

        Date triggerDate = reminder.getTriggerDateTime();
        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerDate.getTime(),alarmIntent);

        return newReminder;
    }

    public Reminder getReminder(long reminderId) {
        return reminderRepository.get(reminderId);

    }

    public List<Reminder> readAll(){
        return reminderRepository.readAll();
    }

    public int deleteReminder(Reminder reminderToDelete){

        return reminderRepository.deleteReminder(reminderToDelete);

    }
    public void editReminder(Reminder reminderToUpdate){

        validateReminder(reminderToUpdate);

    }
}
