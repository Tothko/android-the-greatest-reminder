package com.example.thegreatestreminder.BusinessEntities;

import android.content.Context;

public interface Notification {
    enum Type{SMS,EMAIL};
    void fire(Reminder reminder, Context ctx);
    String getReceiver();
    Type getType();
}
