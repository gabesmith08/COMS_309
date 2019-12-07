package User_tests_Database.tests.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import User_tests_Database.tests.repositories.User_Repository;
import User_tests_Database.tests.users.User_Model;

@Service
public class User_Service {

	//Use this instance of the User_Repository to connect 
	//your commands to changes in the database
	@Autowired
	private User_Repository userRepo;
	
	public List<User_Model> getAllUsers() {
		List<User_Model> list = new ArrayList<>();
		userRepo.findAll().forEach(list::add);
		return list;
	}
	
	public void addUser(User_Model temp)
	{
		userRepo.save(temp);
	}
	
	public void updateUser(User_Model User, String id)
	{
		userRepo.save(User);
	}
	
	public void deleteUser(long id)
	{
		userRepo.deleteById(id);
	}

	public User_Model getUser(long id) {
		return userRepo.findById(id).get();
	}
	
	public User_Model getUserEmail(String email) {
		return userRepo.findFirstByEmail(email);
	}
	
	

}
