package iul.iscte.tsio.tests;

import static org.junit.Assert.assertTrue;
import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;
import iul.iscte.tsio.server.Server;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserGetUsersTest {

	private static UserEntity user1;
	private static UserEntity user2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Server.getInstance().login("http://localhost:7474/db/data/");	
		user1 = new UserEntity("test", "techsupport@lemonparty.com");
		user1.setNodeId(UserDAOImpl.getInstance().insertUser(user1));
		Server.getInstance().setLoggedUser("techsupport@lemonparty.com");
		user2 = new UserEntity("test2", "test2@lemonparty.com");
		user2.setNodeId(UserDAOImpl.getInstance().insertUser(user2));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		UserDAOImpl.getInstance().deleteUser(user1);
		UserDAOImpl.getInstance().deleteUser(user2);
	}

	@Test
	public void testGetAllUsers() {
		List<UserEntity> users = UserDAOImpl.getInstance().getAllUsers();
		assertTrue(users.size() == 2);
		assertTrue(users.contains(user1));
		assertTrue(users.contains(user2));
	}
	
	@Test
	public void testGetUsersWithRegex(){
		List<UserEntity> users = UserDAOImpl.getInstance().getUsersWithRegex("tes");
		assertTrue(users.size() == 2);
		assertTrue(users.contains(user1));
		assertTrue(users.contains(user2));
	}

}
