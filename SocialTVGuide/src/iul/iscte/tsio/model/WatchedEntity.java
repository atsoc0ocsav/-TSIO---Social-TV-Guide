package iul.iscte.tsio.model;

public class WatchedEntity {
	private ProgramEntity program;
	private UserEntity user;
	private boolean recommended;
	
	public WatchedEntity(ProgramEntity program, UserEntity user, boolean recommends){
		this.program = program;
		this.user = user;
		this.recommended = recommended;
	}
	
	public ProgramEntity getProgram() {
		return program;
	}

	public UserEntity getUser() {
		return user;
	}

	public boolean isRecommended() {
		return recommended;
	}
}
