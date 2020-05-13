package com.example.thegreatestreminder.BusinessEntities;


import android.content.Context;
import android.content.Intent;

import com.example.thegreatestreminder.DAO.IEmailClient;
import com.example.thegreatestreminder.DAO.Impl.MailjetEmailClient;
import com.example.thegreatestreminder.R;

public class EmailNotification implements Notification {

    private String receiver;
    private IEmailClient emailClient;

    public EmailNotification(String mail){
        this.receiver = mail;
        this.emailClient = new MailjetEmailClient();
    }

    @Override
    public void fire(Reminder reminder, Context ctx) {
        String text = reminder.getName() + "\r\n" + reminder.getDetail();
        emailClient.send(getReceiver(),"Notification from " + ctx.getResources().getString(R.string.app_name),text);
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
