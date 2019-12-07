package com.example.runningman.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataHolder {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private JSONArray activities;
    private String bio;
    private JSONArray allGroups;
    private JSONArray userGroups;
    private JSONArray feedActivities;

    public JSONArray getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(JSONArray userGroups) {
        this.userGroups = userGroups;
    }

    public JSONArray getFeedActivities() {
        return feedActivities;
    }

    public void setFeedActivities(JSONArray feedActivities) {
        this.feedActivities = feedActivities;
    }

    /**
     * Makes a GET request to server to update the user's feed activities.
     *
     * @param context The context of the activity that calls it
     */
    public void updateFeedActivities(Context context) {
        String url = "http://cs309-pp-2.misc.iastate.edu:8080/getuserfeed/" + userId;
        Log.d("TAG", "Request URL: " + url);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray allFeedActivities = new JSONArray(response);
                            Log.d("DATA", "FeedActivitiesJSONARRAY: " + allFeedActivities.toString());
                            DataHolder.getInstance().setFeedActivities(allFeedActivities);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        // Add the request to the RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public JSONArray getAllGroups() {
        return allGroups;
    }

    public void setAllGroups(JSONArray allGroups) {
        this.allGroups = allGroups;
    }

    /**
     * Makes a GET request to the server to update the allGroups
     *
     * @param context The context of the activity that calls it
     */
    public void updateGroups(Context context) {
        String url = "http://cs309-pp-2.misc.iastate.edu:8080/getallgroups/";
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray groupsJSONArray = new JSONArray(response);
                            DataHolder.getInstance().setAllGroups(groupsJSONArray);
                            JSONArray userGroups = new JSONArray();
                            for(int i = 0; i < groupsJSONArray.length(); i++){
                                //Log.d("TAG", groupsJSONArray.get(i).toString());
                                JSONObject group = (JSONObject)groupsJSONArray.get(i);
                                JSONArray userIds = (group.getJSONArray("userIds"));
                                for(int j = 0; j < userIds.length(); j++){
                                    if((int)userIds.get(j)==DataHolder.getInstance().getUserId()){
                                        userGroups.put(userGroups.length(), group);
                                        break;
                                    }
                                }
                            }
                            //Log.d("USERGROUPTEST", userGroups.toString());
                            DataHolder.getInstance().setUserGroups(userGroups);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        // Add the request to the RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Makes a GET Request to get user info and validate login
     *
     * @param context The context of the activity that calls it
     */
    public void updateActivities(Context context) {
        String url = "http://cs309-pp-2.misc.iastate.edu:8080/getuserfromlogin/" + email + "/" + password;
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject profileJSON = new JSONObject(response);
                            DataHolder.getInstance().setActivities(profileJSON.getJSONArray("activities"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        // Add the request to the RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public JSONArray getActivities() {
        return activities;
    }

    public void setActivities(JSONArray activities) {
        this.activities = activities;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    private static final DataHolder holder = new DataHolder();

    public static DataHolder getInstance() {
        return holder;
    }
}
