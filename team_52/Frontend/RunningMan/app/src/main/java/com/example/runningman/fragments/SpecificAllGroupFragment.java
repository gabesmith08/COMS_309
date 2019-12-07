package com.example.runningman.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.example.runningman.activities.groups.JoinGroupConfirmationActivity;
import com.example.runningman.activities.groups.LeaveGroupConfirmationActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class SpecificAllGroupFragment extends Fragment {

    public static final String KEY_GROUP_INDEX = "group_index";

    private ImageView specificGroupImageView;
    private TextView specificGroupNameTextView;
    private TextView specificGroupMemberTextView;
    private TextView specificGroupLocationTextView;
    private TextView specificGroupDescriptionTextView;
    private Button joinGroupButton;
    private boolean inGroup;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //OnGroupJoinedInterface listener = (OnGroupJoinedInterface) getActivity();
        View view = inflater.inflate(R.layout.fragment_specificgroup, container, false);

        specificGroupImageView = view.findViewById(R.id.specificGroupImage);
        specificGroupNameTextView = view.findViewById(R.id.specificGroupName);
        specificGroupMemberTextView = view.findViewById(R.id.specificMembersItem);
        specificGroupLocationTextView = view.findViewById(R.id.specificLocationItem);
        specificGroupDescriptionTextView = view.findViewById(R.id.groupDescription);
        joinGroupButton = view.findViewById(R.id.joinGroupButton);

        try {
            JSONObject groupJSON = DataHolder.getInstance().getAllGroups().getJSONObject(getArguments().getInt(KEY_GROUP_INDEX));
            Log.d("TAG", "GROUP JSON IS" + groupJSON.toString());
            Log.d("TAG", "inGroup: " + inGroup);
            JSONArray userIds = groupJSON.getJSONArray("userIds");
            for (int i = 0; i < userIds.length(); i++) {
                if (DataHolder.getInstance().getUserId() == (int)userIds.get(i)) {
                    inGroup = true;
                }
            }
        } catch (JSONException e) {
            Toast.makeText(getActivity(), "Checking if in group not working", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        if (inGroup) {
            joinGroupButton.setText("LEAVE GROUP");
            joinGroupButton.setBackgroundColor(getResources().getColor(R.color.app_gray));
            joinGroupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JSONObject payload = new JSONObject();
                    String userId = "" + DataHolder.getInstance().getUserId();
                    try {
                        JSONObject groupJSON = (JSONObject) DataHolder.getInstance().getAllGroups().get(getArguments().getInt(KEY_GROUP_INDEX));
                        String groupId = "" + groupJSON.getInt("Groupid");
                        String addUrl = "http://cs309-pp-2.misc.iastate.edu:8080/deleteuserfromgroup/" + groupId + "/" + userId;
                        JsonObjectRequest postRequest = new JsonObjectRequest
                                (Request.Method.DELETE, addUrl, payload, new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        inGroup = false;
                                        Log.d("TAG", "Got a good response");
                                        Intent intent = new Intent(getActivity(), LeaveGroupConfirmationActivity.class);
                                        startActivity(intent);
                                        //Toast.makeText(getActivity(), "Good Response from server" + getArguments().getInt(KEY_GROUP_INDEX), Toast.LENGTH_LONG).show();
                                    }
                                }, new Response.ErrorListener() {

                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.d(TAG, error.getMessage());
                                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();

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
                        MySingleton.getInstance(getActivity()).addToRequestQueue(postRequest);
                        //Toast.makeText(getActivity(), "The group index you are trying to join is: " + getArguments().getInt(KEY_GROUP_INDEX), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        } else {
            joinGroupButton.setText("JOIN GROUP");
            joinGroupButton.setBackgroundColor(getResources().getColor(R.color.app_orange));
            joinGroupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JSONObject payload = new JSONObject();
                    String userId = "" + DataHolder.getInstance().getUserId();
                    try {
                        JSONObject groupJSON = (JSONObject) DataHolder.getInstance().getAllGroups().get(getArguments().getInt(KEY_GROUP_INDEX));
                        String groupId = "" + groupJSON.getInt("Groupid");
                        String addUrl = "http://cs309-pp-2.misc.iastate.edu:8080/addusertogroup/" + userId + "/" + groupId;
                        JsonObjectRequest postRequest = new JsonObjectRequest
                                (Request.Method.PUT, addUrl, payload, new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        inGroup = true;
                                        Log.d("TAG", "Got a good response");
                                        Intent intent = new Intent(getActivity(), JoinGroupConfirmationActivity.class);
                                        startActivity(intent);
                                        //Toast.makeText(getActivity(), "Good Response from server" + getArguments().getInt(KEY_GROUP_INDEX), Toast.LENGTH_LONG).show();
                                    }
                                }, new Response.ErrorListener() {

                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.d(TAG, error.getMessage());
                                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();

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
                        MySingleton.getInstance(getActivity()).addToRequestQueue(postRequest);
                        //Toast.makeText(getActivity(), "The group index you are trying to join is: " + getArguments().getInt(KEY_GROUP_INDEX), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        }



        int index = getArguments().getInt(KEY_GROUP_INDEX);
        try {
            JSONObject groupJSON = DataHolder.getInstance().getAllGroups().getJSONObject(index);
            Log.d("TAG", "groupJSON is: " + groupJSON.toString());
            Toast.makeText(getActivity(), groupJSON.getString("name") + " is clicked on!", Toast.LENGTH_SHORT).show();



            // FOR CONVERTING BACK TO IMAGEVIEW FROM BITMAP RECIEVED FROM DATABASE
            byte[] byteArray = (groupJSON.getString("bytes").getBytes());
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

            if(bmp == null) {
                specificGroupImageView.setImageResource(R.drawable.nav_groups);
            }
            else {
                specificGroupImageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, 50,
                        50, false));
            }

            specificGroupImageView.setImageResource(R.drawable.nav_groups);
            specificGroupNameTextView.setText(groupJSON.getString("name"));
            String numMembers = "" + groupJSON.getJSONArray("userIds").length();
            specificGroupMemberTextView.setText(numMembers);
            String location = "" + groupJSON.getInt("zip");
            specificGroupLocationTextView.setText(location);
            specificGroupDescriptionTextView.setText(groupJSON.getString("bio"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}
