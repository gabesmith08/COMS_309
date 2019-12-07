package _Server_Side.Server.Repositories;

import java.util.List;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import _Server_Side.Server.Entities.Activity_Entity;
import _Server_Side.Server.Entities.User_Entity;
import org.springframework.stereotype.Repository;


@Repository
public interface Activity_repo extends CrudRepository<Activity_Entity, Integer> {
	
}
