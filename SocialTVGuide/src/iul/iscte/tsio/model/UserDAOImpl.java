package iul.iscte.tsio.model;

import iul.iscte.tsio.interfaces.UserDAO;
import iul.iscte.tsio.server.Server;

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
	public List<UserEntity> getUsersByParameters(String nome, String escola,
			boolean aSeguir, String email) {
		return null;
	}

	@Override
	public UserEntity getUserByEmail(String email) {
			String query = "Match (n) where n.email=" + email +" return n;";
			Iterable<Node> user = cypherQueryEngine.query(query, null).to(Node.class);
			
			if(!user.iterator().hasNext()){
				Node aux = user.iterator().next();
				return new UserEntity(aux.getProperty("username").toString(), aux.getProperty("email").toString());
			}
				
			return null;
	}

	@Override
	public boolean updateUser(UserEntity userToUpdate, String oldEmail) {
		return false;
	}

	@Override
	public boolean insertUser(UserEntity userToInsert) {
		String query = "Create (u:User {username: " + userToInsert.getUsername() + ", email: " + userToInsert.getEmail() + "}) Return u;";
		Iterable<Node> user = cypherQueryEngine.query(query, null).to(Node.class);
		if(user.iterator().hasNext()){
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUser(UserEntity userToDelete) {
		return false;
	}

	@Override
	public UserEntity getUser(String name) {

		return null;
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return null;
	}

	@Override
	public boolean createFollow(String follower, String followed) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getSchools() {
		// TODO Auto-generated method stub
		return null;
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
