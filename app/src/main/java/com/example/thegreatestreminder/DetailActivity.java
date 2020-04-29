package com.example.thegreatestreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thegreatestreminder.BusinessEntities.Reminder;
import com.example.thegreatestreminder.BusinessLogic.ReminderService;
import com.example.thegreatestreminder.Utils.Converters.DateTimeConverter;
import com.example.thegreatestreminder.Utils.Helpers.ControlsHelper;

import java.text.ParseException;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    EditText etName;
    EditText etDetail;
    EditText etDate;
    EditText etTime;

    Button btnSave;
    Button btnAddNotification;
    Button btnAddAction;

    ReminderService reminderService;

    private void setupControlsReferences(){
        etName = findViewById(R.id.etReminderName);
        etDetail = findViewById(R.id.etReminderDetail);
        etDate = findViewById(R.id.etReminderDate);
        etTime = findViewById(R.id.etReminderTime);

        btnSave = findViewById(R.id.btnSaveDetail);
        btnAddNotification = findViewById(R.id.btnAddNotification);
        btnAddAction = findViewById(R.id.btnAddAction);
    }

    private Reminder getReminder() throws ParseException {
        String name = this.etName.getText().toString();
        String detail = this.etDetail.getText().toString();
        Date triggerDate = DateTimeConverter.dateTimeFromString(this.etDate.getText().toString(),this.etTime.getText().toString());
        return new Reminder(name,detail,triggerDate);
    }

    private void onSaveClick(View v){
        try {
            this.reminderService.addReminder(getReminder());
            this.finish();
        } catch (ParseException e) {
            e.printStackTrace();
            Toast errorToast = Toast.makeText(this,R.string.invalid_date_error,Toast.LENGTH_SHORT);
            errorToast.show();
        }
    }

    private void onAddNotifClick(View v){
        AddNotificationDialog dialog = new AddNotificationDialog(this);
        dialog.show();
    }

    private void onAddActionClick(View v){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        this.reminderService = DependencyFactory.getInstance(this).getReminderService();

        setupControlsReferences();

        btnSave.setOnClickListener(this::onSaveClick);
        btnAddNotification.setOnClickListener(this::onAddNotifClick);
        btnAddAction.setOnClickListener(this::onAddActionClick);

        ControlsHelper.setupEditDateBehaviour(this,etDate);
        ControlsHelper.setupEditTimeBehaviour(this,etTime);
    }
}
