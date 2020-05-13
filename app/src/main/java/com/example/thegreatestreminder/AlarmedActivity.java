package com.example.thegreatestreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thegreatestreminder.BusinessEntities.Reminder;
import com.example.thegreatestreminder.BusinessLogic.ReminderService;

public class AlarmedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmed);

        Intent i = getIntent();
        long reminderId = i.getLongExtra("reminderId",0);

        ReminderService rs = DependencyFactory.getInstance(this).getReminderService();

        Reminder reminder = rs.getReminder(reminderId);

        TextView txtName = findViewById(R.id.txtReminderName);
        TextView txtDetail = findViewById(R.id.txtReminderDetail);

        txtName.setText(reminder.getName());
        txtDetail.setText(reminder.getDetail());

        Bitmap photo = reminder.getPhoto();
        if(photo != null){
            ImageView img = findViewById(R.id.imgAlarmed);
            img.setImageBitmap(reminder.getPhoto());
        }
    }
}
