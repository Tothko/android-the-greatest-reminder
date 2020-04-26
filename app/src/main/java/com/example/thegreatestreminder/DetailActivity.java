package com.example.thegreatestreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.thegreatestreminder.Utils.Helpers.ControlsHelper;

public class DetailActivity extends AppCompatActivity {

    EditText etName;
    EditText etDetail;
    EditText etDate;
    EditText etTime;

    Button btnSave;
    Button btnAddNotification;
    Button btnAddAction;

    private void setupControlsReferences(){
        etName = findViewById(R.id.etReminderName);
        etDetail = findViewById(R.id.etReminderDetail);
        etDate = findViewById(R.id.etReminderDate);
        etTime = findViewById(R.id.etReminderTime);

        btnSave = findViewById(R.id.btnSaveDetail);
        btnAddNotification = findViewById(R.id.btnAddNotification);
        btnAddAction = findViewById(R.id.btnAddAction);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setupControlsReferences();

        ControlsHelper.setupEditDateBehaviour(this,etDate);
        ControlsHelper.setupEditTimeBehaviour(this,etTime);
    }
}
