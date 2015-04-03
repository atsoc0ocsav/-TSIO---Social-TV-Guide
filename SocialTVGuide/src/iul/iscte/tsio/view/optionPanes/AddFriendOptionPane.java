package iul.iscte.tsio.view.optionPanes;

import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;
import iul.iscte.tsio.server.Server;
import iul.iscte.tsio.utils.Labels;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

public class AddFriendOptionPane {
	private JComboBox<String> usernameComboBox;
	private DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>();
	private AtomicBoolean inUpdate = new AtomicBoolean(false);
	private ArrayList<String> usernames = new ArrayList<String>();

	public AddFriendOptionPane() {
		usernameComboBox = new JComboBox<String>();
		comboBoxModel = new DefaultComboBoxModel<>();
		usernameComboBox.setEditable(true);
		usernameComboBox.setModel(comboBoxModel);
		usernameComboBox.setSelectedIndex(-1);

		((JTextComponent) (usernameComboBox.getEditor().getEditorComponent()))
				.getDocument().addDocumentListener(new DocumentListener() {

					@Override
					public void removeUpdate(DocumentEvent e) {
						if (!inUpdate.get()) {
							searchForUser();
						}
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						if (!inUpdate.get()) {
							searchForUser();
						}
					}

					@Override
					public void changedUpdate(DocumentEvent arg0) {
						System.out
								.println("Change update activated, on AddFriendOptionPane JTextField");
					}
				});
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(new JLabel("Username: "));
		panel.add(usernameComboBox);
		searchForUser();

		Object[] buttonsLabels = new Object[] {
				Labels.ADDFRIENDBUTTON_ADDFRIEND_PANE.getValue(),
				Labels.CANCELBUTTON_ADDFRIEND_PANE.getValue() };

		boolean exit = false;
		while (!exit) {
			int result = JOptionPane.showOptionDialog(null, panel,
					Labels.TITLE_ADDFRIEND_PANE.getValue(),
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, buttonsLabels, buttonsLabels[0]);

			if (result == JOptionPane.OK_OPTION) {
				String name = usernameComboBox.getEditor().getItem().toString();

				if (usernames.contains(name)) {
					System.out.print("Adding new friendship with \"" + name
							+ "\"... ");

					boolean opResult = UserDAOImpl.getInstance()
							.createFriendshipRelationship(
									Server.getInstance().getLoggedUser(),
									UserDAOImpl.getInstance().getUserByName(
											name));

					System.out.println(opResult ? "with success!"
							: "with errors!");

					if (opResult) {
						String message = String.format(
								Labels.TEXT_SUCCESSADDINGFRIEND_ADDFRIEND_PANE
										.getValue(), name);
						JOptionPane.showMessageDialog(null, message,
								Labels.TITLE_SUCCESSADDINGFRIEND_ADDFRIEND_PANE
										.getValue(),
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,
								Labels.TEXT_ERRORADDINGFRIEND_ADDFRIEND_PANE
										.getValue(),
								Labels.TITLE_ERRORADDINGFRIEND_ADDFRIEND_PANE
										.getValue(), JOptionPane.ERROR_MESSAGE);
					}

					exit = true;
				} else {
					JOptionPane.showMessageDialog(null,
							Labels.TEXT_INVALIDADDINGFRIEND_ADDFRIEND_PANE
									.getValue(),
							Labels.TITLE_INVALIDADDINGFRIEND_ADDFRIEND_PANE
									.getValue(), JOptionPane.ERROR_MESSAGE);
				}
			} else {
				exit = true;
			}
		}
	}

	private void searchForUser() {
		if (Server.getInstance().isConnected()) {
			String str = "";
			try {
				str = usernameComboBox.getEditor().getItem().toString();
			} catch (NullPointerException e) {
				System.err
						.println("NullPointerException on AddFriendOptionPane!");
			}
			List<UserEntity> users = UserDAOImpl.getInstance()
					.getUsersWithRegex(str);

			usernames.clear();
			for (UserEntity user : users) {
				if (!user.getUsername().contains(
						Server.getInstance().getLoggedUser().getUsername())) {
				usernames.add(user.getUsername());
				}
			}

			String toAdd = str;

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					inUpdate.set(true);
					comboBoxModel.removeAllElements();

					if (!usernames.contains(toAdd)) {
						comboBoxModel.addElement(toAdd);
					}
					for (String user : usernames) {
						comboBoxModel.addElement(user);
					}

					JTextComponent editor = ((JTextField) usernameComboBox
							.getEditor().getEditorComponent());
					editor.setSelectionEnd(0);
					editor.setSelectionStart(0);

					editor.setCaretPosition(usernameComboBox.getEditor()
							.getItem().toString().length());

					usernameComboBox.showPopup();
					inUpdate.set(false);
				}
			});
		}
	}
}
