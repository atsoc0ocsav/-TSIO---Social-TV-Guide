package iul.iscte.tsio.tests;

import static org.junit.Assert.assertTrue;
import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;
import iul.iscte.tsio.server.Server;

import org.junit.Test;

public class ServerLoginTest {

	static UserEntity user;

	@Test
	public void testUserLogin() {
		assertTrue(Server.getInstance().login("http://localhost:7474/db/data/"));
		UserEntity testUser = new UserEntity("test", "techsupport@lemonparty.com");
		testUser.setNodeId(UserDAOImpl.getInstance().insertUser(testUser));
		assertTrue(Server.getInstance().setLoggedUser("techsupport@lemonparty.com"));
		UserDAOImpl.getInstance().deleteUser(testUser);
	}
}
