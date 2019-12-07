package User_tests_Database.tests.repositories;



import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import User_tests_Database.tests.users.User_Model;

//the types in crudrepo are the things that it will be controlling, the String is the id of the entity

public interface User_Repository extends CrudRepository<User_Model, Long>{
	
	User_Model findFirstByEmail(String email);
	
}
