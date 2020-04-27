package com.example.thegreatestreminder.Utils.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.thegreatestreminder.Constants.SQLConstants;

import static com.example.thegreatestreminder.Constants.SQLConstants.TABLE_NAME;

public class DBOpenHelper extends SQLiteOpenHelper {

    public DBOpenHelper(Context context)
    {
        super(context, SQLConstants.DB_NAME, null, SQLConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME
                + "(id INTEGER PRIMARY KEY, name TEXT, detail TEXT, date INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
