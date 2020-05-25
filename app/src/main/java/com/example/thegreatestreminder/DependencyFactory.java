package com.example.thegreatestreminder;

import android.content.Context;

import com.example.thegreatestreminder.BusinessLogic.ReminderService;
import com.example.thegreatestreminder.DAO.IReminderRepository;
import com.example.thegreatestreminder.DAO.Impl.SQLiteReminderRepository;

public class DependencyFactory {

    private IReminderRepository repo;
    private ReminderService service;
    private static DependencyFactory instance;

    private DependencyFactory(Context ctx){
        this.repo = new SQLiteReminderRepository(ctx);
        this.service = new ReminderService(ctx,repo);
    }

    public static DependencyFactory getInstance(Context ctx){
        if(instance == null){
            instance = new DependencyFactory(ctx);
        }
        return instance;
    }

    public IReminderRepository getReminderRepository(){
        return repo;
    }

    public ReminderService getReminderService(){
        return service;
    }
}
