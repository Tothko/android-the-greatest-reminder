package com.example.thegreatestreminder.BusinessEntities;

import java.time.Duration;
import java.util.Date;

public class Reminder {

    private String name;
    private String detail;
    private Date triggerDate;
    private ReminderAction action;

    public Reminder(String name,String detail,Date triggerDate){
        this.name = name;
        this.detail = detail;
        this.triggerDate = triggerDate;
    }

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
