package com.example.runningman.activities.feed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.runningman.R;
import com.example.runningman.activities.groups.GroupsActivity;
import com.example.runningman.activities.auth.MainActivity;
import com.example.runningman.activities.profile.ProfileActivity;
import com.example.runningman.fragments.FeedListFragment;
import com.example.runningman.fragments.SpecificActivityFragment;
import com.example.runningman.model.DataHolder;

public class FeedActivity extends AppCompatActivity implements FeedListFragment.OnActivitySelectedInterface {
    public static final String TAG = FeedActivity.class.getSimpleName();
    public static final String LIST_FRAGMENT = "list_fragment";
    public static final String SPECIFICACTIVITY_FRAGMENT = "specificactivity_fragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        DataHolder.getInstance().updateGroups(FeedActivity.this);
        DataHolder.getInstance().updateFeedActivities(FeedActivity.this);

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final Button activityFormButton = findViewById(R.id.activityFormButton);
        activityFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedActivity.this, ActivityFormActivity.class);
                startActivity(intent);
            }
        });

        ImageButton navFeed = findViewById(R.id.navFeed);
        navFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedActivity.this, FeedActivity.class);
                startActivity(intent);
            }
        });
        ImageButton navGroups = findViewById(R.id.navGroups);
        navGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedActivity.this, GroupsActivity.class);
                startActivity(intent);
            }
        });
        ImageButton navProfile = findViewById(R.id.navProfile);
        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        FeedListFragment savedFragment = (FeedListFragment) getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT);
        if (savedFragment == null){
            FeedListFragment fragment = new FeedListFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.feedListLinearLayout, fragment, LIST_FRAGMENT);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void OnListActivitySelected(int index) {

        SpecificActivityFragment fragment = new SpecificActivityFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(SpecificActivityFragment.KEY_ACTIVITY_INDEX, index);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.feedListLinearLayout, fragment, SPECIFICACTIVITY_FRAGMENT);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
