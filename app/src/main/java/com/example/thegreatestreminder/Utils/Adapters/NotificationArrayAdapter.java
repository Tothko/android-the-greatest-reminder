package com.example.thegreatestreminder.Utils.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thegreatestreminder.BusinessEntities.Notification;
import com.example.thegreatestreminder.BusinessEntities.Reminder;
import com.example.thegreatestreminder.R;

import java.util.List;

public class NotificationArrayAdapter extends ArrayAdapter<Notification> {

    List<Notification> notifications;

    public NotificationArrayAdapter(@NonNull Context context, @NonNull List<Notification> objects) {
        super(context, 0, objects);
        notifications = objects;
    }

    private void setupViews(View view, Notification entity){
        TextView txtMain = view.findViewById(R.id.txtListItemMain);
        TextView txtDetail = view.findViewById(R.id.txtListItemDetail);

        Notification.Type type = entity.getType();
        String name = type == Notification.Type.EMAIL ? "EMAIL" : "SMS";
        txtMain.setText(name);
        txtDetail.setText(entity.getReceiver());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        //Inflate view only if it is not existing, otherwise waste of resources
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.notif_listview_item,null);
        }

        Notification entity = this.notifications.get(position);

        this.setupViews(view,entity);

        return view;
    }
}
