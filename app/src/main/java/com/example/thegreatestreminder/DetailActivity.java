package com.example.thegreatestreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class DetailActivity extends AppCompatActivity {

    EditText etName;
    EditText etDetail;
    EditText etDate;
    EditText etTime;

    Button btnSave;
    Button btnAddNotification;
    Button btnAddAction;

    DatePickerDialog picker;

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

        setupEditDateBehaviour();
    }

    private void setupEditDateBehaviour() {
        etDate.setInputType(InputType.TYPE_CLASS_DATETIME);

        etDate.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            // date picker dialog
            picker = new DatePickerDialog(DetailActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> etDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1), year, month, day);
            picker.show();
        });
    }
}
