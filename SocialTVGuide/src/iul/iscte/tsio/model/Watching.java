package iul.iscte.tsio.model;

public class Watching {
	private Program program;
	private UserEntity user;
	private boolean recommended;
	
	public Watching(Program program, UserEntity user, boolean recommends){
		this.program = program;
		this.user = user;
		this.recommended = recommended;
	}
	
	public Program getProgram() {
		return program;
	}

	public UserEntity getUser() {
		return user;
	}

	public boolean isRecommended() {
		return recommended;
	}
}
