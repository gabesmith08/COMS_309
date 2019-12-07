package com.example.runningman.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.runningman.R;
import com.example.runningman.activities.auth.MainActivity;
import com.example.runningman.model.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminHomeActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        final Spinner apiMethods = findViewById(R.id.apiMethodSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.apiMethods, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        apiMethods.setAdapter(adapter);

        Button sendRequestButton = findViewById(R.id.sendRequestButton);
        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String requestMethod = apiMethods.getSelectedItem().toString();

                TextView requestText = findViewById(R.id.requestText);

                //Start each request with the server's base url
                String url = "http://cs309-pp-2.misc.iastate.edu:8080/";
                url += requestText.getText();
                Log.d(TAG, "Request URL is: " + url);

                switch (requestMethod) {
                    case "GET":
                        Toast.makeText(AdminHomeActivity.this, "Trying GET Request", Toast.LENGTH_SHORT).show();
                        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        //Toast.makeText(MainActivity.this, "Response is: "+ response, Toast.LENGTH_LONG).show();
                                        Log.d(TAG, "Response is: " + response);
                                        TextView responseText = findViewById(R.id.responseText);
                                        responseText.setText(response);

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(AdminHomeActivity.this, "Could not connect to server", Toast.LENGTH_LONG).show();
                            }
                        });
                        // Add the request to the RequestQueue.
                        MySingleton.getInstance(AdminHomeActivity.this).addToRequestQueue(getRequest);
                        break;
                    case "POST":
                        Toast.makeText(AdminHomeActivity.this, "Trying POST Request", Toast.LENGTH_SHORT).show();

                        String paramString = "{'first_Name': 'Rob','last_Name': 'Smith','email':'rsmith@yahoo.com','user_Name':'robman' }";
                        try {
                            JSONObject paramData = new JSONObject(paramString);
                            url = "http://cs309-pp-2.misc.iastate.edu:8080/adduser";
                            JsonObjectRequest postRequest = new JsonObjectRequest
                                    (Request.Method.POST, url, paramData, new Response.Listener<JSONObject>() {

                                        @Override
                                        public void onResponse(JSONObject response) {
                                            TextView responseText = findViewById(R.id.responseText);
                                            responseText.setText(response.toString());
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            error.printStackTrace();
                                        }
                                    });
                            MySingleton.getInstance(AdminHomeActivity.this).addToRequestQueue(postRequest);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "DELETE":
                        Toast.makeText(AdminHomeActivity.this, "DELETE Request not yet implemented", Toast.LENGTH_SHORT).show();
                        break;
                    case "PUT":
                        Toast.makeText(AdminHomeActivity.this, "PUT Request not yet implemented", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Log.d(TAG, "Cannot understand request type");
                }
            }
        });
        apiMethods.getSelectedItem();
    }
}
