package com.example.thegreatestreminder.BusinessEntities;

public class SmsReminderAction extends ReminderAction {

    String phoneNumber;
    String message;

    SmsReminderAction(String phoneNumber,String message){
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    @Override
    public Type getType() {
        return Type.SMS;
    }

    @Override
    public void execute() {

    }
}
