package iul.iscte.tsio.model;

public class Watching {
	private Program program;
	private User user;
	private boolean recommended;
	
	public Watching(Program program, User user, boolean recommends){
		this.program = program;
		this.user = user;
		this.recommended = recommended;
	}
	
	public Program getProgram() {
		return program;
	}

	public User getUser() {
		return user;
	}

	public boolean isRecommended() {
		return recommended;
	}
}
