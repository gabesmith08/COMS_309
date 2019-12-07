package _Server_Side.Server.Entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Group_Entity {
	
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

	@Column
    @ElementCollection(targetClass=Integer.class)
    private Set<Integer> UserIds = new HashSet<Integer>();

	@Column(name="Group_Name")
	@Size(max=40)
	public String name;
	
	@Column(name="Group_Bio")
	public String Bio;
	
	@Column(name="ZipCode")
	public int zip;
	
	@Column(name="profpic")
	public String bytes;
	


	public Group_Entity() {}
	
	
	
	public Group_Entity(int groupid, Set<User_Entity> users, Set<Integer> userIds, @Size(max = 40) String name,
			String bio, int zip, String bytes) {
		super();
		Groupid = groupid;
		Users = users;
		UserIds = userIds;
		this.name = name;
		Bio = bio;
		this.zip = zip;
		this.bytes = bytes;
	}

	//-------Values--------
	
	//----Getters and Setters------
	
	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

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
	@JsonIgnore
	public Set<User_Entity> getUsers() {
		return Users;
	}

	public void setUsers(Set<User_Entity> users) {
		Users = users;
	}

	public void addUser(User_Entity user) {
		Users.add(user);
		UserIds.add(user.getId());
	}
	
	public void removeUser(User_Entity user) {
		Users.remove(user);
	}

	public Set<Integer> getUserIds() {
		return UserIds;
	}

	public void setUserIds(Set<Integer> userIds) {
		UserIds = userIds;
	}
	

	public String getBytes() {
		return bytes;
	}

	public void setBytes(String bytes) {
		this.bytes = bytes;
	}
	
	
	//----Getters and Setters------
	

}
