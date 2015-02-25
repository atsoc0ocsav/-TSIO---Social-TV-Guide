package iul.iscte.tsio.controller;

import java.net.URI;

import javax.ws.rs.core.MediaType;

import org.neo4j.graphdb.Node;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Neo4jControllerPrograms {
	private static Neo4jControllerPrograms instance = null;
	final String SERVER_ROOT_URI = "http://localhost:7474/db/data/";

	private Neo4jControllerPrograms() {
		WebResource resource = Client.create().resource(SERVER_ROOT_URI);
		ClientResponse response = resource.get(ClientResponse.class);

		System.out.println(String.format("GET on [%s], status code [%d]",
				SERVER_ROOT_URI, response.getStatus()));
		response.close();
	}

	public static Neo4jControllerPrograms getInstance() {
		if (instance == null) {
			synchronized (Neo4jControllerUsers.class) {
				instance = new Neo4jControllerPrograms();
			}
		}
		return instance;
	}

	public void create(String name) {
		final String nodeEntryPointUri = SERVER_ROOT_URI + "node";
		final String txUri = SERVER_ROOT_URI + "transaction/commit";

		WebResource resource = Client.create().resource(nodeEntryPointUri);
		// POST {} to the node entry point URI
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).entity("{}")
				.post(ClientResponse.class);

		final URI location = response.getLocation();
		System.out.println(String.format(
				"POST to [%s], status code [%d], location header [%s]",
				nodeEntryPointUri, response.getStatus(), location.toString()));
		response.close();
		// End creation of node
		// Begin properties
		String propertyUri = location.toString() + "/properties/name";
		resource = Client.create().resource(propertyUri);
		response = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).entity("\"" + name + "\"")
				.put(ClientResponse.class);

		System.out.println(String.format("PUT to [%s], status code [%d]",
				propertyUri, response.getStatus()));
		response.close();
	}

	public String read() {
		return "";
	};

	public void update() {

	};

	public void delete() {
	};
}
