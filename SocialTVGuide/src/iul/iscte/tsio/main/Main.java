package iul.iscte.tsio.main;

import javax.swing.JOptionPane;

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
		boolean connected = false;
		boolean authenticated = false;

		while (!connected || !authenticated) {
			ServerAddressRequestToUserPane addressRequestPane = new ServerAddressRequestToUserPane();
			String serverAddress = "http://"
					+ addressRequestPane.getIpAddressAsString() + ":"
					+ addressRequestPane.getPortNumberAsString() + "/db/data/";

			System.out.println("Trying to connect to server \"" + serverAddress
					+ "\"...");

			try {
				if (!Server.getInstance().isConnectedToServer()) {
					Server.getInstance().login(serverAddress);
				}
				connected = Server.getInstance().isConnectedToServer();
			} catch (com.sun.jersey.api.client.ClientHandlerException e) {
				JOptionPane.showMessageDialog(null,
						"Connection Timed Out. Please check address and port!",
						"Connection Error", JOptionPane.ERROR_MESSAGE);
			}

			authenticated = Server.getInstance().setLoggedUser(
					"techsupport@lemonparty.com");

			// Create GUI
			if (authenticated) {
				UsersView.getInstance().setVisible(true);
				JOptionPane.showMessageDialog(null, "Successfully Connected!",
						"Connection Information", JOptionPane.OK_OPTION);
			} else {
				//System.out.println("It was not possible to authenticate user");
				JOptionPane.showMessageDialog(null,
						"Unable to autenticate user. Please correct username!",
						"Authentication Error", JOptionPane.ERROR_MESSAGE);
				// System.exit(0);
			}
		}
	}
}
