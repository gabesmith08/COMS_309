package User_tests_Database.tests.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity //this specifies a model to create in the database
public class User_Model
{
	@Id //this is the unique Id for the relational database
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(name="first")
	private String first_Name;
	@Column(name="last")
	private String last_Name;
	@Column(name="email", unique=true)
	private String email;
	@Column(name="user_name")
	private String user_Name;
	
	public User_Model()
	{
		
	}
	
	public User_Model(long id, String first_Name, String last_Name, String email, String user_Name)
	{
		this.id = id;
		this.first_Name = first_Name;
		this.last_Name = last_Name;
		this.email = email;
		this.user_Name = user_Name;
	}
		
		public long getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFirst_Name() {
			return first_Name;
		}

		public void setFirst_Name(String first_Name) {
			this.first_Name = first_Name;
		}

		public String getLast_Name() {
			return last_Name;
		}

		public void setLast_Name(String last_Name) {
			this.last_Name = last_Name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getUser_Name() {
			return user_Name;
		}

		public void setUser_Name(String user_Name) {
			this.user_Name = user_Name;
		}
}


