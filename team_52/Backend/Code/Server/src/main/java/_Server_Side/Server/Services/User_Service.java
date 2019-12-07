package _Server_Side.Server.Services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _Server_Side.Server.Entities.User_Entity;
import _Server_Side.Server.Repositories.User_repo;

@Service
public class User_Service {

	@Autowired
	private User_repo userRepo;

	/**
	 * This method is for development purposes only. It returns all the Users in the database.
	 * @return List of User Entities in the Database
	 */
	public List<User_Entity> getAllUsers() {
		List<User_Entity> list = new ArrayList<>();
		userRepo.findAll().forEach(list::add);
		return list;
	}

	/**
	 * This method saves a User_Entity to the database
	 * @param User_Entity
	 */
	public void addUser(User_Entity temp)
	{
		userRepo.save(temp);
	}

	/**
	 * This method updates the user's information if the user already exists in the database.
	 * @param User_Entity
	 * @param id
	 */
	public void updateUser(User_Entity User, String id)
	{
		userRepo.save(User);
	}

	/**
	 * Selects a user based on id and removes them from the database.
	 * @param id
	 */
	public void deleteUser(int id)
	{
		userRepo.deleteById(id);
	}

	/**
	 * Returns a User_Entity java object from the database.
	 * @param id
	 * @return User_Entity
	 */
	public User_Entity getUser(int id) {
		return userRepo.findById(id).get();
	}

	/**
	 * Used for the login method. It matches the email and password of a user and return a User_Entity object.
	 * @param email
	 * @param password
	 * @return User_Entity
	 */
	public User_Entity getUserEmailPassword(String email, String password) {
		return userRepo.findFirstByEmailAndPassword(email, password);
	}

	/**
	 * Finds a user based on email
	 * @param email
	 * @return User_Entity
	 */
	public User_Entity getUserEmail(String email)
	{
		return userRepo.findFirstByEmail(email);
	}

	/**
	 * This method is used by the websocket to store the .txt files to the servers file system.
	 * @param data
	 * @return boolean
	 */
	public boolean save_data(String data)
	{
		try {
			BufferedWriter out = new BufferedWriter(
					new FileWriter("/data.txt",true));
			out.write(data);
			out.close();
		}
		catch (IOException e) {
			System.out.println("exception occured" + e);
		}
		return false;
	}

}
