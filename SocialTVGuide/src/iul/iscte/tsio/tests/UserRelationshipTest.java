package iul.iscte.tsio.tests;

import static org.junit.Assert.*;

import java.util.List;

import iul.iscte.tsio.controller.ProgramsController;
import iul.iscte.tsio.model.ProgramDAOImpl;
import iul.iscte.tsio.model.ProgramEntity;
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
	private static ProgramEntity movie;
	private static ProgramEntity program;

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
		movie = new ProgramEntity("Title1", "Movie", 100, "Description1");
		program = new ProgramEntity("Title2", "TVShow", 30, "Description2", 1 , 1);
		movie.setNodeId(ProgramsController.getInstance().insertProgram(movie));
		program.setNodeId(ProgramsController.getInstance().insertProgram(program));
		ProgramDAOImpl.getInstance().createWatchedRelationship(user1, program);
		ProgramDAOImpl.getInstance().createLikedRelationship(user1, program);
		ProgramDAOImpl.getInstance().createWatchedRelationship(user1, movie);
		ProgramDAOImpl.getInstance().createLikedRelationship(user1, movie);
		ProgramDAOImpl.getInstance().createWatchedRelationship(user2, movie);
		UserDAOImpl.getInstance().createFriendshipRelationship(user1, user2);
		UserDAOImpl.getInstance().createFriendshipRelationship(user2, user3);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		UserDAOImpl.getInstance().deleteUser(user1);
		UserDAOImpl.getInstance().deleteUser(user2);
		UserDAOImpl.getInstance().deleteUser(user3);
		ProgramsController.getInstance().deleteProgram(movie);
		ProgramsController.getInstance().deleteProgram(program);
	}

	@Test
	public void testRelationshipCRUD() {
		List<ProgramEntity> testRecommendations = ProgramDAOImpl.getInstance().getAllRecommendProgramsForUser(user2);
		assertTrue(testRecommendations.size() == 1);
		assertTrue(testRecommendations.contains(program));
		assertFalse(testRecommendations.contains(movie));
		testRecommendations = ProgramDAOImpl.getInstance().getAllRecommendProgramsForUser(user3);
		assertTrue(testRecommendations.size() == 0);
	}
}
