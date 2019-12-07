package _Server_Side.Server.Entities;

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
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	public User_Entity userActivity;
	
	@Column(name="user_tag")
	public int userid;

	@Column(name="Activity_type")
	public String type;
	
	@Column(name="Distance")
	public float distance;
	
	//---Need to find format----!!!
	@Column(name="Time")
	public String time;
	
	@Column(name="Start_Time")
	public String starttime;
	
	@Column(name="End_Time")
	public String endtime;
	
	@Column(name="Date")
	public String date;
	
	public Activity_Entity() {}
	
	

	public Activity_Entity(int id, User_Entity userActivity, String type, float distance, String time, String starttime,
			String endtime, String date) {
		super();
		this.id = id;
		this.userActivity = userActivity;
		this.type = type;
		this.distance = distance;
		this.time = time;
		this.starttime = starttime;
		this.endtime = endtime;
		this.date = date;
	}



	//------Values------
	
	
	//-----Getters and Setters------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	@JsonIgnore
	public User_Entity getUser() {
		return userActivity;
	}
	@JsonIgnore
	public void setUser(User_Entity user) {
		this.userActivity = user;
	}
	
	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	//-----Getters and Setters------


}
