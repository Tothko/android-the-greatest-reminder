package com.example.thegreatestreminder.DAO.Impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.thegreatestreminder.BusinessEntities.EmailNotification;
import com.example.thegreatestreminder.BusinessEntities.Notification;
import com.example.thegreatestreminder.BusinessEntities.Reminder;
import com.example.thegreatestreminder.BusinessEntities.ReminderFilter;
import com.example.thegreatestreminder.BusinessEntities.SmsNotification;
import com.example.thegreatestreminder.DAO.IReminderRepository;
import com.example.thegreatestreminder.Utils.BitmapUtil;
import com.example.thegreatestreminder.Utils.Helpers.DBOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.example.thegreatestreminder.Constants.SQLConstants.KEY_DATE;
import static com.example.thegreatestreminder.Constants.SQLConstants.KEY_DETAIL;
import static com.example.thegreatestreminder.Constants.SQLConstants.KEY_ID;
import static com.example.thegreatestreminder.Constants.SQLConstants.KEY_NAME;
import static com.example.thegreatestreminder.Constants.SQLConstants.KEY_PHOTO;
import static com.example.thegreatestreminder.Constants.SQLConstants.KEY_RECEIVER;
import static com.example.thegreatestreminder.Constants.SQLConstants.KEY_REMINDER_ID;
import static com.example.thegreatestreminder.Constants.SQLConstants.KEY_TYPE;
import static com.example.thegreatestreminder.Constants.SQLConstants.TABLE_NAME;
import static com.example.thegreatestreminder.Constants.SQLConstants.TABLE_NAME_NOTIF;

public class SQLiteReminderRepository implements IReminderRepository {

    private SQLiteDatabase db;



    public  SQLiteReminderRepository(Context ctx){
        DBOpenHelper openHelper = new DBOpenHelper(ctx);
        db = openHelper.getWritableDatabase();
    }

    @Override
    public void deleteReminder(long reminderId) {
        final String DELETE_STMT_NOTIF = "DELETE FROM " + TABLE_NAME_NOTIF +
                " WHERE " + KEY_REMINDER_ID + " = ?";

        SQLiteStatement stmt = db.compileStatement(DELETE_STMT_NOTIF);

        stmt.bindLong(1,reminderId);

        stmt.executeUpdateDelete();

        final String DELETE_STMT = "DELETE FROM " + TABLE_NAME +
                " WHERE " + KEY_ID + " = ?";

        stmt = db.compileStatement(DELETE_STMT);

        stmt.bindLong(1,reminderId);

        stmt.executeUpdateDelete();
    }

    @Override
    public void editReminder(Reminder reminder) {

    }

    @Override
    public Reminder addReminder(Reminder reminder) {
        final String INSERT_STMT = "INSERT INTO " + TABLE_NAME +
                " ("+KEY_NAME+","+KEY_DETAIL+","+KEY_DATE+","+KEY_PHOTO+")" +
                " VALUES (?,?,?,?)";

        SQLiteStatement stmt = db.compileStatement(INSERT_STMT);

        int i = 1;
        stmt.bindString(i++,reminder.getName());
        stmt.bindString(i++,reminder.getDetail());
        stmt.bindLong(i++,reminder.getTriggerDateTime().getTime());

        if(reminder.getPhoto() != null)
            stmt.bindBlob(i++, BitmapUtil.toBytes(reminder.getPhoto()));
        else
            stmt.bindNull(i++);

        long id = stmt.executeInsert();
        reminder.setId(id);

        if(reminder.hasNotifications())
        {
            final String INSERT_NOTIF_STMT = "INSERT INTO " + TABLE_NAME_NOTIF +
                    " (" + KEY_REMINDER_ID + "," + KEY_TYPE + "," + KEY_RECEIVER + ")" +
                    " VALUES (?,?,?)";

            stmt = db.compileStatement(INSERT_NOTIF_STMT);

            for (Notification notif:
                 reminder.getNotifications()) {
                int typeAsInt = notif.getType() == Notification.Type.EMAIL ? 0 : 1;
                i = 1;
                stmt.bindLong(i++,id);
                stmt.bindLong(i++,typeAsInt);
                stmt.bindString(i++,notif.getReceiver());

                stmt.executeInsert();
            }
        }


        return reminder;
    }

    @Override
    public ArrayList<Reminder> readAll(ReminderFilter filter) {
        ArrayList<Reminder> list = new ArrayList<>();

        Date now = new Date();
        String selection = filter.show == ReminderFilter.ShowType.ACTIVE ? KEY_DATE+">?" : null;
        String [] selectionArgs = filter.show == ReminderFilter.ShowType.ACTIVE ? new String[] { String.valueOf(now.getTime()) } : null;
        String orderBy = filter.order == ReminderFilter.OrderType.EXPIRATION ? KEY_DATE : KEY_NAME;

        if(!filter.asc)
            orderBy += " DESC";

        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID },
                selection, selectionArgs, null, null, orderBy);

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(0);
                list.add(get(id));
            } while (cursor.moveToNext());
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }

        return list;
    }

    private void loadNotifications(Reminder reminder){
        try {
            Cursor cursor = db.query(TABLE_NAME_NOTIF, new String[]{KEY_REMINDER_ID, KEY_TYPE, KEY_RECEIVER},
                    KEY_REMINDER_ID + "=?", new String[]{String.valueOf(reminder.getId())}, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    int type = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_TYPE));
                    String receiver = cursor.getString(cursor.getColumnIndexOrThrow(KEY_RECEIVER));
                    if(type == 0)
                        reminder.addNotification(new EmailNotification(receiver));
                    else
                        reminder.addNotification(new SmsNotification(receiver));
                } while (cursor.moveToNext());
            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Reminder get(long reminderId) {
        Cursor cursor = db.query(TABLE_NAME,new String[]{KEY_ID,KEY_NAME,KEY_DETAIL,KEY_DATE,KEY_PHOTO},
                KEY_ID+"=?",new String[]{String.valueOf(reminderId)},null,null,null);

        Reminder reminder = null;
        if (cursor.moveToFirst()) {
            reminder = new Reminder(cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(KEY_DETAIL)),
                    new Date(cursor.getLong(cursor.getColumnIndexOrThrow(KEY_DATE))));
            reminder.setPhoto(BitmapUtil.fromBytes(cursor.getBlob(cursor.getColumnIndexOrThrow(KEY_PHOTO))));
            reminder.setId(reminderId);
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }

        if(reminder != null)
            this.loadNotifications(reminder);

        return reminder;
    }
}
