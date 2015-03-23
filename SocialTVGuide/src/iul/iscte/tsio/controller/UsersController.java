package iul.iscte.tsio.controller;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import iul.iscte.tsio.model.ProgramDAOImpl;
import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;

public class UsersController {
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

	public long createUser(UserEntity userToInsert) {
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

	public boolean createFriendship(UserEntity user, UserEntity friend) {
		return UserDAOImpl.getInstance().createFriendshipRelationship(user,
				friend);
	}

	public boolean deleteFriendship(UserEntity user, UserEntity friend) {
		return UserDAOImpl.getInstance().deleteFriendshipRelationship(user,
				friend);
	}

	public List<UserEntity> getAllUsers() {
		return UserDAOImpl.getInstance().getAllUsers();
	}

	public boolean isUserFriend(UserEntity user, UserEntity friend) {
		return UserDAOImpl.getInstance().isUserFriend(user, friend);
	}

	public List<UserEntity> getAllFriends(UserEntity user) {
		return UserDAOImpl.getInstance().getAllFriends(user);
	}

	public List<UserEntity> getUsersWithRegex(String title) {
		return UserDAOImpl.getInstance().getUsersWithRegex(title);
	};
}
