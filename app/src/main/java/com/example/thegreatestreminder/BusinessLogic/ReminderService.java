package com.example.thegreatestreminder.BusinessLogic;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.thegreatestreminder.BusinessEntities.Reminder;
import com.example.thegreatestreminder.DAO.IReminderRepository;

import java.util.Date;

public class ReminderService {

    private Context ctx;
    IReminderRepository reminderRepository;

    public ReminderService(Context ctx,IReminderRepository reminderRepository){
        this.ctx = ctx;
        this.reminderRepository = reminderRepository;
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
        PendingIntent alarmIntent = PendingIntent.getBroadcast(ctx,25132,intent,0);

        Date triggerDate = reminder.getTriggerDateTime();
        alarmManager.set(AlarmManager.RTC_WAKEUP, /*triggerDate.getTime()*/ System.currentTimeMillis() + 5000L,alarmIntent);

        return newReminder;
    }

    public Reminder getReminder(long reminderId) {
        return reminderRepository.get(reminderId);
    }
}
