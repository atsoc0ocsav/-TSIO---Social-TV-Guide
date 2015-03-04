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
		this.cypherQueryEngine = new RestCypherQueryEngine(graphDatabase);
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
	public UserEntity getUserByEmail(String email) {
		// Ensure that email is unique
		String query = "Match (n:User) Where n.email=\"" + email
				+ "\" return n;";
		System.out.println(query);
		Iterable<Node> user = null;
		try {
			user = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace(); 	
		}
		if (user.iterator().hasNext()) {
			Node aux = user.iterator().next();
			return new UserEntity(aux.getId(), aux.getProperty("username")
					.toString(), aux.getProperty("email").toString());
		}
		return null;
	}

	@Override
	public boolean insertUser(UserEntity userToInsert) {
		String query = "Create (u:User {username: \""
				+ userToInsert.getUsername() + "\", email: \""
				+ userToInsert.getEmail() + "\"}) Return u;";
		Iterable<Node> user = null;
		try {
			user = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace(); 	
		}
		if (user.iterator().hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUser(UserEntity userToDelete) {
		String query = "Match (u:User) Where id(u)=" + userToDelete.getNodeId()
				+ " Delete u Return u;";
		Iterable<Node> user = null;
		try {
			user = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace(); 	
		}
		if (!user.iterator().hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUser(UserEntity userToUpdate) {
		String query = "Match (u:User) Where id(u)=" + userToUpdate.getNodeId() + "u.username: \""
				+ userToUpdate.getUsername() + "\", u.email: \""
				+ userToUpdate.getEmail() + "\" Return u;";
		Iterable<Node> user = null;
		try {
			user = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace(); 	
		}
		if (user.iterator().hasNext()) {
			Node aux = user.iterator().next();
			if (aux.getProperty("username").toString().compareTo(userToUpdate.getUsername())==0 && aux.getProperty("email").toString().compareTo(userToUpdate.getUsername())==0)
				return true;
		}
		return false;
	}

	@Override
	public UserEntity getUserByName(String username) {
		String query = "Match (n:User) where n.username=\"" + username
				+ "\" return n;";
		Iterable<Node> user = null;
		try {
			user = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace(); 	
		}
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
		Iterable<Node> users = null;
		try {
			users = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace(); 	
		}
		List<UserEntity> auxList = new ArrayList<UserEntity>();
		while (!users.iterator().hasNext()) {
			Node auxNode = users.iterator().next();
			auxList.add(new UserEntity(auxNode.getId(), auxNode.getProperty(
					"username").toString(), auxNode.getProperty("email")
					.toString()));
		}
		return auxList;
	}

	@Override
	public boolean createFriendshipRelationship(UserEntity user,
			UserEntity friend) {
		String query = "MATCH (n:User), (m:User) WHERE id(n)=" + user.getNodeId() + "AND id(m) = " + friend.getNodeId() +" MERGE (n)-[r:Friend]->(m) Return r";
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
	public boolean deleteFriendshipRelationship(UserEntity user,
			UserEntity friend) {
		String query = "MATCH (n:User)-[r:Friend]->(m:User) WHERE id(n)=" + user.getNodeId() + "AND id(m)=" + friend.getNodeId() +" Delete r Return r";
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
	public boolean isUserFriend(UserEntity user, UserEntity friend) {
		String query = "Match (u1:User)<-[f:Friend]->(u2:User) Where id(u1)="
				+ user.getNodeId() + " And id(u2)=" + friend.getNodeId()
				+ " return count(f);";
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
	public List<UserEntity> getAllFriends(UserEntity user) {
		String query = "Match (u1:User)<-[:Friend]->(u2:User) Where id(u1)="
				+ user.getNodeId() + " return u2;";
		Iterable<Node> friends = null;
		try {
			friends = cypherQueryEngine.query(query, null).to(Node.class);
		} catch (Exception e) {
			System.err.print("Something went wrong, please call techSupport");
			e.printStackTrace(); 	
		}
		List<UserEntity> auxList = new ArrayList<UserEntity>();
		while (!friends.iterator().hasNext()) {
			Node auxNode = friends.iterator().next();
			auxList.add(new UserEntity(auxNode.getId(), auxNode.getProperty(
					"username").toString(), auxNode.getProperty("email")
					.toString()));
		}
		return auxList;
	}
}
