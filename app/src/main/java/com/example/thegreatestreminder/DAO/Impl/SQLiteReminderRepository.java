package com.example.thegreatestreminder.DAO.Impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.thegreatestreminder.BusinessEntities.Reminder;
import com.example.thegreatestreminder.DAO.IReminderRepository;
import com.example.thegreatestreminder.Utils.Helpers.DBOpenHelper;

import static com.example.thegreatestreminder.Constants.SQLConstants.TABLE_NAME;

public class SQLiteReminderRepository implements IReminderRepository {

    private SQLiteDatabase db;

    public  SQLiteReminderRepository(Context ctx){
        DBOpenHelper openHelper = new DBOpenHelper(ctx);
        db = openHelper.getWritableDatabase();
    }

    @Override
    public void deleteReminder(Reminder reminder) {

    }

    @Override
    public void editReminder(Reminder reminder) {

    }

    @Override
    public Reminder addReminder(Reminder reminder) {
        final String INSERT_STMT = "INSERT INTO " + TABLE_NAME +
                " (name,detail,date)" +
                " VALUES (?,?,?)";

        SQLiteStatement stmt = db.compileStatement(INSERT_STMT);

        int i = 1;
        stmt.bindString(i++,reminder.getName());
        stmt.bindString(i++,reminder.getDetail());
        stmt.bindLong(i++,reminder.getTriggerDateTime().getTime());

        long id = stmt.executeInsert();
        reminder.setId(id);
        return reminder;
    }
}
