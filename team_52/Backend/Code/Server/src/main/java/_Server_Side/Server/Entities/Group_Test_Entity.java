package _Server_Side.Server.Entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Group_Test_Entity {
	
	//------Values-------
	
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int Groupid;
	
	//This is many users to one group
	//so you can retrieve all users in one
	//group using the group_entity repo
	
    @ManyToMany(mappedBy = "Groups", fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Set<User_Entity> Users = new HashSet<User_Entity>();

	@Column(name="Group_Name")
	@Size(max=40)
	public String name;
	
	@Column(name="Group_Bio")
	public String Bio;
	
	//-------Values--------
	
	//----Getters and Setters------

	public int getId() {
		return Groupid;
	}

	public void setId(int id) {
		this.Groupid = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return Bio;
	}

	public void setBio(String bio) {
		Bio = bio;
	}
	
	public Set<User_Entity> getUsers() {
		return Users;
	}

	public void setUsers(Set<User_Entity> users) {
		Users = users;
	}
	
	public void addUser(User_Entity user) {
		Users.add(user);
	}
	
	//----Getters and Setters------
	

}
