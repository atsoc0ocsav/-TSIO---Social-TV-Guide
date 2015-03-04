package iul.iscte.tsio.server;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Server {
	private static String SERVER_ROOT_URI;
	private static Server instance = null;

	private Server(){}
	
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
	
	public void login(String serverAddress){
		SERVER_ROOT_URI = serverAddress;
		Client client = Client.create();
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
}
