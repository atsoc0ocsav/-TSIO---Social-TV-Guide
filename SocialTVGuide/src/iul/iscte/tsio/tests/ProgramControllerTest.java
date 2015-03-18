package iul.iscte.tsio.tests;

import static org.junit.Assert.*;
import iul.iscte.tsio.controller.ProgramsController;
import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.server.Server;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProgramControllerTest {
	static ProgramEntity movie;
	static ProgramEntity program;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Server.getInstance().login("http://localhost:7474/browser/");
		movie = new ProgramEntity("Title1", "Movie", 100, "Description1");
		program = new ProgramEntity("Title2", "TVShow", 30, "Description2", 1 , 1);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsertProgram() {
		assertTrue(ProgramsController.getInstance().insertProgram(movie));
		assertTrue(ProgramsController.getInstance().insertProgram(program));
	}
	
	@Test
	public void testGetProgramByTitle(){
		ProgramEntity tempMovie = ProgramsController.getInstance().getProgramByTitle(movie.getTitle());
		assertEquals(tempMovie, movie);
		ProgramEntity tempProgram = ProgramsController.getInstance().getProgramByTitle(program.getTitle());
		assertEquals(tempProgram, program);
	}
	
	@Test
	public void testUpdateProgram(){
		movie.setTitle("Title3");
		assertTrue(ProgramsController.getInstance().updateProgram(movie));
		program.setTitle("Title4");
		assertTrue(ProgramsController.getInstance().updateProgram(program));
	}
	
	@Test
	public void testDeleteProgram(){
		assertTrue(ProgramsController.getInstance().deleteProgram(movie));
		assertTrue(ProgramsController.getInstance().deleteProgram(program));
	}
}
