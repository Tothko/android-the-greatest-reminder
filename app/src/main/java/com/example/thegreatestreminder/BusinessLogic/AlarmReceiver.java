package com.example.thegreatestreminder.BusinessLogic;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.thegreatestreminder.AlarmedActivity;
import com.example.thegreatestreminder.BusinessEntities.Notification;
import com.example.thegreatestreminder.BusinessEntities.Reminder;
import com.example.thegreatestreminder.DependencyFactory;
import com.example.thegreatestreminder.R;

import java.util.List;

public class AlarmReceiver extends BroadcastReceiver {

    private Context ctx;

    private void makeAndroidNotification(Reminder reminder)
    {
        Intent intent1 = new Intent(ctx, AlarmedActivity.class);
        intent1.putExtra("reminderId",reminder.getId());
        PendingIntent pendingIntent = PendingIntent.getActivity(ctx,0,intent1,0);
        //Define Notification Manager
        NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);

        //Define sound URI
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ctx)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(reminder.getName())
                .setContentText(reminder.getDetail())
                .setSound(soundUri);

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
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        this.ctx = context;

        long reminderId = intent.getLongExtra("reminderId",0);

        ReminderService reminderService = DependencyFactory.getInstance(context).getReminderService();

        Reminder reminder = reminderService.getReminder(reminderId);

        makeAndroidNotification(reminder);

        if(reminder.hasNotifications()){
            List<Notification> additionalNotifications = reminder.getNotifications();

            for (Notification notification: additionalNotifications) {
                notification.fire();
            }
        }
    }
}
