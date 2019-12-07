package _Server_Side.Server.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import _Server_Side.Server.Entities.Activity_Entity;
import _Server_Side.Server.Repositories.Activity_repo;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class Activity_Service {
	
	
	//
	@Autowired
	private Activity_repo activityRepo;

	/**
	 * Testing method, it grabs all the activities within the database.
	 * @return List of Activity_Entities
	 */
	public List<Activity_Entity> getAllActivity() {
		List<Activity_Entity> list = new ArrayList<>();
		activityRepo.findAll().forEach(list::add);
		return list;

	}

	/**
	 * Adds an activity to the database.
	 * @param Activity_Entity
	 */
	public void addActivity(Activity_Entity Activity) {
		// TODO Auto-generated method stub
		activityRepo.save(Activity);
	}

	/**
	 * Removes an Activity from the database.
	 * @param id
	 */
	public void deleteActivity(int id) {
		// TODO Auto-generated method stub
		activityRepo.deleteById(id);
	}

	/**
	 * returns an Activity based on an id
	 * @param id
	 * @return Activity_Entity
	 */
	public Activity_Entity getActivity(int id) {
		// TODO Auto-generated method stub
		return activityRepo.findById(id).get();
	}

}
