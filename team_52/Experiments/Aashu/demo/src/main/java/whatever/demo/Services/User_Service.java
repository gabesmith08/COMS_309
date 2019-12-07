package whatever.demo.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import whatever.demo.model.User;
import whatever.demo.repository.User_Repo;

@Service

public class User_Service {
	
	User_Repo user_repo;
		@Autowired
		public User_Service(User_Repo user_repo){
		
			
			this.user_repo=user_repo;
			
			
		}
		
		public List<User> getAllUsers() {
			List<User> list = new ArrayList<>();
			user_repo.findAll().forEach(list::add);
			return list;
		}
		
		public void addUser(User temp)
		{
			user_repo.save(temp);
		}
		
		public void updateUser(User User, String id)
		{
			user_repo.save(User);
		}
		
		public void deleteUser(long id)
		{
			user_repo.deleteById(id);
		}

		public User getUser(long id) {
			return user_repo.findById(id).get();
		}
		
		public User getUserEmail(String email) {
			return user_repo.findFirstByEmail(email);
		}
		
		

	
	

}
