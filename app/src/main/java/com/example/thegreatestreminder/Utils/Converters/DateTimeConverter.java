package com.example.thegreatestreminder.Utils.Converters;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeConverter {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm");


    public static String dateToString(Date date){
        return DATE_FORMAT.format(date);
    }

    public static String timeToString(Time date){
        return TIME_FORMAT.format(date);
    }

    public static String dateTimeToString(Date date){
        return DATE_TIME_FORMAT.format(date);
    }

    public static Date dateTimeFromString(String str) throws ParseException { return DATE_TIME_FORMAT.parse(str);}

    public static Date dateTimeFromString(String dateStr,String timeStr) throws ParseException {
        String str = dateStr + " " + timeStr;
        return dateTimeFromString(str);
    }

}
