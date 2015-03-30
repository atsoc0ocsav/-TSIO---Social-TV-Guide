package iul.iscte.tsio.view.optionPanes;

import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;
import iul.iscte.tsio.server.Server;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SelectUserOptionPane extends JFrame {
	private static final long serialVersionUID = 1919821842077609484L;

	private JTextField usernameToSearch;

	public SelectUserOptionPane() {
		JPanel dialogJPane = new JPanel(new GridLayout());

		// JTextFields Construction
		dialogJPane.add(new JLabel("Nome: "));

		usernameToSearch = new JTextField();
		usernameToSearch.getDocument().addDocumentListener(
				new DocumentListener() {

					@Override
					public void removeUpdate(DocumentEvent e) {
						searchForUser();
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						searchForUser();
					}

					@Override
					public void changedUpdate(DocumentEvent arg0) {
						System.out
								.println("Change update activated, on SelectUserOptionPane JTextField");
					}
				});
		dialogJPane.add(usernameToSearch);

		int result = JOptionPane.showConfirmDialog(null, dialogJPane,
				"Please Enter Friend name", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {

		}
	}

	private void searchForUser() {
		if (Server.getInstance().isConnected()) {
			List<UserEntity> list = UserDAOImpl.getInstance()
					.getUsersWithRegex(usernameToSearch.getText());
		}

	}

	public static void main(String[] args) {
		new SelectUserOptionPane();
	}
}
