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
	CANCELBUTTON_ADDFRIEND_PANE("Cancel"),
	TITLE_INVALIDADDINGFRIEND_ADDFRIEND_PANE("Invalid Username"),
	TEXT_INVALIDADDINGFRIEND_ADDFRIEND_PANE("The inserted username is invalid. Please correct it!"),
	TITLE_ERRORADDINGFRIEND_ADDFRIEND_PANE("Error on Add Friend"),
	TEXT_ERRORADDINGFRIEND_ADDFRIEND_PANE("Internal error occurred. Please contact system administrator"),
	TITLE_SUCCESSADDINGFRIEND_ADDFRIEND_PANE("Success on Add Friend"),
	TEXT_SUCCESSADDINGFRIEND_ADDFRIEND_PANE("The friendship with user %s was successfully added!"),
	
	// Delete friend option pane
	TITLE_DELETEFRIEND_PANE("Insert the friend's username"),
	ADDFRIENDBUTTON_DELETEFRIEND_PANE("Delete Friend"),
	CANCELBUTTON_DELETEFRIEND_PANE("Cancel"),
	TITLE_INVALIDDELETINGFRIEND_DELETEFRIEND_PANE("Invalid Username"),
	TEXT_INVALIDDELETINGFRIEND_DELETEFRIEND_PANE("The inserted username is invalid. Please correct it!"),
	TITLE_ERRORDELETINGFRIEND_DELETEFRIEND_PANE("Error on Delete Friend"),
	TEXT_ERRORDELETINGFRIEND_DELETEFRIEND_PANE("Internal error occurred. Please contact system administrator"),
	TITLE_SUCCESSDELETINGFRIEND_DELETEFRIEND_PANE("Success on Delete Friend"),
	TEXT_SUCCESSDELETINGFRIEND_DELETEFRIEND_PANE("The friendship with user %s was successfully deleted!"),
	
	
	// Search Panel
	SEARCHBUTTON_SEARCH_PANEL("Search Programs"),
	FIELDTEXT_SEARCH_PANEL("Insert program name to search..."),
	
	//Others
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
