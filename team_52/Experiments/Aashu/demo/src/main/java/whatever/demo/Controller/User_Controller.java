package whatever.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import whatever.demo.Services.User_Service;
import whatever.demo.model.User;



@RestController

public class User_Controller {
	
	@Autowired
	private User_Service userService;
	
	@RequestMapping(method=RequestMethod.GET, value="/getuser")
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}
	@GetMapping(value="/getuserfromid/{id}")
	public User getUser(@PathVariable long id)
	{
		User temp = userService.getUser(id);
		return temp;
		
	}
	//getuserfromid/1
	//getuserfromid?id=1
	@GetMapping(value="/getuserfromid/")
	public User get_User(@RequestParam("id") long id )
	{
		User temp = userService.getUser(id);
		return temp;
		
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/adduser")
	public void addUser(@RequestBody User user)
	{
		userService.addUser(user);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/putuser")
	public void updateUser(@RequestBody User user)
	{
		userService.addUser(user);
	}
	
	@DeleteMapping(value="/deleteuser/{id}")
	public void deleteUser(@PathVariable long id)
	{
		userService.deleteUser(id);
	}
	
	@GetMapping(value="/getuserfromemail/{email}")
	public User getUser(@PathVariable String email)
	{
		User temp = userService.getUserEmail(email);
		return temp;
		
	}
	
	@GetMapping(value="/emailExists/{email}")
	public String emailExists(@PathVariable String email)
	{
		User temp = userService.getUserEmail(email);
		if(temp == null) {
			return "{ 'email': false }";
		}
		return "{'email': true }";
	}


}
