package com.example.thegreatestreminder.Utils.Converters;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeConverter {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd-mm-yyyy HH:mm");


    public static String dateToString(Date date){
        return DATE_FORMAT.format(date);
    }

    public static String timeToString(Time date){
        return TIME_FORMAT.format(date);
    }

    public static String dateTimeToString(Date date){
        return DATE_TIME_FORMAT.format(date);
    }
}
