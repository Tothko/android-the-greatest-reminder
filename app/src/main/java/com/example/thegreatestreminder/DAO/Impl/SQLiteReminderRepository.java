package com.example.thegreatestreminder.DAO.Impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.thegreatestreminder.BusinessEntities.Reminder;
import com.example.thegreatestreminder.DAO.IReminderRepository;
import com.example.thegreatestreminder.Utils.Converters.DateTimeConverter;
import com.example.thegreatestreminder.Utils.Helpers.DBOpenHelper;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static com.example.thegreatestreminder.Constants.SQLConstants.TABLE_NAME;

public class SQLiteReminderRepository implements IReminderRepository {

    private SQLiteDatabase db;

    private final static String KEY_ID = "id";
    private final static String KEY_NAME = "name";
    private final static String KEY_DETAIL = "detail";
    private final static String KEY_DATE = "date";

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
                " ("+KEY_NAME+","+KEY_DETAIL+","+KEY_DATE+")" +
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

    @Override
    public List<Reminder> readAll() {
        return null;
    }

    @Override
    public Reminder get(long reminderId) {
        Cursor cursor = db.query(TABLE_NAME,new String[]{KEY_ID,KEY_NAME,KEY_DETAIL,KEY_DATE},
                KEY_ID+"=?",new String[]{String.valueOf(reminderId)},null,null,null);

        Reminder reminder = null;
        if (cursor.moveToFirst()) {
            reminder = new Reminder(cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(KEY_DETAIL)),
                    new Date(cursor.getLong(cursor.getColumnIndexOrThrow(KEY_DATE))));
            reminder.setId(reminderId);
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }

        return reminder;
    }
}
