package iul.iscte.tsio.tests;

import static org.junit.Assert.*;
import iul.iscte.tsio.controller.ProgramsController;
import iul.iscte.tsio.model.ProgramDAOImpl;
import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;
import iul.iscte.tsio.server.Server;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProgramWatchedLikedRelationshipTest {
	static ProgramEntity movie;
	static ProgramEntity program;
	static UserEntity user;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Server.getInstance().connect("http://localhost:7474/db/data/");	
		user = new UserEntity("test", "techsupport@lemonparty.com");
		user.setNodeId(UserDAOImpl.getInstance().insertUser(user));
		Server.getInstance().logUser("techsupport@lemonparty.com");
		movie = new ProgramEntity("Title1", "Movie", 100, "Description1");
		program = new ProgramEntity("Title2", "TVShow", 30, "Description2", 1 , 1);
		movie.setNodeId(ProgramsController.getInstance().insertProgram(movie));
		program.setNodeId(ProgramsController.getInstance().insertProgram(program));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		UserDAOImpl.getInstance().deleteUser(user);
		ProgramsController.getInstance().deleteProgram(movie);
		ProgramsController.getInstance().deleteProgram(program);
	}

	@Test
	public void testWatchedLikedRelationship() {
		assertTrue(ProgramDAOImpl.getInstance().createWatchedRelationship(user, program));
		assertTrue(ProgramDAOImpl.getInstance().createLikedRelationship(user, program));
		assertTrue(ProgramDAOImpl.getInstance().hasUserWatchedProgram(user, program));
		assertTrue(ProgramDAOImpl.getInstance().hasUserLikedProgram(user, program));
		assertFalse(ProgramDAOImpl.getInstance().hasUserLikedProgram(user, movie));
		assertFalse(ProgramDAOImpl.getInstance().hasUserWatchedProgram(user, movie));
		assertTrue(ProgramDAOImpl.getInstance().createWatchedRelationship(user, movie));
		assertTrue(ProgramDAOImpl.getInstance().hasUserWatchedProgram(user, movie));
		List<ProgramEntity> temp = ProgramDAOImpl.getInstance().getAllWatchedProgramsByUser(user);
		assertTrue(temp.size() == 2);
		assertTrue(temp.contains(movie));
		assertTrue(temp.contains(program));
		assertTrue(ProgramDAOImpl.getInstance().deleteLikedRelationship(user, program));
		assertTrue(ProgramDAOImpl.getInstance().deleteWatchedRelationship(user, program));
		assertTrue(ProgramDAOImpl.getInstance().deleteWatchedRelationship(user, movie));
	}

}
