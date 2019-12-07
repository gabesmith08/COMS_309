package io.javabrains.springbootstarter.groups;

public class Group_Model {
	private String id;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Group_Model(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	

}
