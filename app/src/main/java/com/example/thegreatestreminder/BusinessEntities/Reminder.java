package com.example.thegreatestreminder.BusinessEntities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reminder {

    private long id;
    private String name;
    private String detail;
    private Date triggerDate;
    private ArrayList<Notification> notifications;

    public Reminder(String name,String detail,Date triggerDate){
        this.name = name;
        this.detail = detail;
        this.triggerDate = triggerDate;
        this.notifications = new ArrayList<>();
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean hasNotifications(){
        return this.notifications.size() > 0;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void addNotification(Notification notification) {
        this.notifications.add(notification);
    }
}
