package iul.iscte.tsio.tests;

import static org.junit.Assert.*;

import java.util.List;

import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;
import iul.iscte.tsio.server.Server;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserRelationshipTest {
	private static UserEntity user1;
	private static UserEntity user2;
	private static UserEntity user3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Server.getInstance().login("http://localhost:7474/db/data/");
		user1 = new UserEntity("test", "techsupport@lemonparty.com");
		user1.setNodeId(UserDAOImpl.getInstance().insertUser(user1));
		Server.getInstance().setLoggedUser("techsupport@lemonparty.com");
		user2 = new UserEntity("test2", "test2@lemonparty.com");
		user2.setNodeId(UserDAOImpl.getInstance().insertUser(user2));
		user3 = new UserEntity("test3", "test3@lemonparty.com");
		user3.setNodeId(UserDAOImpl.getInstance().insertUser(user3));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		UserDAOImpl.getInstance().deleteUser(user1);
		UserDAOImpl.getInstance().deleteUser(user2);
		UserDAOImpl.getInstance().deleteUser(user3);
	}

	@Test
	public void testRelationshipCRUD() {
		assertTrue(UserDAOImpl.getInstance().createFriendshipRelationship(user1, user2));
		assertTrue(UserDAOImpl.getInstance().isUserFriend(user1, user2));
		assertTrue(UserDAOImpl.getInstance().createFriendshipRelationship(user1, user3));
		List<UserEntity> friends = UserDAOImpl.getInstance().getAllFriends(user1);
		assertTrue(friends.size() == 2);
		assertTrue(friends.contains(user2));
		assertTrue(friends.contains(user3));
		assertTrue(UserDAOImpl.getInstance().deleteFriendshipRelationship(user1, user2));
		assertTrue(UserDAOImpl.getInstance().deleteFriendshipRelationship(user1, user3));
	}

}
