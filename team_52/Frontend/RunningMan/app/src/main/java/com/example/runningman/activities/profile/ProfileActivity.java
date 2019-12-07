package com.example.runningman.activities.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.runningman.R;
import com.example.runningman.activities.feed.FeedActivity;
import com.example.runningman.activities.groups.GroupsActivity;
import com.example.runningman.fragments.ProfileGroupsListFragment;
import com.example.runningman.fragments.SpecificProfileGroupFragment;
import com.example.runningman.model.DataHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity implements ProfileGroupsListFragment.OnGroupSelectedInterface {
    public static final String TAG = ProfileActivity.class.getSimpleName();
    public static final String LIST_FRAGMENT = "list_fragment";
    public static final String SPECIFICGROUP_FRAGMENT = "specificgroup_fragment";

    //searches database for given group name (Mock Test)
    public JSONObject getResponse(String email) throws JSONException {
        JSONObject response = new JSONObject();
        response.put(email, Boolean.valueOf(true));
        return response;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageButton navFeed = findViewById(R.id.navFeed);
        navFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, FeedActivity.class);
                startActivity(intent);
            }
        });
        ImageButton navGroups = findViewById(R.id.navGroups);
        navGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, GroupsActivity.class);
                startActivity(intent);
            }
        });
        ImageButton navProfile = findViewById(R.id.navProfile);
        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        Button editProfileButton = findViewById(R.id.editProfileButton);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });


        LinearLayout profileLinearLayout = findViewById(R.id.profileLinearLayout);

        TextView firstName = new TextView(ProfileActivity.this);
        firstName.setId(View.generateViewId());
        firstName.setText(String.format("First Name: %s", DataHolder.getInstance().getFirstName()));
        profileLinearLayout.addView(firstName);

        TextView lastName = new TextView(ProfileActivity.this);
        lastName.setId(View.generateViewId());
        lastName.setText(String.format("Last Name: %s", DataHolder.getInstance().getLastName()));
        profileLinearLayout.addView(lastName);

        JSONArray activities = DataHolder.getInstance().getActivities();
        TextView numActivities = new TextView(ProfileActivity.this);
        numActivities.setId(View.generateViewId());
        String activityLength;
        if(activities == null){
            activityLength = "0";
        } else {
            activityLength = "" + activities.length();
        }
        numActivities.setText(String.format("Activities: %s", activityLength));
        profileLinearLayout.addView(numActivities);

        TextView bio = new TextView(ProfileActivity.this);
        bio.setId(View.generateViewId());
        bio.setText(String.format("Bio: %s", DataHolder.getInstance().getBio()));
        profileLinearLayout.addView(bio);

        TextView email = new TextView(ProfileActivity.this);
        email.setId(View.generateViewId());
        email.setText(String.format("Email: %s", DataHolder.getInstance().getEmail()));
        profileLinearLayout.addView(email);

        TextView password = new TextView(ProfileActivity.this);
        password.setId(View.generateViewId());
        password.setText(String.format("Password: %s", DataHolder.getInstance().getPassword()));
        profileLinearLayout.addView(password);


        //Fragment Handling
        ProfileGroupsListFragment savedFragment = (ProfileGroupsListFragment) getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT);
        if (savedFragment == null) {
            ProfileGroupsListFragment fragment = new ProfileGroupsListFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.groupListLinearLayout, fragment, LIST_FRAGMENT);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onListGroupSelected(int index) {
        SpecificProfileGroupFragment fragment = new SpecificProfileGroupFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(SpecificProfileGroupFragment.KEY_GROUP_INDEX, index);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.groupListLinearLayout, fragment, SPECIFICGROUP_FRAGMENT);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public boolean emailExists(String email, ProfileActivity profileActivity) throws JSONException {

        //Does not work because .getResponse has not been implemented
        return profileActivity.getResponse(email).getBoolean("emailFound");

    }
}


