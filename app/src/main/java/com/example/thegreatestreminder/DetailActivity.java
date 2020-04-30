package com.example.thegreatestreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thegreatestreminder.BusinessEntities.Notification;
import com.example.thegreatestreminder.BusinessEntities.Reminder;
import com.example.thegreatestreminder.BusinessEntities.ReminderAction;
import com.example.thegreatestreminder.BusinessLogic.ReminderService;
import com.example.thegreatestreminder.Utils.Adapters.NotificationArrayAdapter;
import com.example.thegreatestreminder.Utils.Converters.DateTimeConverter;
import com.example.thegreatestreminder.Utils.Helpers.ControlsHelper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    EditText etName;
    EditText etDetail;
    EditText etDate;
    EditText etTime;

    Button btnSave;
    Button btnAddNotification;
    Button btnAddAction;

    ListView lvNotif;
    ListView lvActions;

    ReminderService reminderService;
    List<Notification> notificationList;
    List<ReminderAction> actionList;

    private void setupControlsReferences(){
        etName = findViewById(R.id.etReminderName);
        etDetail = findViewById(R.id.etReminderDetail);
        etDate = findViewById(R.id.etReminderDate);
        etTime = findViewById(R.id.etReminderTime);

        btnSave = findViewById(R.id.btnSaveDetail);
        btnAddNotification = findViewById(R.id.btnAddNotification);
        btnAddAction = findViewById(R.id.btnAddAction);

        lvActions = findViewById(R.id.lvDetailActions);
        lvNotif = findViewById(R.id.lvDetailNotifications);
    }

    private Reminder getReminder() throws ParseException {
        String name = this.etName.getText().toString();
        String detail = this.etDetail.getText().toString();
        Date triggerDate = DateTimeConverter.dateTimeFromString(this.etDate.getText().toString(),this.etTime.getText().toString());
        Reminder reminder = new Reminder(name,detail,triggerDate);

        for (Notification n :
                notificationList) {
            reminder.addNotification(n);
        }

        return reminder;
    }

    private void onSaveClick(View v){
        try {
            this.reminderService.addReminder(getReminder());
            this.finish();
        } catch (ParseException e) {
            e.printStackTrace();
            Toast errorToast = Toast.makeText(this,R.string.invalid_date_error,Toast.LENGTH_SHORT);
            errorToast.show();
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            Toast errorToast = Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT);
            errorToast.show();
        }
    }

    private void checkPermisions(Notification notification){
        if(notification.getType() == Notification.Type.SMS) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M
                    && checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {

                String[] permissions = {Manifest.permission.SEND_SMS};

                requestPermissions(permissions, 1);
            }
        }
        else{

        }
    }

    private void onAddNotifClick(View v){
        AddNotificationDialog dialog = new AddNotificationDialog(this);
        dialog.show();
        dialog.setOnDismissListener((DialogInterface dialogInterface) -> {
            Notification notif = dialog.getNotification();
            if(notif != null){
                checkPermisions(notif);
                notificationList.add(notif);
                lvNotif.setAdapter(new NotificationArrayAdapter(this,notificationList));
            }
        });
    }

    private void onAddActionClick(View v){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        this.reminderService = DependencyFactory.getInstance(this).getReminderService();
        this.notificationList = new ArrayList<>();
        this.actionList = new ArrayList<>();

        setupControlsReferences();

        btnSave.setOnClickListener(this::onSaveClick);
        btnAddNotification.setOnClickListener(this::onAddNotifClick);
        btnAddAction.setOnClickListener(this::onAddActionClick);

        lvNotif.setAdapter(new NotificationArrayAdapter(this,notificationList));

        ControlsHelper.setupEditDateBehaviour(this,etDate);
        ControlsHelper.setupEditTimeBehaviour(this,etTime);
    }
}
