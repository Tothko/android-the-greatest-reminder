package com.example.thegreatestreminder;

import android.content.Context;

import com.example.thegreatestreminder.BusinessLogic.ReminderService;
import com.example.thegreatestreminder.DAO.IReminderRepository;
import com.example.thegreatestreminder.DAO.Impl.SQLiteReminderRepository;

public class DependencyFactory {

    private Context ctx;
    private static DependencyFactory instance;

    public static DependencyFactory getInstance(Context ctx){
        if(instance == null){
            instance = new DependencyFactory();
            instance.ctx = ctx;
        }
        return instance;
    }

    public IReminderRepository getReminderRepository(){
        return new SQLiteReminderRepository(ctx);
    }

    public ReminderService getReminderService(){
        return new ReminderService(ctx,getReminderRepository());
    }
}
