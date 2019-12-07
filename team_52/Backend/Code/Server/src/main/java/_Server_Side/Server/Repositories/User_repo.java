package _Server_Side.Server.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import _Server_Side.Server.Entities.User_Entity;
import org.springframework.stereotype.Repository;

//the types in crudrepo are the things that it will be controlling, the String is the id of the entity

@Repository
public interface User_repo extends CrudRepository<User_Entity, Integer>{


	/**
	 * This is the database call we are making to retrieve a User_Entity based on email and password.
	 * @param email
	 * @param password
	 * @return User_Entity
	 */
	User_Entity findFirstByEmailAndPassword(String email, String password);
	
	/**
	 * This method finds the first User_Entity associated with an email.
	 * @param email
	 * @return User_Entity
	 */
	User_Entity findFirstByEmail(String email);
	
}
