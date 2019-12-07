package com.example.runningman.activities.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.runningman.R;
import com.example.runningman.model.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;


public class EditProfileActivity extends AppCompatActivity {

    public static final String TAG = EditProfileActivity.class.getSimpleName();
    Button confirmProfileChangesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        confirmProfileChangesButton = findViewById(R.id.confirmProfileChangesButton);

        confirmProfileChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == confirmProfileChangesButton) {


                    try {
                        //int user_id = DataHolder.getInstance().getUserId();

                        TextView firstNameProfileEdit = findViewById(R.id.firstNameProfileEdit);
                        String firstName = firstNameProfileEdit.getText().toString();
                        TextView lastNameProfileEdit = findViewById(R.id.lastNameProfileEdit);
                        String lastName = lastNameProfileEdit.getText().toString();
                        TextView emailView = findViewById(R.id.emailProfileEdit);
                        String email = emailView.getText().toString();
                        TextView passwordView = findViewById(R.id.passwordProfileEdit);
                        String password = passwordView.getText().toString();
                        TextView bioView = findViewById(R.id.bioProfileEdit);
                        String bio = bioView.getText().toString();



                        JSONObject profile = new JSONObject();

                        if(firstName.length() > 1) {
                            profile.put("first_name", firstName);
                        }
                        if(lastName.length() > 1) {
                            profile.put("last_name", lastName);
                        }
                        if(email.length() > 1) {
                            profile.put("email", email);
                        }
                        if(password.length() > 1) {
                            profile.put("password", password);
                        }
                        if(bio.length() > 1) {
                            profile.put("bio", bio);
                        }

                        String addUrl = "http://cs309-pp-2.misc.iastate.edu:8080/putuser";
                        JsonObjectRequest putRequest = new JsonObjectRequest
                                (Request.Method.PUT, addUrl, profile, new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Log.d(TAG, "Got a good response");
                                    }
                                }, new Response.ErrorListener() {

                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.d(TAG, error.getMessage());
                                    }
                                }) {
                            @Override
                            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {

                                String json = new String(
                                        response.data,
                                        StandardCharsets.UTF_8
                                );

                                if (json.length() == 0) {
                                    return Response.success(
                                            null,
                                            HttpHeaderParser.parseCacheHeaders(response)
                                    );
                                } else {
                                    return super.parseNetworkResponse(response);
                                }

                            }
                        };
                        MySingleton.getInstance(EditProfileActivity.this).addToRequestQueue(putRequest);

                    } catch (JSONException e) {
                        Toast.makeText(EditProfileActivity.this, "JSON ERROR", Toast.LENGTH_LONG).show();
                        Log.d(TAG, e.getLocalizedMessage());
                    }
                    Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}
