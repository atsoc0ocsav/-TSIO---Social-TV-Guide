package iul.iscte.tsio.server;

import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Server implements ServerObservated {
	private static String SERVER_ROOT_URI;
	private static Server instance = null;
	private UserEntity loggedUser;

	private String serverAddress = "";
	private String email = "";

	private boolean connectedToServer = false;
	private boolean authenticatedUser = false;

	private ServerObservator observator;

	private boolean connectingToServer = false;

	private Server() {
	}

	public static Server getInstance() {
		if (instance == null) {
			synchronized (Server.class) {
				instance = new Server();
			}
		}
		return instance;
	}

	public String getServer_ROOT_URI() {
		return SERVER_ROOT_URI;
	}

	@Override
	public boolean connect(String serverAddress) {
		this.serverAddress = serverAddress;
		ConnectThread connectToServerThread = new ConnectThread();
		connectToServerThread.start();

		return connectedToServer;
	}

	private void connectToServer() {
		try {
			SERVER_ROOT_URI = serverAddress;
			Client client = Client.create();
			WebResource resource = client.resource(SERVER_ROOT_URI);
			ClientResponse response = resource.get(ClientResponse.class);
			
			if (response.getStatus() != 200) {
				System.out.println(String.format(
						"Server is unavailable, status code [%d]",
						response.getStatus()));
				sysoutToUser("Unable to connect to server!\n");
				response.close();
				connectedToServer = false;
				observator.unlockIPHostnameInput();

			} else {
				System.out.println(String.format("GET on [%s], status code [%d]",
						SERVER_ROOT_URI, response.getStatus()));
				sysoutToUser("Connected with sucess to "+SERVER_ROOT_URI+"!\n");
				connectedToServer = true;
				observator.authenticateUser();
			}
		} catch (com.sun.jersey.api.client.ClientHandlerException e) {
			sysoutToUser("Server is unavailable (server timeout)\n");
			connectedToServer = false;
			observator.unlockIPHostnameInput();
		}
	}

	@Override
	public boolean logUser(String email) {
		this.email = email;
		AuthenticateUserThread authenticateUserThread = new AuthenticateUserThread();
		authenticateUserThread.start();

		return authenticatedUser;
	}

	private void authenticateUser() {
		UserEntity loggedUser = UserDAOImpl.getInstance().getUserByEmail(email);
		if (loggedUser != null) {
			this.loggedUser = loggedUser;
			authenticatedUser = true;
			sysoutToUser("User authenticated with success!\n");
		} else {
			authenticatedUser = false;
			sysoutToUser("Error authenticating user!\n");
		}
	}

	public UserEntity getLoggedUser() {
		return loggedUser;
	}

	@Override
	public boolean isConnected() {
		synchronized (this) {
			return connectedToServer;
		}
	}

	public boolean isAuthenticated() {
		synchronized (this) {
			return authenticatedUser;
		}
	}

	private class ConnectThread extends Thread {
		@Override
		public void run() {
			synchronized (this) {
				connectingToServer = true;
				if (!connectedToServer) {
					connectToServer();
				}
			}
		}
	}

	private class AuthenticateUserThread extends Thread {
		@Override
		public void run() {
			synchronized (this) {
				if (!authenticatedUser && connectingToServer
						&& !connectingToServer) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				if (!authenticatedUser && connectedToServer) {
					authenticateUser();
				}

				if (authenticatedUser && connectedToServer) {
					observator.launchGUI();
				}
			}
		}
	}

	@Override
	public void addObserver(ServerObservator observator) {
		this.observator = observator;
	}

	private void sysoutToUser(String str) {
		if (observator != null) {
			observator.addMessage(str);
		} else {
			System.out.println(str);
		}
	}

	public void unLogUser() {
		if (loggedUser != null)
			loggedUser = null;
	}
}
