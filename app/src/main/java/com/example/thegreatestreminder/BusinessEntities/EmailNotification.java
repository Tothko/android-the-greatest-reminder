package com.example.thegreatestreminder.BusinessEntities;


import android.content.Context;
import android.content.Intent;

import com.example.thegreatestreminder.R;

public class EmailNotification implements Notification {

    private String receiver;

    public EmailNotification(String mail){
        this.receiver = mail;
    }

    @Override
    public void fire(Reminder reminder, Context ctx) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        String text = reminder.getName() + "\r\n" + reminder.getDetail();
        String[] receivers = { getReceiver() };
        emailIntent.putExtra(Intent.EXTRA_EMAIL, receivers);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Notification from " + ctx.getResources().getString(R.string.app_name));
        emailIntent.putExtra(Intent.EXTRA_TEXT, text);
        ctx.startActivity(emailIntent);
    }

    @Override
    public String getReceiver() {
        return receiver;
    }

    @Override
    public Type getType() {
        return Type.EMAIL;
    }
}
