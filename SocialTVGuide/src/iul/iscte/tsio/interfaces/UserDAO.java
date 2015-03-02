package iul.iscte.tsio.interfaces;

import iul.iscte.tsio.model.UserEntity;

import java.util.List;

public interface UserDAO {

	public List<UserEntity> getUsersByParameters(String nome, String escola,
			boolean aSeguir, String email);

	public UserEntity getUserByEmail(String email);

	public boolean updateUser(UserEntity userToUpdate, String oldEmail);

	public boolean insertUser(UserEntity userToInsert);

	public boolean deleteUser(UserEntity userToDelete);

	public UserEntity getUser(String name);

	public List<UserEntity> getAllUsers();

	public boolean createFollow(String follower, String followed);

	public String[] getSchools();
}
