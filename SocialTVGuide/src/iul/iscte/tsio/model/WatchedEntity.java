package iul.iscte.tsio.model;

public class WatchedEntity {
	private ProgramEntity program;
	private UserEntity user;
	
	public WatchedEntity(ProgramEntity program, UserEntity user){
		this.program = program;
		this.user = user;
	}
	
	public ProgramEntity getProgram() {
		return program;
	}

	public UserEntity getUser() {
		return user;
	}
}
