package com.example.thegreatestreminder.BusinessEntities;

import android.content.Context;
import android.telephony.SmsManager;

import com.example.thegreatestreminder.R;

public class SmsNotification implements Notification {

    private String receiver;

    public SmsNotification(String phoneNumber){
        this.receiver = phoneNumber;
    }

    @Override
    public void fire(Reminder reminder, Context ctx) {
        SmsManager smsManager = SmsManager.getDefault();
        String text = "Notification from " + ctx.getResources().getString(R.string.app_name) +
                "\r\n" + reminder.getName() + "\r\n" + reminder.getDetail();
        smsManager.sendTextMessage(getReceiver(), null, text, null, null);
    }

    @Override
    public String getReceiver() {
        return receiver;
    }

    @Override
    public Type getType() {
        return Type.SMS;
    }
}
