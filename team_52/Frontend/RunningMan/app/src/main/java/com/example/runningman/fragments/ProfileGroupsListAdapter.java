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

public class ProfileGroupsListAdapter extends RecyclerView.Adapter {

    private final ProfileGroupsListFragment.OnGroupSelectedInterface mListener;

    public ProfileGroupsListAdapter(ProfileGroupsListFragment.OnGroupSelectedInterface listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_list_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        if (DataHolder.getInstance().getUserGroups() == null) {
            return 0;
        }
        return DataHolder.getInstance().getUserGroups().length();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView groupImageView;
        private TextView groupNameTextView;
        private TextView groupMemberTextView;
        private TextView groupLocationTextView;
        private int mIndex;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            groupImageView = itemView.findViewById(R.id.groupImage2);
            groupNameTextView = itemView.findViewById(R.id.groupName);
            groupMemberTextView = itemView.findViewById(R.id.membersItem);
            groupLocationTextView = itemView.findViewById(R.id.locationItem);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position) {
            mIndex = position;
            try {
                groupImageView.setImageResource(R.drawable.nav_groups);
                JSONObject groupJSON = DataHolder.getInstance().getUserGroups().getJSONObject(position);
                groupNameTextView.setText(groupJSON.getString("name"));
                String numMembers = "" + groupJSON.getJSONArray("userIds").length();
                groupMemberTextView.setText(numMembers);
                String location = "" + groupJSON.getInt("zip");
                groupLocationTextView.setText(location);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {
            mListener.onListGroupSelected(mIndex);
        }
    }
}