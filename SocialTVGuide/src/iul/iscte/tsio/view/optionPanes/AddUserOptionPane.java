package iul.iscte.tsio.view.optionPanes;

import iul.iscte.tsio.model.UserDAOImpl;
import iul.iscte.tsio.model.UserEntity;
import iul.iscte.tsio.utils.Labels;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddUserOptionPane {
	private static final String EMAIL_REGEX = "[A-Za-z0-9._+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
	private static final String USERNAME_REGEX = "^\\w{5,15}$";

	private JTextField email;
	private JTextField username;

	private String emailStr = null;
	private String usernameStr = null;

	public AddUserOptionPane() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		panel.add(new JLabel("Username: "));
		panel.add(username = new JTextField());
		panel.add(new JLabel("E-mail: "));
		panel.add(email = new JTextField());

		Object[] buttonsLabels = new Object[] {
				Labels.ADDUSERBUTTON_ADDUSER_PANE.getValue(),
				Labels.CANCELBUTTON_ADDUSER_PANE.getValue() };

		boolean exit = false;
		while (!exit) {
			int result = JOptionPane.showOptionDialog(null, panel,
					Labels.TITLE_ADDUSER_PANE.getValue(),
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, buttonsLabels, buttonsLabels[0]);

			if (result == JOptionPane.OK_OPTION) {
				int returnCode = validateUserInformations();

				if (returnCode == 0) {
					System.out.print("Adding new user \"" + usernameStr
							+ "\"... ");

					long opResult = UserDAOImpl.getInstance().insertUser(
							new UserEntity(usernameStr, emailStr));
					System.out.print("opResult: " + opResult+" ");

					System.out.println(opResult > 0 ? "with success!"
							: "with errors!");

					if (opResult > 0) {
						String message = String.format(
								Labels.TEXT_SUCCESSADDINGUSER_ADDUSER_PANE
										.getValue(), usernameStr);
						JOptionPane.showMessageDialog(null, message,
								Labels.TITLE_SUCCESSADDINGUSER_ADDUSER_PANE
										.getValue(),
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,
								Labels.TEXT_ERRORADDINGUSER_ADDUSER_PANE
										.getValue(),
								Labels.TITLE_ERRORADDINGUSER_ADDUSER_PANE
										.getValue(), JOptionPane.ERROR_MESSAGE);
					}

					exit = true;
				} else {
					String message = "";
					switch (returnCode) {
					case 1:
						message = "One or more fields in the form are empty. Please correct it/them and try again!";
						break;
					case 2:
						message = "The inserted email is invalid. Please correct it and try again!";
						break;
					case 3:
						message = "The inserted username is invalid. Please correct it and try again!";
						break;
					default:
						message = Labels.TEXT_INVALIDADDINGUSER_ADDUSER_PANE
								.getValue();
						break;
					}

					JOptionPane.showMessageDialog(null, message,
							Labels.TITLE_INVALIDADDINGUSER_ADDUSER_PANE
									.getValue(), JOptionPane.ERROR_MESSAGE);
				}
			} else {
				exit = true;
			}
		}
	}

	private int validateUserInformations() {
		String emailTmp = email.getText();
		String usernameTmp = username.getText();

		if (emailTmp == null || usernameTmp == null) {
			return 1;
		} else {
			if (!emailTmp.matches(EMAIL_REGEX)) {
				return 2;
			} else {
				if (!usernameTmp.matches(USERNAME_REGEX)) {
					return 3;
				} else {
					emailStr = emailTmp;
					usernameStr = usernameTmp;
					return 0;
				}
			}
		}
	}
}
