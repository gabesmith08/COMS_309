package _Server_Side.Server.Controllers;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
import _Server_Side.Server.Services.Group_Service;
import _Server_Side.Server.Services.User_Service;

@RestController
public class Group_Contoller {
	
	@Autowired
	private Group_Service groupService;
	@Autowired
	private User_Service userService;
	
	/**
	 * get all the groups in the database
	 * @return JSON array of group objects
	 */
	@RequestMapping(method=RequestMethod.GET, value="/getallgroups")
	public List<Group_Entity> GetAllGroups()
	{
		return groupService.getAllGroups();
	}
	
	/**
	 * returns a JSON group object specified by id
	 * @param id
	 * @return JSON group Object
	 */
	@GetMapping(value="/getgroupfromid/{id}")
	public Group_Entity GetGroup(@PathVariable int id)
	{
		Group_Entity temp = groupService.getGroup(id);
		return temp;
	}
	
	/**
	 * Create a group, takes a JSON body of a group passed POST
	 * @param group
	 */
	@RequestMapping(method=RequestMethod.POST, value="/addgroup")
	public void AddGroup(@RequestBody Group_Entity group)
	{
		groupService.addGroup(group);
	}
	
	/**
	 * updates the information of the group, needs a group JSON body attached PUT
	 * @param group
	 */
	@RequestMapping(method=RequestMethod.PUT, value="/updategroup")
	public void UpdateGroup(@RequestBody Group_Entity group)
	{
		groupService.addGroup(group);
	}
	
	/**
	 * deletes an entire group based on id
	 * @param id
	 */
	@DeleteMapping(value="/deletegroup/{id}")
	public void DeleteGroup(@PathVariable int id)
	{
		groupService.deleteGroup(id);
	}
	
	/**
	 * adds a user to a group
	 * @param groupid
	 * @param userid
	 */
	@RequestMapping(method=RequestMethod.PUT, value="/addusertogroup/{userid}/{groupid}")
	public void AddUserToGroup(@PathVariable("groupid") int groupid, @PathVariable("userid") int userid)
	{
		Group_Entity tempGroup = groupService.getGroup(groupid);
		User_Entity tempUser = userService.getUser(userid);
		tempGroup.addUser(tempUser);
		tempUser.getGroups().add(tempGroup);
		groupService.addGroup(tempGroup);
	}
	
	/**
	 * returns all the user objects associated with a group
	 * @param groupid
	 * @return JSON array of user objects
	 */
	@RequestMapping(method=RequestMethod.GET, value="/getuserprofs/{groupid}")
	public Set<User_Entity> GetUserProfiles(@PathVariable("groupid") int groupid)
	{
		Set<User_Entity> userList = new HashSet<User_Entity>();
		Group_Entity tempGroup= groupService.getGroup(groupid);
		Set<Integer> tempList = tempGroup.getUserIds();
		for(Integer id : tempList)
		{
			userList.add(userService.getUser(id));
		}
		return userList;
	}
	
	/**
	 * removes a user from a group
	 * @param groupid
	 * @param userid
	 * @return NA
	 */
	@RequestMapping(method=RequestMethod.DELETE, value="/deleteuserfromgroup/{groupid}/{userid}")
	public void RemoveUser(@PathVariable("groupid") int groupid, @PathVariable("userid") int userid)
	{
		Group_Entity tempGroup = groupService.getGroup(groupid);
		User_Entity tempUser = userService.getUser(userid);
		tempGroup.removeUser(tempUser);
		tempGroup.getUserIds().remove(tempUser.getId());
		tempUser.getGroups().remove(tempGroup);
		groupService.addGroup(tempGroup);
		userService.addUser(tempUser);
	}
	
	/**
	 * get all the actvities associated with a group
	 * @param groupid
	 * @return JSON array of actvity objects
	 */
	@RequestMapping(method=RequestMethod.GET, value="/getallactivitiesingroup/{groupid}")
	public Set<Activity_Entity> GetActivities(@PathVariable("groupid") int groupid)
	{
		Set<Activity_Entity> activityList = new HashSet<Activity_Entity>();
		Group_Entity tempGroup = groupService.getGroup(groupid);
		Set<Integer> tempList = tempGroup.getUserIds();
		for(Integer id : tempList)
		{
			User_Entity tempUser = userService.getUser(id);
			activityList.addAll(tempUser.getActivities());
		}
		return activityList;
	}
	
	/**
	 * this method sets a profile picture of a group
	 * @param groupid
	 * @param bytes (Body of JSON)
	 */
	@RequestMapping(method=RequestMethod.PUT, value="/putgroupprofpic/{groupid}")
	public void SetProfPicGroup(@PathVariable("groupid") int groupid, @RequestBody String bytes)
	{
		Group_Entity tempGroup = groupService.getGroup(groupid);
		tempGroup.setBytes(bytes);
		groupService.addGroup(tempGroup);
	}
	
	/**
	 * removes the groups profile picture
	 * @param groupid
	 */
	@RequestMapping(method=RequestMethod.DELETE, value="/deletegroupprofpic/{groupid}")
	public void DeleteProfPicGroup(@PathVariable("groupid") int groupid)
	{
		Group_Entity tempGroup = groupService.getGroup(groupid);
		tempGroup.setBytes(null);
		groupService.addGroup(tempGroup);
	}

	
	/**
	 * This method returns a sorted list of activities associated with a user.
	 * It returns a JSON Array of Activity objects with user names associated.
	 * @param userid
	 * @return JSON Array of Activity Objects
	 */
	@RequestMapping(method=RequestMethod.GET, value="/getuserfeed/{userid}", produces="application/json")
	public String getFeedActivities(@PathVariable("userid") int userid)
	{
		JSONArray tempArray = new JSONArray();
		User_Entity tempUser = userService.getUser(userid);
		Set<Group_Entity> tempGroup = tempUser.getGroups();
		for(Group_Entity temp : tempGroup)
		{
			Set<User_Entity> tempUsers = temp.getUsers();
			for(User_Entity tempUs : tempUsers)
			{
				Set<Activity_Entity> tempActs = tempUs.getActivities();
				String name = tempUs.getFirst_name();
				name = name + " " + tempUs.getLast_name();
				for(Activity_Entity tempA : tempActs)
				{
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
			}
		}
		
		Map<Date,JSONObject> tempMap = new TreeMap<Date,JSONObject>(Collections.reverseOrder());
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		//Date date = simpleDateFormat.parse("2018-09-09");
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
		//Set<JSONObject> sorted = new HashSet(tempJSON);
		return tempJSON.toString();
	}
}
