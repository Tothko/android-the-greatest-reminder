package com.example.thegreatestreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thegreatestreminder.BusinessEntities.Reminder;
import com.example.thegreatestreminder.BusinessLogic.ReminderService;
import com.example.thegreatestreminder.Utils.Adapters.ReminderArrayAdapter;
import com.example.thegreatestreminder.Utils.Converters.DurationConverter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewMain;
    private Button btnAdd;

    private List<Reminder> reminders;

    private ReminderService reminderService;

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
        loadReminders();
        ReminderArrayAdapter adapter = new ReminderArrayAdapter(this,reminders);

       /* adapter.setOnItemDelete((reminder) -> {
            deleteReminder(reminder);

        });

        adapter.setOnItemEdit((reminder) ->{
            editReminder(reminder);

        });*/

        adapter.notifyDataSetChanged();
    }

    /* -----------Methods for handling data access ---------------- */

    /**
     * Loads all the reminders from the data acces object into reminders arraylist
     */
    private void loadReminders(){
        this.reminders = reminderService.readAll();
       // this.reminders.add(new Reminder());
       // this.reminders.add(new Reminder());
    }

    private int deleteReminder(Reminder reminder){
        int deletedReminders = reminderService.deleteReminder(reminder);
        refreshListView();
        Log.println(Log.DEBUG, "Delete", "You succesfully deleted "+deletedReminders+ " reminders");
        return deletedReminders;

    }

    private void editReminder(Reminder reminder){
        reminderService.editReminder(reminder);
        refreshListView();
    }

    /* -----------Activity Overrides and event handlers---------------- */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("The Greatest Reminder");

        this.reminderService = DependencyFactory.getInstance(this).getReminderService();
        this.loadReminders();
        this.setUpReferences();
        this.setUpListView();
        this.setUpButtonAdd();


        this.refreshListView();
    }
}
