package iul.iscte.tsio.interfaces;

import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.model.UserEntity;

import java.util.List;

public interface ProgramDAO {
	public ProgramEntity getProgramByTitle(String title);
	public boolean updateProgram(ProgramEntity programToUpdate);
	public boolean insertProgram(ProgramEntity programToInsert);
	public boolean deleteProgram(ProgramEntity programToDelete);
	public List<ProgramEntity> getAllPrograms();
	public boolean createLikedRelationship(UserEntity user, ProgramEntity program);
	public boolean deleteLikedRelationship(UserEntity user, ProgramEntity program);
	public boolean hasUserLikedProgram(UserEntity user, ProgramEntity program);
	public List<ProgramEntity> getAllLikedProgramsByUser(UserEntity user);
	public List<ProgramEntity> getAllLikedProgramsByFriends(UserEntity user);
	public boolean createWatchedRelationship(UserEntity user, ProgramEntity program);
	public boolean deleteWatchedRelationship(UserEntity user, ProgramEntity program);
	public boolean hasUserWatchedProgram(UserEntity user, ProgramEntity program);
	public List<ProgramEntity> getAllWatchedProgramsByUser(UserEntity user);
	public List<ProgramEntity> getAllWatchedProgramsByFriends(UserEntity user);
	public List<ProgramEntity> getProgramsWithRegex(String title);
	public List<ProgramEntity> getAllRecommendProgramsForUser(UserEntity user);
}
