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
		//serverAddress = serverAddress + "/db/data";
		
		serverAddress = "http://52.10.21.89:7474/db/data/";

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
