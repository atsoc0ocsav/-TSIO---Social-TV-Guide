package iul.iscte.tsio.model;

import java.util.List;

import iul.iscte.tsio.interfaces.RecommendDAO;

public class RecommendDAOImpl implements RecommendDAO {
	
	// Perhaps change "Recommended Term" to "Like term"
	@Override
	public boolean createRecommendedRelationship(UserEntity user,
			ProgramEntity program) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteRecommendRelationship(UserEntity user,
			ProgramEntity program) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasUserRecommendProgram(UserEntity user,
			ProgramEntity program) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ProgramEntity> getAllRecommendProgramsByUser(UserEntity user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProgramEntity> getAllRecommendProgramsByFriends(UserEntity user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProgramEntity> getAllRecommendPrograms(UserEntity user) {
		// TODO Auto-generated method stub
		// Query: User Recommendations -> Friends who recommended the same movies -> Other movies they recommended -> Outer Join movies user hasn't watched yet
		// First Draft: Match (u1:User)-[l1:Liked]->(p1:Program)<-[l2:Liked]-(u2:User)-[l3:Liked]->(p2:Program) Where u1-[r3:Friend]->u2 AND NOT u1-[w:Watched]->p2 return p2
		return null;
	}

}
