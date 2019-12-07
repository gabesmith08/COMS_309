package _Server_Side.Server.Controllers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import _Server_Side.Server.Entities.Activity_Entity;
import _Server_Side.Server.Entities.Group_Entity;
import _Server_Side.Server.Entities.User_Entity;
import _Server_Side.Server.Services.Activity_Service;
import _Server_Side.Server.Services.Group_Service;
import _Server_Side.Server.Services.User_Service;


@RestController
public class User_Controller {
	
	@Autowired
	private User_Service userService;
	@Autowired
	private Activity_Service activityService;
	
	
	//USERS ---------------------- >
	
	/**
	 * get all the users in the database
	 * @return Json array of user objects
	 */
	@RequestMapping(method=RequestMethod.GET, value="/getallusers")
	public List<User_Entity> getAllUsers()
	{
		return userService.getAllUsers();
	}
	
	/**
	 * get a single user based on id
	 * @return Json of user objects
	 */
	
	@GetMapping(value="/getuserfromid/{id}")
	public User_Entity getUser(@PathVariable int id)
	{
		User_Entity temp = userService.getUser(id);
		return temp;
	}
	
	/**
	 * Add user to the database, must have Json body attached
	 * @return NA
	 */
	@RequestMapping(method=RequestMethod.POST, value="/adduser")
	public void addUser(@RequestBody User_Entity user)
	{
		userService.addUser(user);
	}
	
	/**
	 * update the user with a JSON body of user sent as PUT request
	 * @return NA
	 */
	@RequestMapping(method=RequestMethod.PUT, value="/putuser")
	public void updateUser(@RequestBody User_Entity user)
	{
		userService.addUser(user);
	}
	
	/**
	 * deletes user from the database
	 * @return NA
	 */
	@DeleteMapping(value="/deleteuser/{id}")
	public void deleteUser(@PathVariable int id)
	{
		userService.deleteUser(id);
	}
	
	/**
	 * get a certain user based on user email and password
	 * @return Json of user object
	 */
	@GetMapping(value="/getuserfromlogin/{email}/{password}")
	public User_Entity getUser(@PathVariable("email") String email, @PathVariable("password") String password)
	{
		User_Entity temp = userService.getUserEmailPassword(email, password);
		return temp;
	}
	
	/**
	 * check if the email given is in the database
	 * @return boolean JSON
	 */
	@GetMapping(value="/emailExists/{email}")
	public String emailExists(@PathVariable String email)
	{
		User_Entity temp = userService.getUserEmail(email);
		if(temp == null) {
			return "{ 'email': false }";
		}
		return "{'email': true }";
	}
	
	//USERS ----------------- <
	//GROUPS--------------------- >
	
	/**
	 * get all the groups associated with a user
	 * @return Json array of group objects
	 */
	@RequestMapping(method=RequestMethod.GET, value="/getusergroups/{userid}")
	public Set<Group_Entity> getGroups(@PathVariable("userid") int userid)
	{
		User_Entity tempUser = userService.getUser(userid);
		return tempUser.getGroups();
	}
	
	
	//GROUPS --------------------------- <
	
	
	//ACTIVITIES --------------- >
	
	/**
	 * get all the activities associated with a user
	 * @return Json array of activity objects
	 */
	@RequestMapping(method=RequestMethod.GET, value="/getallactivitiesfromuser/{userid}")
	public String getActivities(@PathVariable(name="userid") int userid)
	{
		JSONArray tempArray = new JSONArray();
		User_Entity tempUser = userService.getUser(userid);
		Set<Activity_Entity> tempSet = tempUser.getActivities();
		String name = tempUser.getFirst_name();
		name = name + " " + tempUser.getLast_name();
		//create JSON objects to return and add then to JSONArray
		for(Activity_Entity tempA : tempSet) {
			JSONObject jo = new JSONObject();
			jo.put("type",tempA.getType());
			jo.put("distance",tempA.getDistance());
			jo.put("time",tempA.getTime());
			jo.put("starttime",tempA.getStarttime());
			jo.put("endtime",tempA.getEndtime());
			jo.put("date",tempA.getDate());
			jo.put("userid",tempA.getId());
			jo.put("Username", name);
			tempArray.put(jo);
		}
		
		//create mapping of objects to dates
		Map<Date,JSONObject> tempMap = new TreeMap<Date,JSONObject>(Collections.reverseOrder());
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		for( int i = 0; i < tempArray.length();i++)
		{
			JSONObject JSON = tempArray.getJSONObject(i);
			Date date = new Date();
			try {
				date = simpleDateFormat.parse(JSON.getString("date"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tempMap.put(date, JSON);
		}
		Collection<JSONObject> tempJSON = tempMap.values();
		return tempJSON.toString();
	}
	
	/**
	 * deletes an activity
	 * @return NA
	 */
	@RequestMapping(method=RequestMethod.DELETE, value="/removeactivity/{activityid}")
	public void removeActivitie(@PathVariable(name="activityid") int activityid)
	{
		activityService.deleteActivity(activityid);
	}
	
	/**
	 * add an activity to a user, the userid specifies which user the activity is added to, needs a JSON body of activity attached
	 * @return NA
	 */
	@RequestMapping(method=RequestMethod.POST, value="/addactivitytouser/{userid}")
	public void addActivity(@RequestBody Activity_Entity activity, @PathVariable int userid)
	{
		activity.setUser(userService.getUser(userid));
		activity.setUserid(userid);
		activityService.addActivity(activity);
	}
	
	/**
	 * return an activity based on id passed in URI
	 * @return Json of activity object
	 */
	@GetMapping(value="/getactivityfromid/{id}")
	public Activity_Entity getActivity(@PathVariable int id)
	{
		Activity_Entity temp = activityService.getActivity(id);
		return temp;
	}
	
	/**
	 * update the contents within a certain activity, must contain JSON body of Activity 
	 * Do not specify the userid or id of the activity in the body of the JSON, do that in the URI
	 * @return NA
	 */
	@RequestMapping(method=RequestMethod.PUT, value="/updateactivity/{userid}/{activityid}")
	public void updateActivity(@RequestBody Activity_Entity activity, @PathVariable("userid") int userid, @PathVariable("activityid") int actid)
	{
		User_Entity tempUser = userService.getUser(userid);
		activity.setUser(tempUser);
		activity.setUserid(tempUser.getId());
		activity.setId(actid);
		activityService.addActivity(activity);
	}
	
	
	// ACTIVITIES ---------------------------- <
	
}
