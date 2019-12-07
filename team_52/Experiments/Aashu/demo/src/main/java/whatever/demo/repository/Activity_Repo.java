package whatever.demo.repository;

import org.springframework.data.repository.CrudRepository;

import whatever.demo.model.Activity_Entity;
import whatever.demo.model.User;

public interface Activity_Repo extends CrudRepository<Activity_Entity, Long> {
	

	

	
}
