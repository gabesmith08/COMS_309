package _Server_Side.Server.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import _Server_Side.Server.Entities.Group_Entity;
import _Server_Side.Server.Entities.User_Entity;
import _Server_Side.Server.Repositories.Group_repo;

@Service
public class Group_Service {
	
	@Autowired
	private Group_repo groupRepo;

	/**
	 * This is a testing method that returns all of the users in the database.
	 * @return List of all Group_Entities
	 */
	public List<Group_Entity> getAllGroups() {
		List<Group_Entity> list = new ArrayList<>();
		groupRepo.findAll().forEach(list::add);
		return list;
	}

	/**
	 * Find a certin group by id.
	 * @param id
	 * @return Group_Entity
	 */
	public Group_Entity getGroup(int id) {
		return groupRepo.findById(id).get();
	}

	/**
	 * Adds a group to the database.
	 * @param group
	 */
	public void addGroup(Group_Entity group) {
		groupRepo.save(group);
		
	}

	/**
	 * Removes a group from the database.
	 * @param id
	 */
	public void deleteGroup(int id) {
		groupRepo.deleteById(id);
		
	}
	
	/**
	 * Get the Users associated with a group.
	 * @param Group_Entity
	 * @return Set of User_Entities
	 */
	public Set<User_Entity> getUsers(Group_Entity group)
	{
		return group.getUsers();
	}

}
