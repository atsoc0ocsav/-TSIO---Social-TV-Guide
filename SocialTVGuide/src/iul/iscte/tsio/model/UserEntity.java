package iul.iscte.tsio.model;

public class UserEntity {
	//Create constraint to guarantee the username is unique
	private String username;
	private String email;
	
	public UserEntity(String username, String email){
		this.username = username;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}
	
	public String getEmail(){
		return email;
	}
	
}
