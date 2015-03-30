package iul.iscte.tsio.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;
import iul.iscte.tsio.server.Server;

import org.junit.Test;

public class ServerLoginTest {

	static UserEntity user;

	@Test
	public void testUserLogin() {
		assertTrue(Server.getInstance().connect("http://localhost:7474/db/data/"));
		assertTrue(Server.getInstance().isConnected());
		UserEntity testUser = new UserEntity("test", "techsupport@lemonparty.com");
		testUser.setNodeId(UserDAOImpl.getInstance().insertUser(testUser));

		assertTrue(Server.getInstance().logUser("techsupport@lemonparty.com"));
		assertTrue(Server.getInstance().isAuthenticated());
		Server.getInstance().unLogUser();
		assertFalse(Server.getInstance().isAuthenticated());
		UserDAOImpl.getInstance().deleteUser(testUser);
	}
}
