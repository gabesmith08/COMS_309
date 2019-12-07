package com.example.runningman.activities.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.runningman.R;
import com.example.runningman.activities.feed.FeedActivity;
import com.example.runningman.model.DataHolder;
import com.example.runningman.model.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class SignUpActivity extends AppCompatActivity {


    public static final String TAG = SignUpActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        Button signUpButton = findViewById(R.id.signupRequestButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView checkEmailView = findViewById(R.id.email);
                final String checkEmail = checkEmailView.getText().toString();
                String url = "http://cs309-pp-2.misc.iastate.edu:8080/emailExists/" + checkEmail;

                Log.d(TAG, "Request URL is: " + url);
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(
                        Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 100 characters of the response string
                                Log.d(TAG, "Response is: " + response);
                                try {
                                    JSONObject emailJSON = new JSONObject(response);
                                    //response: "{ 'email' : true }"
                                    boolean emailExists = emailJSON.getBoolean("email");
                                    if (emailExists) {
                                        Toast.makeText(SignUpActivity.this, "Email already exists! Try logging in.", Toast.LENGTH_LONG).show();
                                    } else {

                                        TextView firstNameView = findViewById(R.id.firstName);
                                        final String firstName = firstNameView.getText().toString();
                                        TextView lastNameView = findViewById(R.id.lastName);
                                        final String lastName = lastNameView.getText().toString();
                                        TextView emailView = findViewById(R.id.email);
                                        final String email = emailView.getText().toString();
                                        TextView passwordView = findViewById(R.id.password);
                                        final String password = passwordView.getText().toString();
                                        TextView bioView = findViewById(R.id.bio);
                                        final String bio = bioView.getText().toString();
                                        RadioGroup genderGroup = findViewById(R.id.genderButtonGroup);
                                        RadioButton genderView = findViewById(genderGroup.getCheckedRadioButtonId());
                                        final String gender = genderView.getText().toString();

                                        JSONObject profile = new JSONObject();
                                        profile.put("first_name", firstName);
                                        profile.put("last_name", lastName);
                                        profile.put("email", email);
                                        profile.put("password", password);
                                        profile.put("bio", bio);
                                        profile.put("gender", gender);

                                        String addurl = "http://cs309-pp-2.misc.iastate.edu:8080/adduser";
                                        JsonObjectRequest postRequest = new JsonObjectRequest
                                                (Request.Method.POST, addurl, profile, new Response.Listener<JSONObject>() {

                                                    @Override
                                                    public void onResponse(JSONObject response) {

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
                                                                            DataHolder.getInstance().setActivities(new JSONArray());
                                                                            DataHolder.getInstance().setEmail(profileJSON.getString("email"));
                                                                            DataHolder.getInstance().setPassword(profileJSON.getString("password"));

                                                                            //Send to Feed
                                                                            Intent intent = new Intent(SignUpActivity.this, FeedActivity.class);
                                                                            startActivity(intent);

                                                                        } catch (JSONException e) {
                                                                            Toast.makeText(SignUpActivity.this, "No Account, Sign UP!", Toast.LENGTH_LONG).show();
                                                                        }
                                                                    }
                                                                },
                                                                new Response.ErrorListener() {
                                                                    @Override
                                                                    public void onErrorResponse(VolleyError error) {
                                                                        Log.d(TAG, error.getMessage());
                                                                    }
                                                                });
                                                        MySingleton.getInstance(SignUpActivity.this).addToRequestQueue(stringRequest);
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
                                        MySingleton.getInstance(SignUpActivity.this).addToRequestQueue(postRequest);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, error.getMessage());
                            }
                        });

                // Add the request to the RequestQueue.
                MySingleton.getInstance(SignUpActivity.this).addToRequestQueue(stringRequest);
            }
        });


    }


}
