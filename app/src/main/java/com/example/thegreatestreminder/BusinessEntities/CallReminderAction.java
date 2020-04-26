package com.example.thegreatestreminder.BusinessEntities;

public class CallReminderAction extends ReminderAction {

    public CallReminderAction(String mobileNumber){

    }

    @Override
    public Type getType() {
        return Type.CALL;
    }

    @Override
    public void execute() {

    }
}
