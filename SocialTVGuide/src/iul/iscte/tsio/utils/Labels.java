package iul.iscte.tsio.utils;

public enum Labels {
	// Login view labels
	LOGINVIEWTITLE("Social TV Guide - Connect to Server"),
	CONNECTBUTTON("Connect"),
	CANCELBUTTON("Cancel"),
	DISCONNECTBUTTON("Disconnect"),
	LOGINBUTTON("Login"),	
	
	
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
