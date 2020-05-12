package com.example.thegreatestreminder.Utils.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thegreatestreminder.BusinessEntities.Reminder;
import com.example.thegreatestreminder.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Array adapter used to draw a custom list item for our listview
 */
public class ReminderArrayAdapter extends ArrayAdapter<Reminder> {

    public interface ListItemCallback{
        void execute(Reminder reminder);
    }

    private List<Reminder> reminders;
    private ListItemCallback deleteCallback;
    private ListItemCallback editCallback;

    public void setOnItemDelete(ListItemCallback cb){
        this.deleteCallback = cb;
    }

    public void setOnItemEdit(ListItemCallback cb){
        this.editCallback = cb;
    }

    /**
     *  Sets up a view with listeners and entity data
     * @param view
     * @param entity
     */
    private void setupViews(View view, Reminder entity){
        TextView txtMain = view.findViewById(R.id.txtListItemMain);
        TextView txtDetail = view.findViewById(R.id.txtListItemDetail);
        Button btnDelete = view.findViewById(R.id.btnDeleteRecord);
        Button btnEdit = view.findViewById(R.id.btnEditRecord);

        btnDelete.setOnClickListener((View v) -> {
            if(deleteCallback != null)
                deleteCallback.execute(entity);
        });

        btnEdit.setOnClickListener((View v) -> {
            if(editCallback != null)
                editCallback.execute(entity);
        });

        txtMain.setText(entity.getName());
        txtDetail.setText(entity.getDetail());
    }

    public ReminderArrayAdapter(@NonNull Context context, @NonNull List<Reminder> data) {
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