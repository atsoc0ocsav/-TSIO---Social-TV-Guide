package iul.iscte.tsio.tests;

import static org.junit.Assert.*;

import java.util.List;

import iul.iscte.tsio.controller.ProgramsController;
import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;
import iul.iscte.tsio.server.Server;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserCRUDTest {
	
	public static UserEntity user;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Server.getInstance().login("http://localhost:7474/db/data/");	
		user = new UserEntity("test", "techsupport@lemonparty.com");
		user.setNodeId(UserDAOImpl.getInstance().insertUser(user));
		Server.getInstance().setLoggedUser("techsupport@lemonparty.com");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		UserDAOImpl.getInstance().deleteUser(user);
	}

	
	@Test
	public void test() {
		UserEntity userTest = new UserEntity("test2", "test@test.pt");
		//Create
		userTest.setNodeId(UserDAOImpl.getInstance().insertUser(userTest));
		//Read
		UserEntity tempUser = UserDAOImpl.getInstance().getUserByEmail(userTest.getEmail());
		assertEquals(tempUser, userTest);
		tempUser = UserDAOImpl.getInstance().getUserByName(userTest.getUsername());
		assertEquals(tempUser, userTest);
		List<UserEntity>tempUserList = UserDAOImpl.getInstance().getUsersWithRegex("test");
		System.out.println(tempUserList.size());
		assertTrue(tempUserList.size() == 2);
		assertTrue(tempUserList.contains(user));
		assertTrue(tempUserList.contains(userTest));
		//Update
		userTest.setUsername("test3");
		assertTrue(UserDAOImpl.getInstance().updateUser(userTest));
		//Delete
		assertTrue(UserDAOImpl.getInstance().deleteUser(userTest));
	}

}
