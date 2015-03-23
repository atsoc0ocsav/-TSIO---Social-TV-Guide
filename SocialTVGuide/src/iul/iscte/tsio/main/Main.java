package iul.iscte.tsio.main;

import iul.iscte.tsio.controller.UsersController;
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
		Server.getInstance().login(serverAddress);

		// Create GUI
		boolean authetincated = UsersController.getInstance().login(
				"email3@email.com");
		if (authetincated) {
			UsersView.getInstance().setVisible(true);
		}

		System.out.println(authetincated);
	}
}
