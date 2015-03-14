package iul.iscte.tsio.view.panels;


import iul.iscte.tsio.model.UserEntity;
import iul.iscte.tsio.utils.Labels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

public class UserInformation extends JPanel {

	private static final long serialVersionUID = 1L;

	public UserInformation(UserEntity loggedUser) {
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		setBorder(new EmptyBorder(5, 5, 5, 5) );
		
		JLabel usernameLabel = new JLabel(Labels.USERINFONAMELABEL.getValue() +  ": ");
		JLabel usernameValue = new JLabel(loggedUser.getUsername());
		add(usernameLabel);
		add(usernameValue);
		layout.putConstraint(SpringLayout.WEST, usernameLabel, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, usernameLabel, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, usernameValue, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, usernameValue, 20, SpringLayout.EAST, usernameLabel);
		
		
		JLabel emailLabel = new JLabel(Labels.USERINFOEMAILLABEL.getValue() + ": ");
		JLabel emailValue = new JLabel(loggedUser.getEmail());
		add(emailLabel);
		add(emailValue);
		
		layout.putConstraint(SpringLayout.WEST, emailLabel, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, emailLabel, 50, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, emailValue, 50, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, emailValue, 20, SpringLayout.EAST, emailLabel);
		
		
	}
}
