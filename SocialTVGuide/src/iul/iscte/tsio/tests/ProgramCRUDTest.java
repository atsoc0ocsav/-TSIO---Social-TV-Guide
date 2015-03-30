package iul.iscte.tsio.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import iul.iscte.tsio.controller.ProgramsController;
import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;
import iul.iscte.tsio.server.Server;

import java.util.List;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProgramCRUDTest {
	static ProgramEntity movie;
	static ProgramEntity program;
	static UserEntity user;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Server.getInstance().login("http://localhost:7474/db/data/");	
<<<<<<< HEAD
		Server.getInstance().logUser("techsupport@lemonparty.com");
=======
		user = new UserEntity("test", "techsupport@lemonparty.com");
		user.setNodeId(UserDAOImpl.getInstance().insertUser(user));
		Server.getInstance().setLoggedUser("techsupport@lemonparty.com");
>>>>>>> refs/remotes/origin/master
		movie = new ProgramEntity("Title1", "Movie", 100, "Description1");
		program = new ProgramEntity("Title2", "TVShow", 30, "Description2", 1 , 1);
	}

	@Test
	public void testProgramCRUD() {
		//Create
		movie.setNodeId(ProgramsController.getInstance().insertProgram(movie));
		program.setNodeId(ProgramsController.getInstance().insertProgram(program));
		//Read
		ProgramEntity tempMovie = ProgramsController.getInstance().getProgramByTitle(movie.getTitle());
		assertEquals(tempMovie, movie);
		ProgramEntity tempProgram = ProgramsController.getInstance().getProgramByTitle(program.getTitle());
		assertEquals(tempProgram, program);
		List<ProgramEntity> tempProgramList = ProgramsController.getInstance().getProgramsWithRegex("Title");
		assertTrue(tempProgramList.contains(movie));
		assertTrue(tempProgramList.contains(program));
		//Update
		movie.setTitle("Title3");
		assertTrue(ProgramsController.getInstance().updateProgram(movie));
		program.setTitle("Title4");
		assertTrue(ProgramsController.getInstance().updateProgram(program));
		//Delete
		assertTrue(ProgramsController.getInstance().deleteProgram(movie));
		assertTrue(ProgramsController.getInstance().deleteProgram(program));
	}
	
	@After
	public void tearDownAfterClass() throws Exception {
		UserDAOImpl.getInstance().deleteUser(user);
	}
}
