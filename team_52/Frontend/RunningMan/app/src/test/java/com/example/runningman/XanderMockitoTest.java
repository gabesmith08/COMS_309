package com.example.runningman;

import android.provider.ContactsContract;

import com.example.runningman.model.DataHolder;
import com.example.runningman.websockets.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.*;

public class XanderMockitoTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

//    @RunWith(MockitoJUnitRunner.class)
//    public class DataHolderTest {

    private static final String TEST_EMAIL = "mytest@gmail.com";

    public static final String TEST_PASSWORD = "running";

    public final DataHolder TEST_DATAHOLDER = DataHolder.getInstance();


    //        @Mock
//        int userId;
//        @Mock
//        String firstName;
//        @Mock
//        String lastName;
//        @Mock
//        String email;
//        @Mock
//        String password;
//        @Mock
//        String bio;
    @Mock
    JSONArray activities;
    @Mock
    JSONArray groups;

    @Mock
    Message message;

    @Before
    public void initMocks() {
        // Create groups array
//            JSONObject group1 = new JSONObject();
//            JSONObject group2 = new JSONObject();
//            JSONObject group3 = new JSONObject();
//            try {
//                group1.put("name","Ames Running Group");
//                group1.put("num_members", 23);
//                group1.put("location", "50014");
//                group1.put("description", "We're just chill runners who like group runs. Come run with us!");
//                group2.put("name", "Bartlett Runners");
//                group2.put("num_members", 12);
//                group2.put("location", "60103");
//                group2.put("description", "This club was founded on respect. Looking for new members. Show up on time, or we'll leave without you.");
//                group3.put("name", "The Elites");
//                group3.put("num_members", 2);
//                group3.put("location", "80025");
//                group3.put("description", "We're fast. If you can't keep up with us, then we will drop you like its hot!");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            JSONArray groups = new JSONArray();
//            groups.put(group1);
//            groups.put(group2);
//            groups.put(group3);

        //create activities array
//            JSONObject act1 = new JSONObject();
//            JSONObject act2 = new JSONObject();
//            JSONObject act3 = new JSONObject();
//            try {
//                act1.put("type", "My first Run");
//                act1.put("distance", 23);
//                act1.put("starttime", "12:06");
//                act1.put("endtime","13:21");
//                act1.put("date", "12/14/18");
//
//                act2.put("type", "My second Run");
//                act2.put("distance", 25);
//                act2.put("starttime", "12:01");
//                act2.put("endtime","12:24");
//                act2.put("date", "12/16/18");
//
//                act3.put("type", "My third Run");
//                act3.put("distance", 2.4);
//                act3.put("starttime", "1:06");
//                act3.put("endtime","3:47");
//                act3.put("date", "12/18/18");
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            JSONArray activities = new JSONArray();
//            activities.put(act1);
//            activities.put(act2);
//            activities.put(act3);
    }

    @Test
    public void setMessageTest() {
        message = new Message("John Smith", "Hello yall!", false);
        Assert.assertEquals(message.getFromName(), "John Smith");
        Assert.assertEquals(message.getMessage(), "Hello yall!");
    }

    @Test
    public void setDataHolderBioTest() {
        String bioStr = "this is a bio";
        DataHolder.getInstance().setBio(bioStr);
        Assert.assertEquals(bioStr, DataHolder.getInstance().getBio());
    }

    @Test
    public void  setDataHolderNamesTest() {
        String firstName = "Xander";
        String lastName = "Apponi";
        DataHolder.getInstance().setFirstName(firstName);
        DataHolder.getInstance().setLastName(lastName);
        Assert.assertEquals(firstName,DataHolder.getInstance().getFirstName());
        Assert.assertEquals(lastName,DataHolder.getInstance().getLastName());
    }

//        @Test
//        public void setActivities_goodJSONActivities(){
//            when(DataHolder.getInstance().getActivities()).thenReturn(activities);
//            DataHolder.getInstance().setActivities(activities);
//            JSONArray returnedActivities = DataHolder.getInstance().getActivities();
//            try {
//                JSONObject act1 = (JSONObject) returnedActivities.get(0);
//                Assert.assertEquals(act1.getString("type"), "My first Run");
//
//            }
//            catch (JSONException e){
//                e.printStackTrace();
//            }
//
//        }
//        @Test
//        public void setGroups_goodJSONActivities(){
//            when(DataHolder.getInstance().getAllGroups()).thenReturn(groups);
//            DataHolder.getInstance().setAllGroups(groups);
//            Assert.assertEquals(DataHolder.getInstance().getAllGroups(), groups);
//        }


//    }


}
