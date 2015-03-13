package iul.iscte.tsio.model;

import iul.iscte.tsio.interfaces.ProgramDAO;
import iul.iscte.tsio.server.Server;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.rest.graphdb.RestGraphDatabase;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;

public class ProgramDAOImpl implements ProgramDAO {

	private static ProgramDAOImpl instance;
	private RestGraphDatabase graphDatabase;
	private RestCypherQueryEngine cypherQueryEngine;

	private ProgramDAOImpl() {
		this.graphDatabase = new RestGraphDatabase(Server.getInstance()
				.getServer_ROOT_URI());
		this.cypherQueryEngine = new RestCypherQueryEngine(
				graphDatabase.getRestAPI());
	}

	public static ProgramDAOImpl getInstance() {
		if (instance == null) {
			synchronized (UserDAOImpl.class) {
				instance = new ProgramDAOImpl();
			}
		}
		return instance;
	}

	@Override
	public ProgramEntity getProgramByTitle(String title) {
		String query = "Match (n:Program) WHERE n.title=\"" + title
				+ "\" return n;";
		Iterable<Node> program = null;
		try {
			program = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}
		ProgramEntity aux = null;
		if (program.iterator().hasNext()) {
			Node auxNode = program.iterator().next();
			if (auxNode.getProperty("type").toString().compareTo("Movie") == 0) {
				aux = new ProgramEntity(auxNode.getId(), auxNode.getProperty(
						"title").toString(), auxNode.getProperty("type")
						.toString(), Integer.parseInt(auxNode.getProperty(
						"runtime").toString()), auxNode.getProperty(
						"description").toString());
			} else {
				aux = new ProgramEntity(auxNode.getId(), auxNode.getProperty(
						"title").toString(), auxNode.getProperty("type")
						.toString(), Integer.parseInt(auxNode.getProperty(
						"runtime").toString()), auxNode.getProperty(
						"description").toString(), Integer.parseInt(auxNode
						.getProperty("season").toString()),
						Integer.parseInt(auxNode.getProperty("episodeNumber")
								.toString()));
			}
		}
		return aux;
	}

