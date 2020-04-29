package com.example.thegreatestreminder;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;

public class AddNotificationDialog extends Dialog implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button btnSave;
    Spinner spinner;

    Context ctx;

    public AddNotificationDialog(@NonNull Context context) {
        super(context);
        ctx = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.notification_dialog);
        btnSave = findViewById(R.id.btnSaveNotification);
        btnSave.setOnClickListener(this);

        spinner = findViewById(R.id.spinnerNotifType);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ctx, R.array.notifTypes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
