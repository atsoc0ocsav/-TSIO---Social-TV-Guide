package iul.iscte.tsio.interfaces;

import iul.iscte.tsio.model.UserEntity;

import java.util.List;

public interface UserDAO {
	public UserEntity getUserByEmail(String email);
	public boolean updateUser(UserEntity userToUpdate);
	public long insertUser(UserEntity userToInsert);
	public boolean deleteUser(UserEntity userToDelete);
	public UserEntity getUserByName(String name);
	public List<UserEntity> getAllUsers();
	public boolean createFriendshipRelationship(UserEntity user, UserEntity friend);
	public boolean deleteFriendshipRelationship(UserEntity user, UserEntity friend);
	public boolean isUserFriend(UserEntity user, UserEntity friend);
	public List<UserEntity> getAllFriends(UserEntity user);
	public List<UserEntity> getUsersWithRegex(String name);
}
