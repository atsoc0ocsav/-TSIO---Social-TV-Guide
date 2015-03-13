package iul.iscte.tsio.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Vector;

import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;
import iul.iscte.tsio.utils.Labels;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class UserFriendsListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private UserEntity loggedUser;
	private JButton addFriend;
	private JButton deleteFriend;
	private JScrollPane scrollPane;
	private JList<UserEntity> friendsList;

	public UserFriendsListPanel(UserEntity loggedUser) {
		this.loggedUser = loggedUser;
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(0, 5, 5, 5) );
		
		JPanel aux = new JPanel();
		aux.setLayout(new FlowLayout());
		aux.add(addFriend = new JButton(Labels.ADDFRIENDBUTTON.getValue()));
		aux.add(deleteFriend = new JButton(Labels.DELETEFRIENDBUTTON.getValue()));
		add(aux, BorderLayout.SOUTH);

		
		// TODO - analisar melhor esta solução
		List<UserEntity> data = UserDAOImpl.getInstance().getAllFriends(loggedUser);
		// JList only accepts vector or array
		Vector<UserEntity> userData = new Vector<UserEntity>();
		for (UserEntity userEntity : data) {
			userData.add(userEntity);
		}

		friendsList = new JList<UserEntity>(userData);
		friendsList
				.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		add(scrollPane = new JScrollPane(friendsList), BorderLayout.CENTER);
	}

}
