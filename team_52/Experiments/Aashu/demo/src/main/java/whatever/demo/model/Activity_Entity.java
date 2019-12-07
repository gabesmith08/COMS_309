package whatever.demo.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name ="activity_type")
public class Activity_Entity {
	
	//-----------Values-------------
	
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	
	//can get the activities for one user with
	//this value using the activity_repo
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name ="user_id")
	public  User user;
	
	@Column(name="Activity_type")
	public String type;
	
	@Column(name="Distance")
	public float distance;
	
	//---Need to find format----!!!
	@Column(name="Time")
	public String time;
	
	@Column(name="Location")
	public String location;
	
	//------Values------
	
	
	//-----Getters and Setters------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@JsonIgnore
	public User getUserID() {
		return user;
	}

	public void setUserID(User userID) {
		user = userID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	//-----Getters and Setters------


}
