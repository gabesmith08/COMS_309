package _Server_Side.Server.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import _Server_Side.Server.Entities.Activity_Entity;
import _Server_Side.Server.Services.Activity_Service;
import _Server_Side.Server.Services.Group_Service;
import _Server_Side.Server.Services.User_Service;


@RestController
public class Activitiy_Contoller {
	
	@Autowired
	private Activity_Service activityService;
	@Autowired
	private User_Service userService;
	@Autowired 
	private Group_Service groupService;
	
	/**
	 * get all of the activities in the database
	 * @return JSON array of activity objects
	 */
	@RequestMapping(method=RequestMethod.GET, value="/getallactivities")
	public List<Activity_Entity> getAllActivity()
	{
		return activityService.getAllActivity();
	}
}
