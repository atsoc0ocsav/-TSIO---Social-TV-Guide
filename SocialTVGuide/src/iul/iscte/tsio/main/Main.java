package iul.iscte.tsio.main;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Main {
	final static String SERVER_ROOT_URI = "http://localhost:7474/db/data/";

	public static void main(String[] args) {
		WebResource resource = Client.create().resource(SERVER_ROOT_URI);
		ClientResponse response = resource.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			System.out.println(String.format(
					"Server is unavailable, status code [%d]",
					response.getStatus()));
			response.close();
		} else {
			System.out.println(String.format("GET on [%s], status code [%d]",
					SERVER_ROOT_URI, response.getStatus()));
			//Create GUI
		}
	}
}
