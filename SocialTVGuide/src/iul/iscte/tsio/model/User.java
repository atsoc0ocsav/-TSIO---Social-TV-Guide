package iul.iscte.tsio.model;

import iul.iscte.tsio.controller.Neo4jControllerUsers;

public class User {
	//Create constraint to guarantee the username is unique
	private String username;
	
	public User(String username){
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	
	public static User getUser(String username){
		return Neo4jControllerUsers.getInstance().read(username);
	}
}
