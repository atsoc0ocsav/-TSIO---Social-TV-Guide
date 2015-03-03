package iul.iscte.tsio.controller;

import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;

import java.util.ArrayList;

public class ProgramsController {
	private static ProgramsController instance = null;

	private ProgramsController() {}

	public static ProgramsController getInstance() {
		if (instance == null) {
			synchronized (UsersController.class) {
				instance = new ProgramsController();
			}
		}
		return instance;
	}

	public boolean create(UserEntity userToInsert) {
		return UserDAOImpl.getInstance().insertUser(userToInsert);
	}

	public String read() {
		return "";
	};

	public void update() {

	};

	public void delete() {
	};
	
	public ArrayList<ProgramEntity> getRecommendShowsForUser(UserEntity user){
		
		return new ArrayList();
	}
}
