package iul.iscte.tsio.controller;

import iul.iscte.tsio.model.ProgramDAOImpl;
import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.model.UserEntity;

import java.util.ArrayList;

public class ProgramsController {
	private static ProgramsController instance = null;

	private ProgramsController() {};

	public static ProgramsController getInstance() {
		if (instance == null) {
			synchronized (UsersController.class) {
				instance = new ProgramsController();
			}
		}
		return instance;
	};

	public boolean createProgram(ProgramEntity programToInsert) {
		return ProgramDAOImpl.getInstance().insertProgram(programToInsert);
	};

	public ProgramEntity getProgramByName(String title) {
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
				.getAllLikedProgramsByFriends(user);
	};

	public boolean likeShow(UserEntity user, ProgramEntity program) {
		return ProgramDAOImpl.getInstance().createLikedRelationship(user,
				program);
	};

	public boolean watchShow(UserEntity user, ProgramEntity program) {
		return ProgramDAOImpl.getInstance().createWatchedRelationship(user,
				program);
	};
}
