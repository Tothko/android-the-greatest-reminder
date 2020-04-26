package com.example.thegreatestreminder.BusinessEntities;

public abstract class ReminderAction {

    enum Type{ CALL,SMS,EMAIL };

    public abstract Type getType();

    public abstract void execute();
}
