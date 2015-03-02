package iul.iscte.tsio.interfaces;

import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.model.UserEntity;

import java.util.List;

public interface FriendsDAO {
	public boolean createFriendshipRelationship(UserEntity user, UserEntity friend);
	public boolean deleteFriendshipRelationship(UserEntity user, UserEntity friend);
	public boolean isUserFriend(UserEntity user, UserEntity friend);
	public List<UserEntity> getAllFriends(UserEntity user);
	public List<ProgramEntity> getAllFriendsRecommendations(UserEntity user);
}
