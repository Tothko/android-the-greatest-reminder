package com.example.thegreatestreminder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thegreatestreminder.BusinessEntities.Reminder;
import com.example.thegreatestreminder.Utils.Adapters.ReminderArrayAdapter;
import com.example.thegreatestreminder.Utils.Converters.DurationConverter;

import java.time.Duration;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewMain;
    private ArrayList<Reminder> reminders;

    private Button btnAdd;

    //Constants for child activities
    final static int REMIND_DETAIL = 123;

    /**
     * Finds and assigns view by id for all the references used in this activity
     */
    private void setUpReferences(){
        listViewMain = findViewById(R.id.lvMain);

        btnAdd = findViewById(R.id.btnAddRecord);
    }

    private void setUpButtonAdd(){
        btnAdd.setOnClickListener((View v) -> {
            Intent x = new Intent(this, DetailActivity.class);
            startActivityForResult(x,REMIND_DETAIL);
        });
    }

    /* ---------------Methods for handling listview-------------- */

    /**
     * Sets up listview
     * - onItemClickListener
      */
    private void setUpListView(){
        listViewMain.setOnItemClickListener((parent,view,position,id) -> {
            Reminder entity = reminders.get(position);
            Duration durationUntilFired = entity.getDurationUntilFired();
            String durationAsString = DurationConverter.durationToString(durationUntilFired);
            String text = String.format("It takes \'%s\' until this will be reminded",durationAsString);
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * Refreshes the listview
     * should be called when there are a new data available
     */
    private void refreshListView(){
        ReminderArrayAdapter adapter = new ReminderArrayAdapter(this,reminders);
        adapter.setOnItemDelete((reminder) -> {
            deleteReminder(reminder);
        });

        adapter.setOnItemEdit((reminder) ->{
            editReminder(reminder);
        });

        listViewMain.setAdapter(adapter);
    }

    /* -----------Methods for handling data access ---------------- */

    /**
     * Loads all the reminders from the data acces object into reminders arraylist
     */
    private void loadReminders(){
        this.reminders = new ArrayList<>();
       // this.reminders.add(new Reminder());
       // this.reminders.add(new Reminder());
    }

    private void deleteReminder(Reminder reminder){

    }

    private void editReminder(Reminder reminder){

    }

    /* -----------Activity Overrides and event handlers---------------- */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("The Greatest Reminder");

        this.setUpReferences();
        this.setUpListView();
        this.setUpButtonAdd();;

        this.loadReminders();
        this.refreshListView();
    }
}
