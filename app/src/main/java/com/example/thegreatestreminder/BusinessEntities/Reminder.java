package com.example.thegreatestreminder.BusinessEntities;

import java.time.Duration;
import java.util.Date;

public class Reminder {

    private String name;
    private String detail;
    private Date triggerDate;
    private ReminderAction action;


    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public Duration getDurationUntilFired() {
        throw new UnsupportedOperationException();
    }

    public Date getTriggerDateTime(){
        return triggerDate;
    }

    public ReminderAction getAction(){
        return action;
    }
}
