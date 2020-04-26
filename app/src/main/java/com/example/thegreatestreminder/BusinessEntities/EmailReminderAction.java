package com.example.thegreatestreminder.BusinessEntities;

public class EmailReminderAction extends ReminderAction {

    String recipients[];
    String message;

    public EmailReminderAction(String recipients[],String message){
        this.recipients = recipients;
        this.message = message;
    }


    @Override
    public Type getType() {
        return Type.EMAIL;
    }

    @Override
    public void execute() {

    }
}
