package iul.iscte.tsio.main;

import iul.iscte.tsio.controller.UsersController;
import iul.iscte.tsio.server.Server;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("Enter the server address: ");
	    Scanner scanner = new Scanner(System.in);
	    String serverAddress = scanner.nextLine();
	    scanner.close();
	    //Verify is /db/data is there
	    serverAddress = serverAddress + "/db/data";
		Server.getInstance().login(serverAddress);
		// Create GUI
		boolean authetincated = UsersController.getInstance().login("test@test.pt");
		System.out.println(authetincated);
	}
}
