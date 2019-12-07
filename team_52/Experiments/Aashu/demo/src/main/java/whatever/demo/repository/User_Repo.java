package whatever.demo.repository;

import org.springframework.data.repository.CrudRepository;

import whatever.demo.model.User;



public interface User_Repo extends CrudRepository<User, Long> {
	

	User findFirstByEmail(String email);

	
}
