package iul.iscte.tsio.main;

import iul.iscte.tsio.controller.UsersController;
import iul.iscte.tsio.server.Server;
import iul.iscte.tsio.view.UsersView;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("Enter the server address: ");
		Scanner scanner = new Scanner(System.in);
		String serverAddress = scanner.nextLine();
		scanner.close();
		// Verify is /db/data is there
		// serverAddress = serverAddress + "/db/data";
		serverAddress = serverAddress.concat("/db/data/");
		boolean connected = Server.getInstance().login(serverAddress);
		boolean authenticated = Server.getInstance().setLoggedUser("techsupport@lemonparty.com");
		// Create GUI

		if (authenticated) {
			UsersView.getInstance().setVisible(true);
		}else{
			System.out.println("It was not possible to authenticate user");
			System.exit(0);
		}
		System.out.println(authenticated);
	}
}
