package _Server_Side.Server.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _Server_Side.Server.Entities.User_Entity;
import _Server_Side.Server.Repositories.GrouptoUser_repo;
import _Server_Side.Server.Repositories.User_repo;

@Service
public class User_Service {
	
	@Autowired
	private User_repo userRepo;
	
	public List<User_Entity> getAllUsers() {
		List<User_Entity> list = new ArrayList<>();
		userRepo.findAll().forEach(list::add);
		return list;
	}
	
	public void addUser(User_Entity temp)
	{
		userRepo.save(temp);
	}
	
	public void updateUser(User_Entity User, String id)
	{
		userRepo.save(User);
	}
	
	public void deleteUser(int id)
	{
		userRepo.deleteById(id);
	}

	public User_Entity getUser(int id) {
		return userRepo.findById(id).get();
	}
	
	public User_Entity getUserEmailPassword(String email, String password) {
		return userRepo.findFirstByEmailAndPassword(email, password);
	} 

}
