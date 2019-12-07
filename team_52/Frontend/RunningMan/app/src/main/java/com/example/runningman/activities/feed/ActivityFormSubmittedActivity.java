package com.example.runningman.activities.feed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.runningman.R;
import com.example.runningman.model.DataHolder;

public class ActivityFormSubmittedActivity extends AppCompatActivity {
    public static final String TAG = ActivityFormSubmittedActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_form_submitted);

        //Since an Activity was added, we now need to update the FeedActivities
        DataHolder.getInstance().updateFeedActivities(ActivityFormSubmittedActivity.this);

        Button returnButton = findViewById(R.id.returnToFeedButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Send back to the feed
                Intent intent = new Intent(ActivityFormSubmittedActivity.this, FeedActivity.class);
                startActivity(intent);
            }
        });
    }
}
