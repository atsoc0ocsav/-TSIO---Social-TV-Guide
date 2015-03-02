package iul.iscte.tsio.server;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class Server {
	final static String SERVER_ROOT_URI = "http://52.10.21.89:7474/db/data/";
	final static String username = "username";
	final static String password = "password";
	private static Server instance = null;

	private Server(){
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter(username, password));
		WebResource resource = client.resource(SERVER_ROOT_URI);
		ClientResponse response = resource.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			System.out.println(String.format(
					"Server is unavailable, status code [%d]",
					response.getStatus()));
			response.close();
		} else {
			System.out.println(String.format("GET on [%s], status code [%d]",
					SERVER_ROOT_URI, response.getStatus()));
		}
	}
	
	public static Server getInstance() {
		if (instance == null) {
			synchronized (Server.class) {
				instance = new Server();
			}
		}
		return instance;
	}
	
	public String getServer_ROOT_URI(){
		return SERVER_ROOT_URI;
	}
}
