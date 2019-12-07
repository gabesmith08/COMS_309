package com.example.runningman.activities.groups;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.runningman.R;
import com.example.runningman.model.DataHolder;

public class LeaveGroupConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_group_confirmation);

        DataHolder.getInstance().updateGroups(LeaveGroupConfirmationActivity.this);

        Button returnButton = findViewById(R.id.returnToGroupsButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Send back to the groups page
                Intent intent = new Intent(LeaveGroupConfirmationActivity.this, GroupsActivity.class);

                startActivity(intent);
            }
        });
    }
}
