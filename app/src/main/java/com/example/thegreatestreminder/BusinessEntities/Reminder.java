package com.example.thegreatestreminder.BusinessEntities;

import java.time.Duration;

public class Reminder {
    public String getName() {
        return "Name";
    }

    public String getDetail() {
        return "Detail";
    }

    public Duration getDurationUntilFired() {
        throw new UnsupportedOperationException();
    }
}
