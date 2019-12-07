package com.example.runningman.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.runningman.R;
import com.example.runningman.model.DataHolder;

import org.json.JSONException;
import org.json.JSONObject;

public class SpecificActivityFragment extends Fragment {
    public static final String KEY_ACTIVITY_INDEX = "activity_index";

    private ImageView specificProfileImageView;
    private TextView specificUsernameTextView;
    private TextView specificActivityNameTextView;
    private TextView specificActivityDistanceTextView;
    private TextView specificActivityDurationTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_specificactivity, container, false);

        specificProfileImageView = view.findViewById(R.id.specificProfileImage);
        specificUsernameTextView = view.findViewById(R.id.username);
        specificActivityNameTextView = view.findViewById(R.id.specificActivityName);
        specificActivityDistanceTextView = view.findViewById(R.id.distanceItem);
        specificActivityDurationTextView = view.findViewById(R.id.durationItem);

        try {
            JSONObject activityJSON = DataHolder.getInstance().getFeedActivities().getJSONObject(getArguments().getInt(KEY_ACTIVITY_INDEX));
            specificProfileImageView.setImageResource(R.drawable.nav_profile);
            specificUsernameTextView.setText(activityJSON.getString("Username"));
            specificActivityNameTextView.setText(activityJSON.getString("type"));
            specificActivityDistanceTextView.setText(String.format("%f",activityJSON.getDouble("distance")));
            specificActivityDurationTextView.setText(String.format("%s - to - %s", activityJSON.getString("starttime"), activityJSON.getString("endtime")));

        } catch (JSONException e){
            e.printStackTrace();
        }

        return view;
    }


}
