package com.example.thegreatestreminder.BusinessEntities;

public class ReminderFilter {
    public enum OrderType{
        NAME,EXPIRATION
    }

    public enum ShowType{
        ALL,ACTIVE
    }

    public OrderType order;
    public ShowType show;
    public boolean asc;

    public ReminderFilter(boolean showAll,boolean orderByName,boolean asc){
        this.show = showAll ? ShowType.ALL : ShowType.ACTIVE;
        this.order = orderByName ? OrderType.NAME : OrderType.EXPIRATION;
        this.asc = asc;
    }
}
