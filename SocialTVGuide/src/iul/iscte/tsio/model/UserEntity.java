package iul.iscte.tsio.model;

public class UserEntity {
	// Create constraint to guarantee the username is unique
	private long nodeId;
	private String username;
	private String email;

	public UserEntity(long nodeId, String username, String email) {
		this.nodeId = nodeId;
		this.username = username;
		this.email = email;
	}

	public UserEntity(String username, String email) {
		this.username = username;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public long getNodeId() {
		return nodeId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNodeId(long nodeId) {
		this.nodeId = nodeId;
	}

	@Override
	public String toString() {
		return username;
	}
}
