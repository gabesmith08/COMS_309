package whatever.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import whatever.demo.model.Activity_Entity;
import whatever.demo.model.User;
import whatever.demo.repository.Activity_Repo;
import whatever.demo.repository.User_Repo;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	User_Repo user_repo;
	@Autowired
	Activity_Repo activity_repo;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		//created user without iD
		User user = new User();
		
		user.setEmail("whatever@whaterver.com");
		user.setAge(18);
		user.setHeight(80);
		user.setName("whatever");
		user.setWeight(80);
		user.setUsername("whatevr");
		user_repo.save(user);
		
		
		Optional<User> tempUser = user_repo.findById((long) 1);//OPTIONAL TAKES CARE OF CRASHES
		if(tempUser.isPresent()) {
			//CREATED ACTIVITY WITHOUT ID
			Activity_Entity activity = new Activity_Entity();
			
			activity.setDistance(19);
			activity.setUserID(tempUser.get());
			activity.setLocation("Ames, Iowa");
			activity.setTime("18");
			activity.setType("Running");
			activity_repo.save(activity);
			
			activity = new Activity_Entity();
			
			activity.setDistance(21);
			activity.setUserID(tempUser.get());
			activity.setLocation("Desmoines, Iowa");
			activity.setTime("28");
			activity.setType("Track");
			activity_repo.save(activity);
			
		}
		
	}
	

}
