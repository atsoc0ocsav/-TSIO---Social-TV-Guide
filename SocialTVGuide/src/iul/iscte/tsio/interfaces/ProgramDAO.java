package iul.iscte.tsio.interfaces;

import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.model.UserEntity;

import java.util.List;

public interface ProgramDAO {
	public ProgramEntity getProgramByTitle(String title);
	public boolean updateProgram(ProgramEntity userToUpdate);
	public boolean insertUser(ProgramEntity programToInsert);
	public boolean deleteProgram(ProgramEntity programToDelete);
	public List<ProgramEntity> getAllPrograms();
	public boolean createRecommendedRelationship(UserEntity user, ProgramEntity program);
	public boolean deleteRecommendRelationship(UserEntity user, ProgramEntity program);
	public boolean hasUserRecommendProgram(UserEntity user, ProgramEntity program);
	public List<ProgramEntity> getAllRecommendProgramsByUser(UserEntity user);
	public List<ProgramEntity> getAllRecommendProgramsByFriends(UserEntity user);
	public List<ProgramEntity> getAllRecommendPrograms(UserEntity user);
	public boolean createWatchedRelationship(UserEntity user, ProgramEntity program);
	public boolean deleteWatchedRelationship(UserEntity user, ProgramEntity program);
	public boolean hasUserWatchedProgram(UserEntity user, ProgramEntity program);
	public List<ProgramEntity> getAllWatchedProgramsByUser(UserEntity user);
	public List<ProgramEntity> getAllWatchProgramsByFriends(UserEntity user);
}
