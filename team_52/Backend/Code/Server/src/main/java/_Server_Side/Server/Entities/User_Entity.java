package _Server_Side.Server.Entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class User_Entity {

	// ----------------Values------------------
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int Userid;
	
	//Many groups to one user
	//so can get all groups for one user
	//from this value
	
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinTable(name = "GrouptoUsers",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "Userid"),
        inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "Id"))
    @JsonIgnore
    private Set<Group_Entity> Groups = new HashSet<Group_Entity>();
    
    @OneToMany(mappedBy = "userActivity")
    @JsonIgnoreProperties
    private Set<Activity_Entity> activities = new HashSet<Activity_Entity>();
    
    
    @Column(name = "ownsGroupId")
    private Integer ownsGroupId = null;

	@Column(name="first")
	@Size(max=20)
	public String first_name;
	
	@Column(name="last")
	@Size(max=20)
	public String last_name;
	
	@Column(name="email")
	@Size(max=50)
	public String email;
	
	@JsonFormat(pattern = "YYYY-MM-dd")
	@Column(name="date_of_birth")
	public Date DOB;
	
	@Column(name="weight")
	public float weight;
	
	@Column(name="bio")
	public String bio;
	
	@Column(name="password")
	@Size(max=40)
	public String password;
	
	public User_Entity() {}
	
	public User_Entity(int userid, Set<Group_Entity> groups, Set<Activity_Entity> activities,
			@Size(max = 20) String first_name, @Size(max = 20) String last_name, @Size(max = 50) String email, Date dOB,
			float weight, String bio, @Size(max = 40) String password) {
		super();
		Userid = userid;
		Groups = groups;
		this.activities = activities;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		DOB = dOB;
		this.weight = weight;
		this.bio = bio;
		this.password = password;
	}

	// ----------------Values-------------------
	
	// ------------Getters & Setters-------------
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return Userid;
	}
	public void setId(int id) {
		this.Userid = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public String getBio() {
		return bio;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public Set<Group_Entity> getGroups() {
		return Groups;
	}

	public void setGroups(Set<Group_Entity> groups) {
		Groups = groups;
	}
	
	public void addGroup(Group_Entity group) {
		Groups.add(group);
	}

	public Set<Activity_Entity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity_Entity> activities) {
		this.activities = activities;
	}
	
	
	public Integer getOwnsGroupId() {
		return ownsGroupId;
	}

	public void setOwnsGroupId(Integer ownsGroupId) {
		this.ownsGroupId = ownsGroupId;
	}
	
	
	// ------------Getters & Setters-------------

}
