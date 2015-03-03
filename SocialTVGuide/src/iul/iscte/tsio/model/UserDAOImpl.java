package iul.iscte.tsio.model;

import iul.iscte.tsio.interfaces.UserDAO;
import iul.iscte.tsio.server.Server;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.rest.graphdb.RestAPIFacade;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;

public class UserDAOImpl implements UserDAO {

	private static UserDAOImpl instance;
	private final RestAPIFacade graphDatabase;
	private final RestCypherQueryEngine cypherQueryEngine;

	private UserDAOImpl() {
		this.graphDatabase = new RestAPIFacade(Server.getInstance()
				.getServer_ROOT_URI());
		this.cypherQueryEngine = new RestCypherQueryEngine(
				graphDatabase);
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		// Ensure that email is unique
		String query = "Match (n:User) Where n.email=\"" + email
				+ "\" return n;";
		System.out.println(query);
		Iterable<Node> user = cypherQueryEngine.query(query, null).to(
				Node.class);
		if (user.iterator().hasNext()) {
			Node aux = user.iterator().next();
			return new UserEntity(aux.getId(), aux.getProperty("username")
					.toString(), aux.getProperty("email").toString());
		}
		return null;
	}

	// @Override
	// public boolean updateUser(UserEntity userToUpdate, String oldEmail) {
	// return false;
	// }

	@Override
	public boolean insertUser(UserEntity userToInsert) {
		String query = "Create (u:User {username: \""
				+ userToInsert.getUsername() + "\", email: \""
				+ userToInsert.getEmail() + "\"}) Return u;";
		Iterable<Node> user = cypherQueryEngine.query(query, null).to(
				Node.class);
		if (user.iterator().hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUser(UserEntity userToDelete) {
		String query = "Match (u:User) Where id(u)=" + userToDelete.getNodeId()
				+ " Delete u Return u;";
		Iterable<Node> user = cypherQueryEngine.query(query, null).to(
				Node.class);
		if (!user.iterator().hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public UserEntity getUserByName(String username) {
		String query = "Match (n:User) where n.username=\"" + username
				+ "\" return n;";
		Iterable<Node> user = cypherQueryEngine.query(query, null).to(
				Node.class);
		if (!user.iterator().hasNext()) {
			Node aux = user.iterator().next();
			return new UserEntity(aux.getId(), aux.getProperty("username")
					.toString(), aux.getProperty("email").toString());
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
			auxList.add(new UserEntity(auxNode.getId(), auxNode.getProperty(
					"username").toString(), auxNode.getProperty("email")
					.toString()));
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

	@Override
	public boolean updateUser(UserEntity userToUpdate) {
		// TODO Get All fields from UserEntity; Get NodeID; Change Node With All
		// Fields
		return false;
	}

	@Override
	public boolean createFriendshipRelationship(UserEntity user,
			UserEntity friend) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteFriendshipRelationship(UserEntity user,
			UserEntity friend) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUserFriend(UserEntity user, UserEntity friend) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserEntity> getAllFriends(UserEntity user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProgramEntity> getAllFriendsRecommendations(UserEntity user) {
		// TODO Auto-generated method stub
		return null;
	}
}
