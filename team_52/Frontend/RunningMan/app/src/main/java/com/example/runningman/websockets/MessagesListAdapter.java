package com.example.runningman.websockets;

import android.widget.BaseAdapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.runningman.R;

public class MessagesListAdapter extends BaseAdapter {
    private Context context;
    private List<Message> messagesItems;

    public MessagesListAdapter(Context context, List<Message> navDrawerItems) {
        this.context = context;
        this.messagesItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return messagesItems.size();
    }

    @Override
    public Object getItem(int position) {
        return messagesItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Message m = messagesItems.get(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        // Identifying the message owner
        // message belongs to you, so load the right aligned layout
        // message belongs to other person, load the left aligned layout
        if (messagesItems.get(position).isSelf())
            convertView = mInflater.inflate(R.layout.list_item_message_right,
                    null);
        else convertView = mInflater.inflate(R.layout.list_item_message_left,
                null);

        TextView lblFrom = convertView.findViewById(R.id.lblMsgFrom);
        TextView txtMsg = convertView.findViewById(R.id.txtMsg);

        txtMsg.setText(m.getMessage());
        lblFrom.setText(m.getFromName());

        return convertView;
    }
}