	@Override
	public boolean insertProgram(ProgramEntity programToInsert) {
		String query = "";
		if (programToInsert.getType() == "Movie") {
			query = "Create (p:Program {title: \"" + programToInsert.getTitle()
					+ "\", description: \"" + programToInsert.getDescription()
					+ "\", type:\"" + programToInsert.getType()
					+ "\", runtime:\"" + programToInsert.getRuntime()
					+ "\"}) Return p;";
		} else {
			query = "Create (p:Program {title: \"" + programToInsert.getTitle()
					+ "\", description: \"" + programToInsert.getDescription()
					+ "\", type:\"" + programToInsert.getType()
					+ "\", runtime:\"" + programToInsert.getRuntime()
					+ "\", season\"" + programToInsert.getSeason()
					+ "\", episode:\"" + programToInsert.getEpisodeNumber()
					+ "\"}) Return p;";
		}
		Iterable<Node> program = null;
		try {
			program = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}
		if (program.iterator().hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteProgram(ProgramEntity programToDelete) {
		String query = "Match (p:Program) Where id(p)="
				+ programToDelete.getNodeId() + " Delete p Return p;";
		Iterable<Node> program = null;
		try {
			program = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}
		if (!program.iterator().hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateProgram(ProgramEntity programToUpdate) {
		// Review boolean verification
		String query = "";
		if (programToUpdate.getType() == "Movie") {
			query = "Match p:Program Where id(p)="
					+ programToUpdate.getNodeId() + " Set  p.title: \""
					+ programToUpdate.getTitle() + "\", p.description: \""
					+ programToUpdate.getDescription() + "\", p.type:\""
					+ programToUpdate.getType() + "\", p.runtime:\""
					+ programToUpdate.getRuntime() + "\" Return p;";
		} else {
			query = "Match p:Program Where id(p)="
					+ programToUpdate.getNodeId() + "p.title: \""
					+ programToUpdate.getTitle() + "\", p.description: \""
					+ programToUpdate.getDescription() + "\", p.type:\""
					+ programToUpdate.getType() + "\", p.runtime:\""
					+ programToUpdate.getRuntime() + "\", p.season\""
					+ programToUpdate.getSeason() + "\", p.episode:\""
					+ programToUpdate.getEpisodeNumber() + "\" Return p;";
		}

		Iterable<Node> program = null;
		try {
			program = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
			return false;
		}
		if (program.iterator().hasNext()) {
			Node aux = program.iterator().next();
			if (aux.getProperty("type").toString().compareTo("Movie") == 0) {
				if (aux.getProperty("title").toString()
						.compareTo(programToUpdate.getTitle()) == 0
						&& aux.getProperty("description").toString()
								.compareTo(programToUpdate.getDescription()) == 0
						&& Integer.valueOf(aux.getProperty("runtime")
								.toString()) == programToUpdate.getRuntime())
					return true;
			} else {
				if (aux.getProperty("title").toString()
						.compareTo(programToUpdate.getTitle()) == 0
						&& aux.getProperty("description").toString()
								.compareTo(programToUpdate.getDescription()) == 0
						&& Integer.valueOf(aux.getProperty("runtime")
								.toString()) == programToUpdate.getRuntime()
						&& Integer
								.valueOf(aux.getProperty("season").toString()) == programToUpdate
								.getSeason()
						&& Integer.valueOf(aux.getProperty("episode")
								.toString()) == programToUpdate
								.getEpisodeNumber())
					return true;
			}
		}
		return false;
	}

	@Override
	public List<ProgramEntity> getAllPrograms() {
		String query = "Match (n:Program) return n;";
		Iterable<Node> programs = null;
		try {
			programs = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}
		List<ProgramEntity> auxList = new ArrayList<ProgramEntity>();
		while (!programs.iterator().hasNext()) {
			Node auxNode = programs.iterator().next();
			if (auxNode.getProperty("type").toString().compareTo("Movie") == 0) {
				auxList.add(new ProgramEntity(auxNode.getId(), auxNode
						.getProperty("title").toString(), auxNode.getProperty(
						"type").toString(), Integer.parseInt(auxNode
						.getProperty("runtime").toString()), auxNode
						.getProperty("description").toString()));
			} else {
				auxList.add(new ProgramEntity(auxNode.getId(), auxNode
						.getProperty("title").toString(), auxNode.getProperty(
						"type").toString(), Integer.parseInt(auxNode
						.getProperty("runtime").toString()), auxNode
						.getProperty("description").toString(), Integer
						.parseInt(auxNode.getProperty("season").toString()),
						Integer.parseInt(auxNode.getProperty("episodeNumber")
								.toString())));
			}
		}
		return auxList;
	}

	@Override
	public List<ProgramEntity> getAllLikedProgramsByUser(UserEntity user) {
		String query = "Match (u:User)-[:Liked]->(p:Program) WHERE id(u)="
				+ user.getNodeId() + " return p;";
		Iterable<Node> programs = null;
		try {
			programs = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}
		ArrayList<ProgramEntity> aux = new ArrayList<ProgramEntity>();
		if (programs.iterator().hasNext()) {
			Node auxNode = programs.iterator().next();
			if (auxNode.getProperty("type").toString().compareTo("Movie") == 0) {
				aux.add(new ProgramEntity(auxNode.getId(), auxNode.getProperty(
						"title").toString(), auxNode.getProperty("type")
						.toString(), Integer.parseInt(auxNode.getProperty(
						"runtime").toString()), auxNode.getProperty(
						"description").toString()));
			} else {
				aux.add(new ProgramEntity(auxNode.getId(), auxNode.getProperty(
						"title").toString(), auxNode.getProperty("type")
						.toString(), Integer.parseInt(auxNode.getProperty(
						"runtime").toString()), auxNode.getProperty(
						"description").toString(), Integer.parseInt(auxNode
						.getProperty("season").toString()), Integer
						.parseInt(auxNode.getProperty("episodeNumber")
								.toString())));
			}
		}
		return aux;
	}

	@Override
	public List<ProgramEntity> getAllLikedProgramsByFriends(UserEntity user) {
		String query = "Match (u1:User)<-[:Friend]->(u2:User)-[:Liked]->(p:Program) WHERE id(u1)="
				+ user.getNodeId() + " return p;";
		Iterable<Node> programs = null;
		try {
			programs = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}
		ArrayList<ProgramEntity> aux = new ArrayList<ProgramEntity>();
		if (programs.iterator().hasNext()) {
			Node auxNode = programs.iterator().next();
			if (auxNode.getProperty("type").toString().compareTo("Movie") == 0) {
				aux.add(new ProgramEntity(auxNode.getId(), auxNode.getProperty(
						"title").toString(), auxNode.getProperty("type")
						.toString(), Integer.parseInt(auxNode.getProperty(
						"runtime").toString()), auxNode.getProperty(
						"description").toString()));
			} else {
				aux.add(new ProgramEntity(auxNode.getId(), auxNode.getProperty(
						"title").toString(), auxNode.getProperty("type")
						.toString(), Integer.parseInt(auxNode.getProperty(
						"runtime").toString()), auxNode.getProperty(
						"description").toString(), Integer.parseInt(auxNode
						.getProperty("season").toString()), Integer
						.parseInt(auxNode.getProperty("episodeNumber")
								.toString())));
			}
		}
		return aux;
	}

	@Override
	public boolean createWatchedRelationship(UserEntity user,
			ProgramEntity program) {
		String query = "MATCH (n:User), (m:Program) WHERE id(n)="
				+ user.getNodeId() + "AND id(m) = " + program.getNodeId()
				+ " MERGE (n)-[r:Watched]->(m) Return r";
		Iterable<Node> relationship = null;
		try {
			relationship = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}
		if (relationship.iterator().hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteWatchedRelationship(UserEntity user,
			ProgramEntity program) {
		String query = "MATCH (n:User)-[r:Watched]->(m:Program) WHERE id(n)="
				+ user.getNodeId() + "AND id(m) = " + program.getNodeId()
				+ "Delete r Return r";
		Iterable<Node> relationship = null;
		try {
			relationship = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}
		if (!relationship.iterator().hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasUserWatchedProgram(UserEntity user, ProgramEntity program) {
		String query = "Match (u:User)-[:Watched]->(p:Program) Where id(u)="
				+ user.getNodeId() + " And id(p)=" + program.getNodeId()
				+ " return count(p);";
		Iterable<Node> count = null;
		try {
			count = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}
		int auxCount = Integer.valueOf(count.iterator().next()
				.getProperty("count").toString());
		if (auxCount != 0)
			return true;
		return false;
	}

	@Override
	public List<ProgramEntity> getAllWatchedProgramsByUser(UserEntity user) {
		String query = "Match (u:User)-[:Watched]->(p:Program) WHERE id(u)="
				+ user.getNodeId() + " return p;";
		Iterable<Node> programs = null;
		try {
			programs = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace(); 	
		}
		ArrayList<ProgramEntity> aux = new ArrayList<ProgramEntity>();
		if (programs.iterator().hasNext()) {
			Node auxNode = programs.iterator().next();
			if (auxNode.getProperty("type").toString().compareTo("Movie") == 0) {
				aux.add(new ProgramEntity(auxNode.getId(), auxNode.getProperty(
						"title").toString(), auxNode.getProperty("type")
						.toString(), Integer.parseInt(auxNode.getProperty(
						"runtime").toString()), auxNode.getProperty(
						"description").toString()));
			} else {
				aux.add(new ProgramEntity(auxNode.getId(), auxNode.getProperty(
						"title").toString(), auxNode.getProperty("type")
						.toString(), Integer.parseInt(auxNode.getProperty(
						"runtime").toString()), auxNode.getProperty(
						"description").toString(), Integer.parseInt(auxNode
						.getProperty("season").toString()), Integer
						.parseInt(auxNode.getProperty("episodeNumber")
								.toString())));
			}
		}
		return aux;
	}

	@Override
	public List<ProgramEntity> getAllWatchedProgramsByFriends(UserEntity user) {
		String query = "Match (u1:User)<-[:Friend]->(u2:User)-[:Watched]->(p:Program) WHERE id(u1)="
				+ user.getNodeId() + " return p;";
		Iterable<Node> programs = null;
		try {
			programs = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace(); 	
		}
		ArrayList<ProgramEntity> aux = new ArrayList<ProgramEntity>();
		if (programs.iterator().hasNext()) {
			Node auxNode = programs.iterator().next();
			if (auxNode.getProperty("type").toString().compareTo("Movie") == 0) {
				aux.add(new ProgramEntity(auxNode.getId(), auxNode.getProperty(
						"title").toString(), auxNode.getProperty("type")
						.toString(), Integer.parseInt(auxNode.getProperty(
						"runtime").toString()), auxNode.getProperty(
						"description").toString()));
			} else {
				aux.add(new ProgramEntity(auxNode.getId(), auxNode.getProperty(
						"title").toString(), auxNode.getProperty("type")
						.toString(), Integer.parseInt(auxNode.getProperty(
						"runtime").toString()), auxNode.getProperty(
						"description").toString(), Integer.parseInt(auxNode
						.getProperty("season").toString()), Integer
						.parseInt(auxNode.getProperty("episodeNumber")
								.toString())));
			}
		}
		return aux;
	}

	@Override
	public boolean createLikedRelationship(UserEntity user,
			ProgramEntity program) {
		String query = "MATCH (n:User), (m:Program) WHERE id(n)="
				+ user.getNodeId() + "AND id(m) = " + program.getNodeId()
				+ " MERGE (n)-[r:Liked]->(m) Return r";
		Iterable<Node> relationship = null;
		try {
			relationship = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace(); 	
		}
		if (relationship.iterator().hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteLikedRelationship(UserEntity user,
			ProgramEntity program) {
		String query = "MATCH (n:User)-[r:Liked]->(m:Program) WHERE id(n)="
				+ user.getNodeId() + "AND id(m) = " + program.getNodeId()
				+ "Delete r Return r";
		Iterable<Node> relationship = null;
		try {
			relationship = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace(); 	
		}
		if (!relationship.iterator().hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasUserLikedProgram(UserEntity user, ProgramEntity program) {
		String query = "Match (u:User)-[:Liked]->(p:Program) Where id(u)="
				+ user.getNodeId() + " And id(p)=" + program.getNodeId()
				+ " return count(p);";
		Iterable<Node> count = null;
		try {
			count = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace(); 	
		}
		int auxCount = Integer.valueOf(count.iterator().next()
				.getProperty("count").toString());
		if (auxCount != 0)
			return true;
		return false;
	}

}
