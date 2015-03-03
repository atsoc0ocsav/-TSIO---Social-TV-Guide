package iul.iscte.tsio.controller;

import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;

import java.net.URI;

import javax.ws.rs.core.MediaType;

import org.neo4j.graphdb.Node;
import org.neo4j.rest.graphdb.RestGraphDatabase;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

//Maybe create 2 controllers, one for movies and one for users
public class UsersController {
	private UserEntity loggedUser;
	private static UsersController instance = null;
//	private final String SERVER_ROOT_URI = "http://localhost:7474/db/data/";
//	private final RestGraphDatabase graphDatabase;
//	private final RestCypherQueryEngine cypherQueryEngine;
	
	private UsersController() {
//		this.graphDatabase = new RestGraphDatabase(SERVER_ROOT_URI);
//		this.cypherQueryEngine = new RestCypherQueryEngine(graphDatabase.getRestAPI());
	}
	
	public static UsersController getInstance() {
		if (instance == null) {
			synchronized (UsersController.class) {
				instance = new UsersController();
			}
		}
		return instance;
	}
	
	public boolean login(String email){
		UserEntity loggedUser = UserDAOImpl.getInstance().getUserByEmail(email);
		if(loggedUser != null){
			this.loggedUser = loggedUser;
			return true;
		}
		return false;
	}

	public void create(String name, String email) {
		UserDAOImpl.getInstance().insertUser(new UserEntity(name, email));
//		final String nodeEntryPointUri = SERVER_ROOT_URI + "node";
//		final String txUri = SERVER_ROOT_URI + "transaction/commit";
//
//		WebResource resource = Client.create().resource(nodeEntryPointUri);
//		// POST {} to the node entry point URI
//		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON)
//				.type(MediaType.APPLICATION_JSON).entity("{}")
//				.post(ClientResponse.class);
//
//		final URI location = response.getLocation();
//		System.out.println(String.format(
//				"POST to [%s], status code [%d], location header [%s]",
//				nodeEntryPointUri, response.getStatus(), location.toString()));
//		response.close();
		// End creation of node
		// Begin properties
//		String propertyUri = location.toString() + "/properties/name";
//		resource = Client.create().resource(propertyUri);
//		response = resource.accept(MediaType.APPLICATION_JSON)
//				.type(MediaType.APPLICATION_JSON).entity("\"" + name + "\"")
//				.put(ClientResponse.class);
//
//		System.out.println(String.format("PUT to [%s], status code [%d]",
//				propertyUri, response.getStatus()));
//		response.close();
	}

	public UserEntity read(String username) {
//		final String txUri = SERVER_ROOT_URI + "transaction/commit";
//		WebResource resource = Client.create().resource( txUri );
//		String query = "MATCH (user:User { Username:'" + username + "' }) RETURN user";
//		String payload = "{\"statements\" : [ {\"statement\" : \"" + query + "\"} ]}";
//		ClientResponse response = resource
//		        .accept( MediaType.APPLICATION_JSON )
//		        .type( MediaType.APPLICATION_JSON )
//		        .entity( payload )
//		        .post( ClientResponse.class );
//		response.getEntity(String.class);
//		//TODO: Create Parser for response, get everything after "{"row":[{" and before "}]}]}],"errors":[]}"
//		//TODO: Create and return User Data Object
//		Iterable<Node> userNode = cypherQueryEngine.query(query, null).to(Node.class);
//		return new UserEntity((String)userNode.iterator().next().getProperty("Username"));
		return null;
	};

	public void update() {

	};

	public void delete() {
	};
}