package User_tests_Database.tests.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import User_tests_Database.tests.services.User_Service;
import User_tests_Database.tests.users.User_Model;

@RestController
public class User_Controller {
	
	@Autowired
	private User_Service userService;
	
	@RequestMapping(method=RequestMethod.GET, value="/getuser")
	public List<User_Model> getAllUsers()
	{
		return userService.getAllUsers();
	}
	
	@GetMapping(value="/getuserfromid/{id}")
	public User_Model getUser(@PathVariable long id)
	{
		User_Model temp = userService.getUser(id);
		return temp;
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/adduser")
	public void addUser(@RequestBody User_Model user)
	{
		userService.addUser(user);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/putuser")
	public void updateUser(@RequestBody User_Model user)
	{
		userService.addUser(user);
	}
	
	@DeleteMapping(value="/deleteuser/{id}")
	public void deleteUser(@PathVariable long id)
	{
		userService.deleteUser(id);
	}
	
	@GetMapping(value="/getuserfromemail/{email}")
	public User_Model getUser(@PathVariable String email)
	{
		User_Model temp = userService.getUserEmail(email);
		return temp;
		
	}
	
	@GetMapping(value="/emailExists/{email}")
	public String emailExists(@PathVariable String email)
	{
		User_Model temp = userService.getUserEmail(email);
		if(temp == null) {
			return "{ 'email': false }";
		}
		return "{'email': true }";
	}


}
