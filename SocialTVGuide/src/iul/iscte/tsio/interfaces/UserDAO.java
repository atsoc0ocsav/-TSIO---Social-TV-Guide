package iul.iscte.tsio.interfaces;

import iul.iscte.tsio.model.UserEntity;

import java.util.List;

public interface UserDAO {

	public UserEntity getUserByEmail(String email);

//	public boolean updateUser(UserEntity userToUpdate, String oldEmail);

	public boolean insertUser(UserEntity userToInsert);

	public boolean deleteUser(UserEntity userToDelete);

	public UserEntity getUserByName(String name);

	public List<UserEntity> getAllUsers();

}
