package com.example.thegreatestreminder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.thegreatestreminder.BusinessEntities.Reminder;

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

    }

    /**
     * Refreshes the listview
     * should be called when there are a new data available
     */
    private void refreshListView(){

    }


    /**
     * Array adapter used to draw a custom list item for our listview
     */
    private class ReminderArrayAdapter extends ArrayAdapter<Reminder>{

        public ReminderArrayAdapter(@NonNull Context context, @NonNull ArrayList<Reminder> data) {
            super(context, 0,data);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("The Greatest Reminder");

        this.setUpReferences();
        this.setUpListView();
    }
}
