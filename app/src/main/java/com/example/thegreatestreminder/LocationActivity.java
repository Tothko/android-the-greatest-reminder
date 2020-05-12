package com.example.thegreatestreminder;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class LocationActivity extends AppCompatActivity {

    Context ctx;
    private FusedLocationProviderClient fusedLocationClient;

    Button btnSave;
    Button btnCancel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.location_dialog);
        btnSave = findViewById(R.id.btnSaveLocation);
        btnCancel = findViewById(R.id.btnCancel);


    }

}
