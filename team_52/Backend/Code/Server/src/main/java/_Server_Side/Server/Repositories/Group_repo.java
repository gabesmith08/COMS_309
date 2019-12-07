package _Server_Side.Server.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import _Server_Side.Server.Entities.Group_Entity;

//the types in crudrepo are the things that it will be controlling, the String is the id of the entity

public interface Group_repo extends CrudRepository<Group_Entity, Integer>{
	
	
	
}
