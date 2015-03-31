package iul.iscte.tsio.view.optionPanes;

import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;
import iul.iscte.tsio.server.Server;
import iul.iscte.tsio.utils.Labels;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class SelectUserOptionPane {
	private JComboBox<String> username;
	private List<String> usernames = new ArrayList<String>();

	public SelectUserOptionPane() {

		username = new JComboBox<String>();
		username.setEditable(true);
		((JTextComponent) (username.getEditor().getEditorComponent()))
				.getDocument().addDocumentListener(new DocumentListener() {

					@Override
					public void removeUpdate(DocumentEvent e) {
						searchForUser();
						username.showPopup();
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						searchForUser();
						username.showPopup();
					}

					@Override
					public void changedUpdate(DocumentEvent arg0) {
						System.out
								.println("Change update activated, on SelectUserOptionPane JTextField");
					}
				});
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(new JLabel("Username: "));
		panel.add(username);
		AutoCompleteDecorator.decorate(username);

		//searchForUser();

		Object[] buttonsLabels = new Object[] {
				Labels.ADDFRIENDBUTTON_ADDFRIEND_PANE.getValue(),
				Labels.CANCELBUTTON_ADDFRIEND_ADDFRIEND_PANE.getValue() };
		int result = JOptionPane.showOptionDialog(null, panel,
				Labels.TITLE_ADDFRIEND_PANE.getValue(),
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, buttonsLabels, buttonsLabels[0]);

		if (result == JOptionPane.OK_OPTION) {
			System.out.println("Boa!!");
		}
	}

	private void searchForUser() {
		if (Server.getInstance().isConnected()) {
			String str = username.getEditor().getItem().toString();
			List<UserEntity> users = UserDAOImpl.getInstance()
					.getUsersWithRegex(str);

			usernames.clear();
			usernames.add(str);
			for (UserEntity user : users) {
				usernames.add(user.getUsername());
			}

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					username.setModel(new DefaultComboBoxModel(usernames
							.toArray()));
				}
			});
		}
	}
}
