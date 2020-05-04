package com.example.thegreatestreminder.Utils.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.thegreatestreminder.BusinessEntities.Reminder;
import com.example.thegreatestreminder.R;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.MyViewHolder> {

    private List<Reminder> RemindersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, detail, date;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.Name);
            detail = (TextView) view.findViewById(R.id.Detail);
            date = (TextView) view.findViewById(R.id.Date);
        }
    }


    public ReminderAdapter(List<Reminder> RemindersList) {
        this.RemindersList = RemindersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reminder_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Reminder reminder = RemindersList.get(position);
        holder.name.setText(reminder.getName());
        holder.detail.setText(reminder.getDetail());
        holder.date.setText(reminder.getTriggerDateTime().toString());
    }

    @Override
    public int getItemCount() {
        return RemindersList.size();
    }


}