package iul.iscte.tsio.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import iul.iscte.tsio.controller.ProgramsController;
import iul.iscte.tsio.controller.UsersController;
import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;
import iul.iscte.tsio.server.Server;

import org.junit.BeforeClass;
import org.junit.Test;

public class ProgramCRUDTest {
	static ProgramEntity movie;
	static ProgramEntity program;
	static UserEntity user;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Server.getInstance().login("http://localhost:7474/db/data/");	
		Server.getInstance().setLoggedUser("techsupport@lemonparty.com");
		movie = new ProgramEntity("Title1", "Movie", 100, "Description1");
		program = new ProgramEntity("Title2", "TVShow", 30, "Description2", 1 , 1);
		user = Server.getInstance().getLoggedUser();
	}

	@Test
	public void testProgramCRUD() {
		//Create
		movie.setNodeId(ProgramsController.getInstance().insertProgram(movie));
		program.setNodeId(ProgramsController.getInstance().insertProgram(program));
		//Read
		ProgramEntity tempMovie = ProgramsController.getInstance().getProgramByTitle(movie.getTitle());
		movie.setNodeId(tempMovie.getNodeId());
		assertEquals(tempMovie, movie);
		ProgramEntity tempProgram = ProgramsController.getInstance().getProgramByTitle(program.getTitle());
		program.setNodeId(tempProgram.getNodeId());
		assertEquals(tempProgram, program);
		//Update
		movie.setTitle("Title3");
		assertTrue(ProgramsController.getInstance().updateProgram(movie));
		program.setTitle("Title4");
		assertTrue(ProgramsController.getInstance().updateProgram(program));
		//Delete
		assertTrue(ProgramsController.getInstance().deleteProgram(movie));
		assertTrue(ProgramsController.getInstance().deleteProgram(program));
	}
}
