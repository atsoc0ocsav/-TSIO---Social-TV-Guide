package iul.iscte.tsio.model;

import iul.iscte.tsio.interfaces.UserDAO;
import iul.iscte.tsio.server.Server;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.rest.graphdb.RestGraphDatabase;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;

public class UserDAOImpl implements UserDAO {

	private static UserDAOImpl instance;
	private final RestGraphDatabase graphDatabase;
	private final RestCypherQueryEngine cypherQueryEngine;

	private UserDAOImpl() {
		this.graphDatabase = new RestGraphDatabase(Server.getInstance()
				.getServer_ROOT_URI());
		this.cypherQueryEngine = new RestCypherQueryEngine(
				graphDatabase.getRestAPI());
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		String query = "Match (n:User) where n.email=" + email + " return n;";
		Iterable<Node> user = cypherQueryEngine.query(query, null).to(
				Node.class);
		if (!user.iterator().hasNext()) {
			Node aux = user.iterator().next();
			return new UserEntity(aux.getProperty("username").toString(), aux
					.getProperty("email").toString());
		}
		return null;
	}

//	@Override
//	public boolean updateUser(UserEntity userToUpdate, String oldEmail) {
//		return false;
//	}

	@Override
	public boolean insertUser(UserEntity userToInsert) {
		String query = "Create (u:User {username: "
				+ userToInsert.getUsername() + ", email: "
				+ userToInsert.getEmail() + "}) Return u;";
		Iterable<Node> user = cypherQueryEngine.query(query, null).to(
				Node.class);
		if (user.iterator().hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUser(UserEntity userToDelete) {
		String query = "Match (u:User {username: "
				+ userToDelete.getUsername() + ", email: "
				+ userToDelete.getEmail() + "}) Delete u Return u;";
		Iterable<Node> user = cypherQueryEngine.query(query, null).to(
				Node.class);
		if (!user.iterator().hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public UserEntity getUserByName(String username) {
		String query = "Match (n:User) where n.username=" + username
				+ " return n;";
		Iterable<Node> user = cypherQueryEngine.query(query, null).to(
				Node.class);
		if (!user.iterator().hasNext()) {
			Node aux = user.iterator().next();
			return new UserEntity(aux.getProperty("username").toString(), aux
					.getProperty("email").toString());
		}
		return null;
	}

	@Override
	public List<UserEntity> getAllUsers() {
		String query = "Match (n:User) return n;";
		Iterable<Node> user = cypherQueryEngine.query(query, null).to(
				Node.class);
		List<UserEntity> auxList = new ArrayList<UserEntity>();
		while (!user.iterator().hasNext()) {
			Node auxNode = user.iterator().next();
			auxList.add(new UserEntity(auxNode.getProperty("username")
					.toString(), auxNode.getProperty("email").toString()));
		}

		return auxList;
	}

	public static UserDAOImpl getInstance() {
		if (instance == null) {
			synchronized (UserDAOImpl.class) {
				instance = new UserDAOImpl();
			}
		}
		return instance;
	}
}
