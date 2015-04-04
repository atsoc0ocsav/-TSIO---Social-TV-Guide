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
	TEXT_ERRORADDINGFRIEND_ADDFRIEND_PANE("Internal error occurred. Please contact system administrator (CODE 001)"),
	TITLE_SUCCESSADDINGFRIEND_ADDFRIEND_PANE("Success on Add Friend"),
	TEXT_SUCCESSADDINGFRIEND_ADDFRIEND_PANE("The friendship with user %s was successfully added!"),
	
	// Delete friend option pane
	TITLE_DELETEFRIEND_PANE("Insert the friend's username"),
	DELETEFRIENDBUTTON_DELETEFRIEND_PANE("Delete Friend"),
	CANCELBUTTON_DELETEFRIEND_PANE("Cancel"),
	TITLE_INVALIDDELETINGFRIEND_DELETEFRIEND_PANE("Invalid Username"),
	TEXT_INVALIDDELETINGFRIEND_DELETEFRIEND_PANE("The inserted username is invalid. Please correct it!"),
	TITLE_ERRORDELETINGFRIEND_DELETEFRIEND_PANE("Error on Delete Friend"),
	TEXT_ERRORDELETINGFRIEND_DELETEFRIEND_PANE("Internal error occurred. Please contact system administrator (CODE 002)"),
	TITLE_SUCCESSDELETINGFRIEND_DELETEFRIEND_PANE("Success on Delete Friend"),
	TEXT_SUCCESSDELETINGFRIEND_DELETEFRIEND_PANE("The friendship with user %s was successfully deleted!"),
	
	// Insert new user option pane
	TITLE_ADDUSER_PANE("Create new user"),
	ADDUSERBUTTON_ADDUSER_PANE("Add User"),
	CANCELBUTTON_ADDUSER_PANE("Cancel"),
	TITLE_INVALIDADDINGUSER_ADDUSER_PANE("Invalid Fields"),
	TEXT_INVALIDADDINGUSER_ADDUSER_PANE("There are invalid informations in the form. Please correct them and try again!"),
	TITLE_ERRORADDINGUSER_ADDUSER_PANE("Error on adding new user"),
	TEXT_ERRORADDINGUSER_ADDUSER_PANE("Internal error occurred. Please contact system administrator (CODE 003)"),
	TITLE_SUCCESSADDINGUSER_ADDUSER_PANE("Success on adding new user"),
	TEXT_SUCCESSADDINGUSER_ADDUSER_PANE("The user %s was succesfully added!"),
	
	// Delete user option pane
	TITLE_DELETEUSER_PANE("Delete a user"),
	DELETEUSERBUTTON_DELETEUSER_PANE("Delete User"),
	CANCELBUTTON_DELETEUSER_PANE("Cancel"),
	TITLE_INVALIDDELETINGUSER_DELETEUSER_PANE("Invalid Username"),
	TEXT_INVALIDDELETINGUSER_DELETEUSER_PANE("The inserted username is invalid. Please correct it!"),
	TITLE_ERRORDELETINGUSER_DELETEUSER_PANE("Error on deleting user"),
	TEXT_ERRORDELETINGUSER_DELETEUSER_PANE("Internal error occurred. Please contact system administrator (CODE 004)"),
	TITLE_SUCCESSDELETINGUSER_DELETEUSER_PANE("Success on delete user"),
	TEXT_SUCCESSDELETINGUSER_DELETEUSER_PANE("The user %s was succesfully deleted!"),
	
	// Insert new program option pane
	TITLE_ADDPROGAM_PANE("Create a new program"),
	ADDPROGRAMBUTTON_ADDPROGRAM_PANE("Add Program"),
	CANCELBUTTON_ADDPROGRAM_PANE("Cancel"),
	TITLE_INVALIDADDINGPROGRAM_ADDPROGRAM_PANE("Invalid Fields"),
	TEXT_INVALIDADDINGPROGRAM_ADDPROGRAM_PANE("There are invalid informations in the form. Please correct them and try again!"),
	TITLE_ERRORADDINGPROGRAM_ADDPROGRAM_PANE("Error on adding new program"),
	TEXT_ERRORADDINGPROGRAM_ADDPROGRAM_PANE("Internal error occurred. Please contact system administrator (CODE 005)"),
	TITLE_SUCCESSADDINGPROGRAM_ADDPROGRAM_PANE("Success on adding new program"),
	TEXT_SUCCESSADDINGPROGRAM_ADDPROGRAM_PANE("The program %s was succesfully added!"),
	
	// Team Option Panel
	TEXT_TEAM_PANE("This demo application was developed by:\n"
			+ "Diogo Pedroso\n"
			+ "Fábio Martins\n"
			+ "Vasco Costa\n"
			+ "ISCTE-IUL 2015 TSIO"),
	TITLE_TEAM_PANE("Developer Team"),
	
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
