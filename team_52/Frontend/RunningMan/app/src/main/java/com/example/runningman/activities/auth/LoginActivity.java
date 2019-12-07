package com.example.runningman.activities.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.runningman.R;
import com.example.runningman.activities.feed.FeedActivity;
import com.example.runningman.model.DataHolder;
import com.example.runningman.model.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView emailView = findViewById(R.id.emailField);
                final String email = emailView.getText().toString();

                TextView passwordView = findViewById(R.id.passwordField);
                final String password = passwordView.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Empty fields. Please enter values", Toast.LENGTH_SHORT).show();
                    return;
                }

                String url = "http://cs309-pp-2.misc.iastate.edu:8080/getuserfromlogin/" + email + "/" + password;
                Log.d(TAG, url);
                StringRequest stringRequest = new StringRequest(
                        Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 100 characters of the response string
                                Log.d(TAG, "Response is: " + response);
                                try {
                                    JSONObject profileJSON = new JSONObject(response);
                                    //response: "{ 'email' : true }"
                                    Log.d(TAG, profileJSON.toString());

                                    DataHolder.getInstance().setUserId(profileJSON.getInt("id"));
                                    DataHolder.getInstance().setFirstName(profileJSON.getString("first_name"));
                                    DataHolder.getInstance().setLastName(profileJSON.getString("last_name"));
                                    DataHolder.getInstance().setBio((profileJSON.getString("bio")));
                                    DataHolder.getInstance().setActivities(profileJSON.getJSONArray("activities"));
                                    DataHolder.getInstance().setEmail(profileJSON.getString("email"));
                                    DataHolder.getInstance().setPassword(profileJSON.getString("password"));
                                    DataHolder.getInstance().setFeedActivities(new JSONArray());
                                    DataHolder.getInstance().updateFeedActivities(LoginActivity.this);
                                    //Send to Feed
                                    Intent intent = new Intent(LoginActivity.this, FeedActivity.class);
                                    startActivity(intent);

                                } catch (JSONException e) {
                                    Toast.makeText(LoginActivity.this, "No Account, Sign UP!", Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, error.getMessage() + "");
                            }
                        });

                // Add the request to the RequestQueue.
                MySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
            }
        });

    }
}
