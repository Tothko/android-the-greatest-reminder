package com.example.thegreatestreminder.BusinessLogic;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.thegreatestreminder.BusinessEntities.Reminder;
import com.example.thegreatestreminder.DependencyFactory;
import com.example.thegreatestreminder.R;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        long reminderId = intent.getLongExtra("reminderId",0);

        ReminderService reminderService = DependencyFactory.getInstance(context).getReminderService();

        Reminder reminder = reminderService.getReminder(reminderId);

       try {
           //Define Notification Manager
           NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

           //Define sound URI
           Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

           NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                   .setSmallIcon(R.drawable.ic_launcher_background)
                   .setContentTitle(reminder.getName())
                   .setContentText(reminder.getDetail())
                   .setSound(soundUri); //This sets the sound to play

           // === Removed some obsoletes
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
           {
               String channelId = "Your_channel_id";
               NotificationChannel channel = new NotificationChannel(
                       channelId,
                       "Channel human readable title",
                       NotificationManager.IMPORTANCE_HIGH);
               notificationManager.createNotificationChannel(channel);
               mBuilder.setChannelId(channelId);
           }

           //Display notification
           notificationManager.notify(654422, mBuilder.build());
       }catch (Exception ex){
           Toast errorToast = Toast.makeText(context,ex.getMessage(),Toast.LENGTH_SHORT);
           errorToast.show();
       }
    }
}
