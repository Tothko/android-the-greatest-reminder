package com.example.thegreatestreminder.DAO;

import com.example.thegreatestreminder.DAO.Impl.SQLiteReminderRepository;

public class DependencyFactory {

    public static IReminderRepository getReminderRepository(){
        return new SQLiteReminderRepository();
    }
}
