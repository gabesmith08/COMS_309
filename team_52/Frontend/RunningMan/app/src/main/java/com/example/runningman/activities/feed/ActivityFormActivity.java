package com.example.runningman.activities.feed;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.runningman.R;
import com.example.runningman.model.DataHolder;
import com.example.runningman.model.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;

public class ActivityFormActivity extends AppCompatActivity implements
        View.OnClickListener {

    public static final String TAG = ActivityFormActivity.class.getSimpleName();

    Button submitButton, btnDatePicker, btnStartTimePicker, btnEndTimePicker;
    TextView txtDate, txtStartTime, txtEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_form);

        submitButton = findViewById(R.id.activityFormSubmitButton);
        btnDatePicker = findViewById(R.id.btn_date);
        btnStartTimePicker = findViewById(R.id.btn_start_time);
        btnEndTimePicker = findViewById(R.id.btn_end_time);

        submitButton.setOnClickListener(this);
        btnDatePicker.setOnClickListener(this);
        btnStartTimePicker.setOnClickListener(this);
        btnEndTimePicker.setOnClickListener(this);

        txtDate = findViewById(R.id.in_date);
        txtStartTime = findViewById(R.id.in_start_time);
        txtEndTime = findViewById(R.id.in_end_time);
    }

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            String dateString = "" + (monthOfYear + 1) + "-" + dayOfMonth + "-" + year;
                            txtDate.setText(dateString);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        int mHour;
        int mMinute;
        if (v == btnStartTimePicker) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            String hourStr = "" + hourOfDay + ":" + minute;
                            txtStartTime.setText(hourStr);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == btnEndTimePicker) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            String dayStr = "" + hourOfDay + ":" + minute;
                            txtEndTime.setText(dayStr);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == submitButton) try {
            int user_id = DataHolder.getInstance().getUserId();
            TextView titleView = findViewById(R.id.in_text_title);
            String title = titleView.getText().toString();
            TextView distanceView = findViewById(R.id.in_text_distance);
            String distance = distanceView.getText().toString();
            TextView startTimeView = findViewById(R.id.in_start_time);
            String startTime = startTimeView.getText().toString();
            TextView endTimeView = findViewById(R.id.in_end_time);
            String endTime = endTimeView.getText().toString();
            TextView dateView = findViewById(R.id.in_date);
            String date = dateView.getText().toString();

            JSONObject activity = new JSONObject();
            activity.put("type", title);
            activity.put("distance", distance);
            activity.put("starttime", startTime);
            activity.put("endtime", endTime);
            activity.put("date", date);
            activity.put("feedback", date);

            //Makes a request to the server to add an activity to the user
            String addUrl = "http://cs309-pp-2.misc.iastate.edu:8080/addactivitytouser/" + user_id;
            JsonObjectRequest postRequest = new JsonObjectRequest
                    (Request.Method.POST, addUrl, activity, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d(TAG, "Got a good response");
                            Intent intent = new Intent(ActivityFormActivity.this, ActivityFormSubmittedActivity.class);
                            startActivity(intent);
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
            MySingleton.getInstance(ActivityFormActivity.this).addToRequestQueue(postRequest);

        } catch (JSONException e) {
            Toast.makeText(ActivityFormActivity.this, "JSON ERROR", Toast.LENGTH_LONG).show();
            Log.d(TAG, e.getLocalizedMessage());
        }
    }
}

