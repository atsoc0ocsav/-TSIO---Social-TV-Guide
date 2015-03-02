package iul.iscte.tsio.interfaces;

import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.model.UserEntity;

import java.util.List;

public interface WatchedDAO {
	public boolean createWatchedRelationship(UserEntity user, ProgramEntity program);
	public boolean deleteWatchedRelationship(UserEntity user, ProgramEntity program);
	public boolean hasUserWatchedProgram(UserEntity user, ProgramEntity program);
	public List<ProgramEntity> getAllWatchedProgramsByUser(UserEntity user);
	public List<ProgramEntity> getAllWatchProgramsByFriends(UserEntity user);
}
