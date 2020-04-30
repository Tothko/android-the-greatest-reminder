package com.example.thegreatestreminder.Utils.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.thegreatestreminder.Constants.SQLConstants;

import static com.example.thegreatestreminder.Constants.SQLConstants.KEY_DATE;
import static com.example.thegreatestreminder.Constants.SQLConstants.KEY_DETAIL;
import static com.example.thegreatestreminder.Constants.SQLConstants.KEY_ID;
import static com.example.thegreatestreminder.Constants.SQLConstants.KEY_NAME;
import static com.example.thegreatestreminder.Constants.SQLConstants.KEY_RECEIVER;
import static com.example.thegreatestreminder.Constants.SQLConstants.KEY_REMINDER_ID;
import static com.example.thegreatestreminder.Constants.SQLConstants.KEY_TYPE;
import static com.example.thegreatestreminder.Constants.SQLConstants.TABLE_NAME;
import static com.example.thegreatestreminder.Constants.SQLConstants.TABLE_NAME_NOTIF;

public class DBOpenHelper extends SQLiteOpenHelper {

    public DBOpenHelper(Context context)
    {
        super(context, SQLConstants.DB_NAME, null, SQLConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME
                + "(" +
                KEY_ID + " INTEGER PRIMARY KEY, "+
                KEY_NAME+" TEXT, "+
                KEY_DETAIL+" TEXT, "+
                KEY_DATE+" INTEGER);");

        db.execSQL("CREATE TABLE " + TABLE_NAME_NOTIF
                + "("+
                KEY_ID+" INTEGER PRIMARY KEY, "+
                KEY_TYPE+ " INTEGER,"+
                KEY_REMINDER_ID+" INTEGER,"+
                KEY_RECEIVER+" TEXT,"+
                "FOREIGN KEY ("+KEY_REMINDER_ID+") REFERENCES "+TABLE_NAME+"("+KEY_ID+")"
                +");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_NOTIF);
        onCreate(db);
    }
}
