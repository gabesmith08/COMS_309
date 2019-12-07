package com.example.runningman.activities.groups;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.runningman.R;
import com.example.runningman.activities.profile.ProfileActivity;
import com.example.runningman.activities.feed.FeedActivity;
import com.example.runningman.fragments.BrowseGroupsListFragment;
import com.example.runningman.fragments.SpecificAllGroupFragment;

import org.json.JSONException;
import org.json.JSONObject;

public class GroupsActivity extends AppCompatActivity implements BrowseGroupsListFragment.OnGroupSelectedInterface {
    public static final String LIST_FRAGMENT = "list_fragment";
    public static final String SPECIFICGROUP_FRAGMENT = "specificgroup_fragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        ImageButton navFeed = findViewById(R.id.navFeed);
        navFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupsActivity.this, FeedActivity.class);
                startActivity(intent);
            }
        });
        ImageButton navGroups = findViewById(R.id.navGroups);
        navGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupsActivity.this, GroupsActivity.class);
                startActivity(intent);
            }
        });
        ImageButton navProfile = findViewById(R.id.navProfile);
        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        final Button createGroupButton = findViewById(R.id.createGroupsButton);
        createGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupsActivity.this, CreateGroupActivity.class);
                startActivity(intent);
            }
        });
        final Button groupChatButton = findViewById(R.id.groupChatButton);
        groupChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupsActivity.this, WebsocketTestActivity.class);
                startActivity(intent);
            }
        });



        //Fragment Handling
        BrowseGroupsListFragment savedFragment = (BrowseGroupsListFragment) getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT);
        if (savedFragment == null) {
            BrowseGroupsListFragment fragment = new BrowseGroupsListFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.groupListLinearLayout, fragment, LIST_FRAGMENT);
            fragmentTransaction.commit();
        }

    }

    //searches database for given group name (Mock Test)
    public JSONObject getResponse(String groupName) throws JSONException {
        JSONObject response = new JSONObject();
        response.put(groupName, Boolean.valueOf (true));
        return response;
    }

    @Override
    public void onListGroupSelected(int index) {


        SpecificAllGroupFragment fragment = new SpecificAllGroupFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(SpecificAllGroupFragment.KEY_GROUP_INDEX, index);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.groupListLinearLayout, fragment, SPECIFICGROUP_FRAGMENT);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    public boolean findGroup(String groupName, GroupsActivity groupsActivity) throws JSONException {

        //Does not work because .getResponse has not been implemented
        return groupsActivity.getResponse(groupName).getBoolean("groupFound");

    }
}
