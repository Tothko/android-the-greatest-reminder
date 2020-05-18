package com.example.thegreatestreminder.BusinessLogic;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.thegreatestreminder.BusinessEntities.Reminder;

import java.util.Date;

class ReminderAlarmService {

    Context ctx;

    public ReminderAlarmService(Context ctx){
        this.ctx = ctx;
    }

    public void addReminder(Reminder reminder){
        AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(ctx, AlarmReceiver.class);
        intent.putExtra("reminderId",reminder.getId());
        PendingIntent alarmIntent = PendingIntent.getBroadcast(ctx,(int)reminder.getId(),intent,0);

        Date triggerDate = reminder.getTriggerDateTime();
        alarmManager.set(AlarmManager.RTC_WAKEUP, /*triggerDate.getTime()*/ System.currentTimeMillis() + 5000L,alarmIntent);
    }

    public void deleteReminder(long reminderId){
        AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(ctx, AlarmReceiver.class);
        intent.putExtra("reminderId",reminderId);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(ctx,(int)reminderId,intent,0);

        alarmManager.cancel(alarmIntent);
    }
}
