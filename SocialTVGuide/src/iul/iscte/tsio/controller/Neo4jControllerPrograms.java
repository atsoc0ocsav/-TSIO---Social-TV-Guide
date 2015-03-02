package iul.iscte.tsio.controller;

import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;

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

	public boolean create(UserEntity userToInsert) {
		return UserDAOImpl.getInstance().insertUser(userToInsert);
	}

	public String read() {
		return "";
	};

	public void update() {

	};

	public void delete() {
	};
}
