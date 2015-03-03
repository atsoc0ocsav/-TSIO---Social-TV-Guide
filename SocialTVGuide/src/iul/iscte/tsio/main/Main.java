package iul.iscte.tsio.main;

import iul.iscte.tsio.controller.UsersController;
import iul.iscte.tsio.server.Server;

public class Main {

	public static void main(String[] args) {
		Server.getInstance();
		// Create GUI
		boolean authetincated = UsersController.getInstance().login("test");
	}
}
