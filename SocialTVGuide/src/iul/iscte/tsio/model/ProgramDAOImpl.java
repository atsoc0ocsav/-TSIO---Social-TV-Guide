package iul.iscte.tsio.model;

import iul.iscte.tsio.interfaces.ProgramDAO;
import iul.iscte.tsio.server.Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.rest.graphdb.RestGraphDatabase;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;
import org.neo4j.rest.graphdb.util.ConvertedResult;

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
		Iterable<Node> program = Collections.<Node> emptyList();
		try {
			program = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}
		ProgramEntity aux = null;
		Iterator<Node> programIterator = program.iterator();
		if (programIterator.hasNext()) {
			Node auxNode = programIterator.next();
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
						Integer.parseInt(auxNode.getProperty("episode")
								.toString()));
			}
		}
		return aux;
	}

	@Override
	public long insertProgram(ProgramEntity programToInsert) {
		String query = "";
		if (programToInsert.getType().equals("Movie")) {
			query = "Create (p:Program {title: \"" + programToInsert.getTitle()
					+ "\", description: \"" + programToInsert.getDescription()
					+ "\", type:\"" + programToInsert.getType()
					+ "\", runtime:\"" + programToInsert.getRuntime()
					+ "\"}) Return id(p);";
		} else {
			query = "Create (p:Program {title: \"" + programToInsert.getTitle()
					+ "\", description: \"" + programToInsert.getDescription()
					+ "\", type:\"" + programToInsert.getType()
					+ "\", runtime:\"" + programToInsert.getRuntime()
					+ "\", season:\"" + programToInsert.getSeason()
					+ "\", episode:\"" + programToInsert.getEpisodeNumber()
					+ "\"}) Return id(p);";
		}
		ConvertedResult<Integer> count = null;
		try {
			count = cypherQueryEngine.query(query, null).to(Integer.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}
		long auxID = count.iterator().next();
		return auxID;
	}

	@Override
	public boolean deleteProgram(ProgramEntity programToDelete) {
		String query = "Match (p:Program) Where id(p)="
				+ programToDelete.getNodeId() + " OPTIONAL MATCH ()-[r]-(p)  Delete p;";
		try {
			cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateProgram(ProgramEntity programToUpdate) {
		String query = "";
		if (programToUpdate.getType() == "Movie") {
			query = "Match (p:Program) Where id(p)="
					+ programToUpdate.getNodeId() + " Set p.title = \""
					+ programToUpdate.getTitle() + "\", p.description = \""
					+ programToUpdate.getDescription() + "\", p.type = \""
					+ programToUpdate.getType() + "\", p.runtime = \""
					+ programToUpdate.getRuntime() + "\" Return p;";
		} else {
			query = "Match (p:Program) Where id(p)="
					+ programToUpdate.getNodeId() + " Set p.title = \""
					+ programToUpdate.getTitle() + "\", p.description = \""
					+ programToUpdate.getDescription() + "\", p.type = \""
					+ programToUpdate.getType() + "\", p.runtime = \""
					+ programToUpdate.getRuntime() + "\", p.season = \""
					+ programToUpdate.getSeason() + "\", p.episode = \""
					+ programToUpdate.getEpisodeNumber() + "\" Return p;";
		}

		Iterable<Node> program = Collections.<Node> emptyList();
		try {
			program = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
			return false;
		}
		Iterator<Node> programIterator = program.iterator();
		if (programIterator.hasNext()) {
			Node aux = programIterator.next();
			if (aux.getProperty("type").equals("Movie")) {
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
		Iterable<Node> programs = Collections.<Node> emptyList();
		try {
			programs = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}
		List<ProgramEntity> auxList = new ArrayList<ProgramEntity>();
		Iterator<Node> programIterator = programs.iterator();
		while (programIterator.hasNext()) {
			Node auxNode = programIterator.next();
			if (auxNode.getProperty("type").equals("Movie")) {
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
						Integer.parseInt(auxNode.getProperty("episode")
								.toString())));
			}
		}
		return auxList;
	}

	@Override
	public List<ProgramEntity> getAllLikedProgramsByUser(UserEntity user) {
		String query = "Match (u:User)-[:Liked]->(p:Program) WHERE id(u)="
				+ user.getNodeId() + " return p;";
		Iterable<Node> programs = Collections.<Node> emptyList();
		try {
			programs = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}
		ArrayList<ProgramEntity> aux = new ArrayList<ProgramEntity>();
		Iterator<Node> programIterator = programs.iterator();
		if (programIterator.hasNext()) {
			Node auxNode = programIterator.next();
			if (auxNode.getProperty("type").equals("Movie")) {
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
						.parseInt(auxNode.getProperty("episode").toString())));
			}
		}
		return aux;
	}

	@Override
	public List<ProgramEntity> getAllLikedProgramsByFriends(UserEntity user) {
		String query = "Match (u1:User)<-[:Friend]->(u2:User)-[:Liked]->(p:Program) WHERE id(u1)="
				+ user.getNodeId() + " return p;";
		Iterable<Node> programs = Collections.<Node> emptyList();
		try {
			programs = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}
		ArrayList<ProgramEntity> aux = new ArrayList<ProgramEntity>();
		Iterator<Node> it = programs.iterator();
		Node auxNode = null;
		while (it.hasNext()) {
			auxNode = it.next();
			if (auxNode.getProperty("type").equals("Movie")) {
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
						.parseInt(auxNode.getProperty("episode").toString())));
			}
		}
		return aux;
	}

	@Override
	public boolean createWatchedRelationship(UserEntity user,
			ProgramEntity program) {
		String query = "MATCH (n:User), (m:Program) WHERE id(n)="
				+ user.getNodeId() + " AND id(m)=" + program.getNodeId()
				+ " MERGE (n)-[r:Watched]->(m) Return r";
		Iterable<Node> relationship = Collections.<Node> emptyList();
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
		// Use Optional Match for situations where he liked the show
		String query = "MATCH (n:User)-[r:Watched]->(m:Program) WHERE id(n)="
				+ user.getNodeId() + " AND id(m)=" + program.getNodeId()
				+ " Delete r";
		try {
			cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
			return false;
		}
			return true;
	}

	@Override
	public boolean hasUserWatchedProgram(UserEntity user, ProgramEntity program) {
		String query = "Match (u:User)-[:Watched]->(p:Program) Where id(u)="
				+ user.getNodeId() + " And id(p)=" + program.getNodeId()
				+ " return count(p);";

		ConvertedResult<Integer> count = null;
		try {
			count = cypherQueryEngine.query(query, null).to(Integer.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}
		int auxCount = count.iterator().next();
		if (auxCount != 0)
			return true;
		return false;
	}

	@Override
	public List<ProgramEntity> getAllWatchedProgramsByUser(UserEntity user) {
		String query = "Match (u:User)-[:Watched]->(p:Program) WHERE id(u)="
				+ user.getNodeId() + " return p;";
		Iterable<Node> programs = Collections.<Node> emptyList();
		try {
			programs = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}
		ArrayList<ProgramEntity> aux = new ArrayList<ProgramEntity>();
		Iterator<Node> programIterator = programs.iterator();
		while (programIterator.hasNext()) {
			Node auxNode = programIterator.next();
			if (auxNode.getProperty("type").equals("Movie")) {
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
						.parseInt(auxNode.getProperty("episode").toString())));
			}
		}
		return aux;
	}

	@Override
	public List<ProgramEntity> getAllWatchedProgramsByFriends(UserEntity user) {
		String query = "Match (u1:User)<-[:Friend]->(u2:User)-[:Watched]->(p:Program) WHERE id(u1)="
				+ user.getNodeId() + " return p;";
		Iterable<Node> programs = Collections.<Node> emptyList();
		try {
			programs = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}
		ArrayList<ProgramEntity> aux = new ArrayList<ProgramEntity>();
		Iterator<Node> programIterator = programs.iterator();
		while (programIterator.hasNext()) {
			Node auxNode = programIterator.next();
			if (auxNode.getProperty("type").equals("Movie")) {
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
						.parseInt(auxNode.getProperty("episode").toString())));
			}
		}
		return aux;
	}

	@Override
	public boolean createLikedRelationship(UserEntity user,
			ProgramEntity program) {
		String query = "MATCH (n:User), (m:Program) WHERE id(n)="
				+ user.getNodeId() + " AND id(m)=" + program.getNodeId()
				+ " MERGE (n)-[r:Liked]->(m) Return r";
		Iterable<Node> relationship = Collections.<Node> emptyList();
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
		String query = "START n=node(" + user.getNodeId()
				+ ") MATCH (n:User)-[r:Liked]->(m:Program) WHERE id(m)="
				+ program.getNodeId() + " Delete r";
		try {
			cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
			return false;
		}
			return true;
	}

	@Override
	public boolean hasUserLikedProgram(UserEntity user, ProgramEntity program) {
		String query = "Match (u:User)-[:Liked]->(p:Program) Where id(u)="
				+ user.getNodeId() + " And id(p)=" + program.getNodeId()
				+ " return count(p) as count;";
		ConvertedResult<Integer> count = null;
		try {
			count = cypherQueryEngine.query(query, null).to(Integer.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}

		int auxCount = count.iterator().next();
		if (auxCount != 0)
			return true;
		return false;
	}

	@Override
	public List<ProgramEntity> getProgramsWithRegex(String title) {
		String query = "Match (p:Program) Where p.title=~'(?i)" + title
				+ ".*' return p;";
		Iterable<Node> programs = Collections.<Node> emptyList();
		try {
			programs = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}
		List<ProgramEntity> auxList = new ArrayList<ProgramEntity>();
		Iterator<Node> programIterator = programs.iterator();
		while (programIterator.hasNext()) {
			Node auxNode = programIterator.next();
			if (auxNode.getProperty("type").equals("Movie")) {
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
						Integer.parseInt(auxNode.getProperty("episode")
								.toString())));
			}
		}
		return auxList;
	}

	@Override
	public List<ProgramEntity> getAllRecommendProgramsForUser(UserEntity user) {
		String query = "Match (u1:User)<-[:Friend]->(u2:User)-[:Liked]->(p:Program) WHERE id(u1)="
				+ user.getNodeId()
				+ " AND NOT ((u1)-[:Watched]->(p)) return DISTINCT p;";
		Iterable<Node> programs = Collections.<Node> emptyList();
		try {
			programs = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace();
		}
		ArrayList<ProgramEntity> aux = new ArrayList<ProgramEntity>();
		Iterator<Node> it = programs.iterator();
		Node auxNode = null;
		while (it.hasNext()) {
			auxNode = it.next();
			if (auxNode.getProperty("type").equals("Movie")) {
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
						.parseInt(auxNode.getProperty("episode").toString())));
			}
		}
		return aux;
	}
}
