package com.example.runningman;


import com.example.runningman.activities.groups.GroupsActivity;
import com.example.runningman.activities.profile.ProfileActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class GroupsActivityTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Test
    public void getResponseTest_returnsTrue() throws JSONException {
        //creates a Mock Object of the class that we have not fully implemented and are going to test
        GroupsActivity test = mock(GroupsActivity.class);
        GroupsActivity findGroupSuccess = new GroupsActivity();

        //parameters to the function that we have not yet implemented in the GroupActivity class
        String groupNameCorrect = "team52";

        //expecting a JSONObject response from the server that represents whether or not the group name exists in the database
        JSONObject response = new JSONObject();

        //In this simulated instance, the response from the server is a JSONObject "groupFound",
        //with a boolean value "true" because the group exists and was found
        response.put("groupFound",new Boolean (true));

        //This line specifies the behavior of the getResponse method from LoginHandler that currently returns null
        //You can think of it as overriding the behavior of the function and forcing it to return a specific value
        //In the following line, we are forcing this unimplemented method to return our predefined variable "response"
        when(test.getResponse(groupNameCorrect)).thenReturn(response);

        Assert.assertEquals(findGroupSuccess.findGroup(groupNameCorrect, test),response.getBoolean("groupFound"));
    }

    @Test
    public void getResponseTest_returnsFalse() throws JSONException {
        //creates a Mock Object of the class that we have not fully implemented and are going to test
        GroupsActivity test = mock(GroupsActivity.class);
        GroupsActivity findGroupSuccess = new GroupsActivity();

        //parameters to the function that we have not yet implemented in the GroupActivity class
        String groupNameIncorrect = "team0";

        //expecting a JSONObject response from the server that represents whether or not the group name exists in the database
        JSONObject response = new JSONObject();

        //In this simulated instance, the response from the server is a JSONObject "groupFound",
        //with a boolean value "true" because the group exists and was found
        response.put("groupFound",new Boolean (false));

        //This line specifies the behavior of the getResponse method from LoginHandler that currently returns null
        //You can think of it as overriding the behavior of the function and forcing it to return a specific value
        //In the following line, we are forcing this unimplemented method to return our predefined variable "response"
        when(test.getResponse(groupNameIncorrect)).thenReturn(response);

        Assert.assertEquals(findGroupSuccess.findGroup(groupNameIncorrect, test),response.getBoolean("groupFound"));

    }

    @Test
    public void checkEmailTest_returnsTrue() throws JSONException {
        //creates a Mock Object of the class that we have not fully implemented and are going to test
        ProfileActivity test = mock(ProfileActivity.class);
        ProfileActivity emailSuccess = new ProfileActivity();

        //parameters to the function that we have not yet implemented in the GroupActivity class
        String emailCorrect = "richard@gmail.com";

        //expecting a JSONObject response from the server that represents whether or not the group name exists in the database
        JSONObject response = new JSONObject();

        //In this simulated instance, the response from the server is a JSONObject "groupFound",
        //with a boolean value "true" because the group exists and was found
        response.put("emailFound",new Boolean (true));

        //This line specifies the behavior of the getResponse method from LoginHandler that currently returns null
        //You can think of it as overriding the behavior of the function and forcing it to return a specific value
        //In the following line, we are forcing this unimplemented method to return our predefined variable "response"
        when(test.getResponse(emailCorrect)).thenReturn(response);

        Assert.assertEquals(emailSuccess.emailExists(emailCorrect, test),response.getBoolean("emailFound"));
    }



}
