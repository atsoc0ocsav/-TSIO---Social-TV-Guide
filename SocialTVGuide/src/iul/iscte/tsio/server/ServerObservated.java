package iul.iscte.tsio.server;

public interface ServerObservated {
	public void addObserver(ServerObservator observator);
	
	public boolean isConnected();
	
	public boolean isAuthenticated();
	
	public boolean connect(String serverAddress);
	
	public boolean logUser(String email);
}
