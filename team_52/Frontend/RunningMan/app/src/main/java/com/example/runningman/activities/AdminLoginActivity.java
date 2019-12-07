package com.example.runningman.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.runningman.R;

public class AdminLoginActivity extends AppCompatActivity {

    private Button adminLoginButton = findViewById(R.id.adminLoginButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminLoginActivity.this, AdminHomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
