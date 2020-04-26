package com.example.thegreatestreminder.BusinessEntities;

public class EmailReminderAction extends ReminderAction {

    @Override
    public Type getType() {
        return Type.EMAIL;
    }

    @Override
    public void execute() {

    }
}
