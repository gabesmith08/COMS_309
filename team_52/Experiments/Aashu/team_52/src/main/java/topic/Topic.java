package topic;

public class Topic {
	private String ID;
	private String Name;
	private String description;
	public String getID() {
		return ID;
	}
	
	public Topic(String iD, String name, String description) {
		super();
		ID = iD;
		Name = name;
		this.description = description;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
