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
		assertTrue(Server.getInstance().login("http://localhost:7474/db/data/"));
		assertTrue(Server.getInstance().isConnectedToServer());
		UserEntity testUser = new UserEntity("test", "techsupport@lemonparty.com");
		testUser.setNodeId(UserDAOImpl.getInstance().insertUser(testUser));
<<<<<<< HEAD
		assertTrue(Server.getInstance().logUser("techsupport@lemonparty.com"));
=======
		assertTrue(Server.getInstance().setLoggedUser("techsupport@lemonparty.com"));
		assertTrue(Server.getInstance().isUserLogged());
		Server.getInstance().unLogUser();
		assertFalse(Server.getInstance().isUserLogged());
>>>>>>> refs/remotes/origin/master
		UserDAOImpl.getInstance().deleteUser(testUser);
	}
}
