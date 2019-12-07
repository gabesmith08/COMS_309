package com.example.runningman.fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.runningman.R;
import com.example.runningman.model.DataHolder;

import org.json.JSONException;
import org.json.JSONObject;

public class FeedListAdapter extends RecyclerView.Adapter {
    private final FeedListFragment.OnActivitySelectedInterface mListener;

    public FeedListAdapter(FeedListFragment.OnActivitySelectedInterface listener){
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        if (DataHolder.getInstance().getFeedActivities()==null){
            return 0;
        }
        return DataHolder.getInstance().getFeedActivities().length();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView profileImageView;
        private TextView activityNameView;
        private TextView activityMilesView;
        private TextView activityDurationView;
        private TextView activityUserName;
        private int mIndex;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImageView = itemView.findViewById(R.id.profileImage);
            activityNameView = itemView.findViewById(R.id.activityName);
            activityMilesView = itemView.findViewById(R.id.milesItem);
            activityDurationView = itemView.findViewById(R.id.durationItem);
            activityUserName = itemView.findViewById(R.id.username);

            itemView.setOnClickListener(this);
        }

        public void bindView(int position){
            mIndex = position;
            try {
                profileImageView.setImageResource(R.drawable.nav_profile);
                JSONObject activityJSON = DataHolder.getInstance().getFeedActivities().getJSONObject(position);
                activityNameView.setText(activityJSON.getString("type"));
                activityMilesView.setText(String.format("%f",activityJSON.getDouble("distance")));
                activityDurationView.setText(String.format("%s - to - %s", activityJSON.getString("starttime"), activityJSON.getString("endtime")));
                activityUserName.setText(activityJSON.getString("Username"));
            }
            catch(JSONException e){
                e.printStackTrace();
            }
        }
        @Override
        public void onClick(View v) {
            mListener.OnListActivitySelected(mIndex);
        }
    }
}
