package iul.iscte.tsio.utils;

public enum Labels {
	// Login view labels
	VIEWTITLE_LOGIN_PANE("Social TV Guide - Connect to Server"),
	CONNECTBUTTON_LOGIN_PANE("Connect"),
	CANCELBUTTON_LOGIN_PANE("Cancel"),
	DISCONNECTBUTTON_LOGIN_PANE("Disconnect"),
	LOGINBUTTON_LOGIN_PANE("Login"),	
	
	// Add friend option pane
	TITLE_ADDFRIEND_PANE("Insert the friend's username"),
	ADDFRIENDBUTTON_ADDFRIEND_PANE("Add Friend"),
	CANCELBUTTON_ADDFRIEND_ADDFRIEND_PANE("Cancel"),
	
	// Others
	SEARCHLABEL("Search Programs"), 
	USERSVIEWTITLE("User View"),
	ADDUSERBUTTON("Add Users"), 
	ADDPROGRAMBUTTON("Add Program"), 
	ADDFRIENDBUTTON("Add Friend"), 
	DELETEFRIENDBUTTON("Remove Friend"), 
	USERINFONAMELABEL("Username"), 
	USERINFOEMAILLABEL("User Email"), 
	PROGRAMMOREDETAILS("More Details"), 
	SEARCHRESULTDIALOGTITLE("Search Results"), 
	CLOSE("Close"), 
	WATCH("Watch"), 
	LIKE("Like"), 
	UNLIKE("UnLike"), 
	RECOMMENDATIONS("Recommendations"), 
	MYFRIENDS("My Friends");

	private final String value;

	private Labels(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
