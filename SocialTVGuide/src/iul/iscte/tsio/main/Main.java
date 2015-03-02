package iul.iscte.tsio.main;

import iul.iscte.tsio.controller.Neo4jControllerUsers;
import iul.iscte.tsio.server.Server;

public class Main {

	public static void main(String[] args) {
		Server.getInstance();
		// Create GUI
		boolean authetincated = Neo4jControllerUsers.getInstance().login("test");
	}
}
