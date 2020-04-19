package com.example.thegreatestreminder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import com.example.thegreatestreminder.Utils.Converters.DurationConverter;

import java.time.Duration;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewMain;
    private ArrayList<Reminder> reminders;

    /**
     * Finds and assigns view by id for all the references used in this activity
     */
    private void setUpReferences(){
        listViewMain = findViewById(R.id.lvMain);
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
        listViewMain.setAdapter(adapter);
    }


    /**
     * Array adapter used to draw a custom list item for our listview
     */
    private class ReminderArrayAdapter extends ArrayAdapter<Reminder>{

        private ArrayList<Reminder> reminders;

        private void setupViews(View view,Reminder entity){
            TextView txtMain = view.findViewById(R.id.txtListItemMain);
            TextView txtDetail = view.findViewById(R.id.txtListItemDetail);
            Button btnDelete = view.findViewById(R.id.btnDeleteRecord);
            Button btnEdit = view.findViewById(R.id.btnEditRecord);

            btnDelete.setOnClickListener((View v) -> {
                deleteReminder(entity);
            });

            btnEdit.setOnClickListener((View v) -> {
                editReminder(entity);
            });

            txtMain.setText(entity.getName());
            txtDetail.setText(entity.getDetail());
        }

        public ReminderArrayAdapter(@NonNull Context context, @NonNull ArrayList<Reminder> data) {
            super(context, 0,data);
            this.reminders = data;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
            //Inflate view only if it is not existing, otherwise waste of resources
            if(view == null)
            {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.main_listview_item,null);
            }

            Reminder entity = this.reminders.get(position);

            this.setupViews(view,entity);

            return view;
        }
    }

    /* -----------Methods for handling data access ---------------- */

    /**
     * Loads all the reminders from the data acces object into reminders arraylist
     */
    private void loadReminders(){
        this.reminders = new ArrayList<>();
        this.reminders.add(new Reminder());
        this.reminders.add(new Reminder());
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

        this.loadReminders();
        this.refreshListView();
    }
}
