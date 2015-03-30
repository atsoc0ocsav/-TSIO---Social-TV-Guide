package iul.iscte.tsio.tests;

import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;
import iul.iscte.tsio.server.Server;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserRecommendationTest {

	private static UserEntity user1;
	private static UserEntity user2;
	private static UserEntity user3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Server.getInstance().connect("http://localhost:7474/db/data/");
		user1 = new UserEntity("test", "techsupport@lemonparty.com");
		user1.setNodeId(UserDAOImpl.getInstance().insertUser(user1));
		Server.getInstance().logUser("techsupport@lemonparty.com");
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
	public void testUserRecommendation() {
		
	}

}
