package iul.iscte.tsio.server;

public interface ServerObservator {	
	public void showMessage(String message);

	public void clearMessages();
	
	public void addMessage(String message);
	
	public void lockIPHostnameInput();

	public void unlockIPHostnameInput();
	
	public void launchGUI();
	
	public void authenticateUser();
}
