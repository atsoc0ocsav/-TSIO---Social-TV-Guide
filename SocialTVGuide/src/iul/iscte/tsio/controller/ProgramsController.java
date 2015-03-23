package iul.iscte.tsio.controller;

import iul.iscte.tsio.model.ProgramDAOImpl;
import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.model.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class ProgramsController {
	private static ProgramsController instance = null;

	private ProgramsController() {
	};

	public static ProgramsController getInstance() {
		if (instance == null) {
			synchronized (UsersController.class) {
				instance = new ProgramsController();
			}
		}
		return instance;
	};

	public long insertProgram(ProgramEntity programToInsert) {
		return ProgramDAOImpl.getInstance().insertProgram(programToInsert);
	};

	public ProgramEntity getProgramByTitle(String title) {
		return ProgramDAOImpl.getInstance().getProgramByTitle(title);
	};

	public boolean updateProgram(ProgramEntity programToUpdate) {
		return ProgramDAOImpl.getInstance().updateProgram(programToUpdate);
	};

	public boolean deleteProgram(ProgramEntity programToDelete) {
		return ProgramDAOImpl.getInstance().deleteProgram(programToDelete);
	};

	public ArrayList<ProgramEntity> getRecommendShowsForUser(UserEntity user) {
		return (ArrayList<ProgramEntity>) ProgramDAOImpl.getInstance()
				.getAllRecommendProgramsForUser(user);
	};

	public boolean likeShow(UserEntity user, ProgramEntity program) {
		return ProgramDAOImpl.getInstance().createLikedRelationship(user,
				program);
	};

	public boolean watchShow(UserEntity user, ProgramEntity program) {
		return ProgramDAOImpl.getInstance().createWatchedRelationship(user,
				program);
	};

	public List<ProgramEntity> getProgramsWithRegex(String title) {
		return ProgramDAOImpl.getInstance().getProgramsWithRegex(title);
	};

	public List<ProgramEntity> getAllPrograms() {
		return ProgramDAOImpl.getInstance().getAllPrograms();
	}

	public List<ProgramEntity> getAllLikedProgramsByUser(UserEntity user) {
		return ProgramDAOImpl.getInstance().getAllLikedProgramsByUser(user);
	}

	public boolean deleteWatch(UserEntity user, ProgramEntity program) {
		ProgramDAOImpl.getInstance().deleteLikedRelationship(user,
				program);
		return ProgramDAOImpl.getInstance().deleteWatchedRelationship(user,
				program);
	}

	public boolean hasUserWatchedProgram(UserEntity user, ProgramEntity program) {
		return ProgramDAOImpl.getInstance()
				.hasUserWatchedProgram(user, program);
	}

	public List<ProgramEntity> getAllWatchedProgramsByUser(UserEntity user) {
		return ProgramDAOImpl.getInstance().getAllWatchedProgramsByUser(user);
	}

	public List<ProgramEntity> getAllWatchedProgramsByFriends(UserEntity user) {
		return ProgramDAOImpl.getInstance()
				.getAllWatchedProgramsByFriends(user);
	}

	public boolean unlikeProgram(UserEntity user, ProgramEntity program) {
		return ProgramDAOImpl.getInstance().deleteLikedRelationship(user,
				program);
	}

	public boolean hasUserLikedProgram(UserEntity user, ProgramEntity program) {
		return ProgramDAOImpl.getInstance().hasUserLikedProgram(user, program);
	}
}
