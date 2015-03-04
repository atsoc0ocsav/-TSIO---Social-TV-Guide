package iul.iscte.tsio.controller;

import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;

public class UsersController {
	private UserEntity loggedUser;
	private static UsersController instance = null;

	private UsersController() {
	}

	public static UsersController getInstance() {
		if (instance == null) {
			synchronized (UsersController.class) {
				instance = new UsersController();
			}
		}
		return instance;
	}

	public boolean login(String email) {
		UserEntity loggedUser = UserDAOImpl.getInstance().getUserByEmail(email);
		if (loggedUser != null) {
			this.loggedUser = loggedUser;
			return true;
		}
		return false;
	}

	public boolean createUser(UserEntity userToInsert) {
		return UserDAOImpl.getInstance().insertUser(userToInsert);
	}

	public UserEntity getUserByName(String username) {
		return UserDAOImpl.getInstance().getUserByName(username);
	};

	public UserEntity getUserByEmail(String email) {
		return UserDAOImpl.getInstance().getUserByEmail(email);
	}

	public boolean updateUser(UserEntity userToUpdate) {
		return UserDAOImpl.getInstance().updateUser(userToUpdate);
	};

	public boolean deleteUser(UserEntity userToDelete) {
		return UserDAOImpl.getInstance().deleteUser(userToDelete);
	};
	
	public boolean createFriendship(UserEntity user, UserEntity friend){
		return UserDAOImpl.getInstance().createFriendshipRelationship(user, friend);
	}
	
	public boolean deleteFriendship(UserEntity user, UserEntity friend){
		return UserDAOImpl.getInstance().deleteFriendshipRelationship(user, friend);
	}
}
