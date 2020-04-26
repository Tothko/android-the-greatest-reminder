package com.example.thegreatestreminder.BusinessEntities;

public class SmsReminderAction extends ReminderAction {
    @Override
    public Type getType() {
        return Type.SMS;
    }

    @Override
    public void execute() {

    }
}
