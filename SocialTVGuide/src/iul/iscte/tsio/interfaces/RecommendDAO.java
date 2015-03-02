package iul.iscte.tsio.interfaces;

import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.model.UserEntity;

import java.util.List;

public interface RecommendDAO {
	public boolean createRecommendedRelationship(UserEntity user, ProgramEntity program);
	public boolean deleteRecommendRelationship(UserEntity user, ProgramEntity program);
	public boolean hasUserRecommendProgram(UserEntity user, ProgramEntity program);
	public List<ProgramEntity> getAllRecommendProgramsByUser(UserEntity user);
	public List<ProgramEntity> getAllRecommendProgramsByFriends(UserEntity user);
	public List<ProgramEntity> getAllRecommendPrograms(UserEntity user);
}
