package iul.iscte.tsio.model;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.rest.graphdb.RestGraphDatabase;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;

import iul.iscte.tsio.interfaces.ProgramDAO;
import iul.iscte.tsio.server.Server;

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
		String query = "Match (n:Program) WHERE n.title=\"" + title + "\" return n;";
		Iterable<Node> user = cypherQueryEngine.query(query, null).to(
				Node.class);
		ProgramEntity aux = null;
		if (user.iterator().hasNext()) {
			Node auxNode = user.iterator().next();
			if (auxNode.getProperty("type").toString().compareTo("Movie") == 0) {
				aux = new ProgramEntity(auxNode.getId(), auxNode
						.getProperty("title").toString(), auxNode.getProperty(
						"type").toString(), Integer.parseInt(auxNode
						.getProperty("runtime").toString()), auxNode
						.getProperty("description").toString());
			} else {
				aux = new ProgramEntity(auxNode.getId(), auxNode
						.getProperty("title").toString(), auxNode.getProperty(
						"type").toString(), Integer.parseInt(auxNode
						.getProperty("runtime").toString()), auxNode
						.getProperty("description").toString(), Integer
						.parseInt(auxNode.getProperty("season").toString()),
						Integer.parseInt(auxNode.getProperty("episodeNumber")
								.toString()));
			}
		}
		return aux;
	}

	@Override
	public boolean insertUser(ProgramEntity programToInsert) {
		return false;
	}

	@Override
	public boolean deleteProgram(ProgramEntity programToDelete) {
		return false;
	}

	@Override
	public List<ProgramEntity> getAllPrograms() {
		String query = "Match (n:Program) return n;";
		Iterable<Node> user = cypherQueryEngine.query(query, null).to(
				Node.class);
		List<ProgramEntity> auxList = new ArrayList<ProgramEntity>();
		while (!user.iterator().hasNext()) {
			Node auxNode = user.iterator().next();
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
	public boolean updateProgram(ProgramEntity userToUpdate) {
		// TODO Auto-generated method stub
		return false;
	}

}
