package iul.iscte.tsio.main;

import iul.iscte.tsio.server.Server;
import iul.iscte.tsio.view.UsersView;
import iul.iscte.tsio.view.inputPanes.ServerAddressRequestToUserPane;

public class Main {

	public static void main(String[] args) {
		// System.out.println("Enter the server address: ");
		// Scanner scanner = new Scanner(System.in);
		// String serverAddress = scanner.nextLine();
		// scanner.close();

		// Get Server IP Address/ Hostname
		ServerAddressRequestToUserPane addressRequestPane = new ServerAddressRequestToUserPane();
		String serverAddress = "http://"
				+ addressRequestPane.getIpAddressAsString() + ":"
				+ addressRequestPane.getPortNumberAsString() + "/db/data/";

		System.out.println("Server: " + serverAddress);
		boolean connected = Server.getInstance().login(serverAddress);
		boolean authenticated = Server.getInstance().setLoggedUser(
				"techsupport@lemonparty.com");

		// Create GUI

		if (authenticated) {
			UsersView.getInstance().setVisible(true);
		} else {
			System.out.println("It was not possible to authenticate user");
			System.exit(0);
		}
		System.out.println(authenticated);
	}
}
