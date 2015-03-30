package iul.iscte.tsio.view.inputPanes;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ServerAddressRequestToUserPane extends JFrame {
	private static final long serialVersionUID = 1919821842077609484L;
	
	private static final String IP_ADDRESS_REGEX = "^([1-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3}$";
	private static final String HOSTNAME_REGEX = "^(([a-zA-Z]|[a-zA-Z][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z]|[A-Za-z][A-Za-z0-9\\-]*[A-Za-z0-9])$";
	
	//TODO: Change default IP Address to localhost
	private String DEFAULT_IP = "52.10.21.89";
	private String DEFAULT_PORT = "7474";
	private InetAddress ip = null;
	private String ip_asString = "";
	private int portNumber = -1;

	private JRadioButton firstLineRadioButton = new JRadioButton("IP Address: ");
	private JRadioButton secondLineRadioButton = new JRadioButton("Hostname:  ");

	private JTextField address1JTextField, address2JTextField,
			address3JTextField, address4JTextField;
	private JTextField hostnameJTextField;
	private JTextField portNumberJTextField;

	public ServerAddressRequestToUserPane() {
		JPanel dialogJPane = new JPanel(new GridLayout(3, 1));

		/*
		 * First Line Construction (IP address introduction)
		 */
		String[] ipAddrStr = DEFAULT_IP.split("\\.");

		// Text Fields Construction
		address1JTextField = new JTextField(3);
		address1JTextField.setText(ipAddrStr[0]);
		address2JTextField = new JTextField(3);
		address2JTextField.setText(ipAddrStr[1]);
		address3JTextField = new JTextField(3);
		address3JTextField.setText(ipAddrStr[2]);
		address4JTextField = new JTextField(3);
		address4JTextField.setText(ipAddrStr[3]);

		// Panel Construction
		JPanel addressAndLabelJPane = new JPanel(new FlowLayout(
				FlowLayout.LEFT, 3, 3));
		addressAndLabelJPane.add(firstLineRadioButton);
		addressAndLabelJPane.add(address1JTextField);
		addressAndLabelJPane.add(new JLabel("."));
		addressAndLabelJPane.add(address2JTextField);
		addressAndLabelJPane.add(new JLabel("."));
		addressAndLabelJPane.add(address3JTextField);
		addressAndLabelJPane.add(new JLabel("."));
		addressAndLabelJPane.add(address4JTextField);
		dialogJPane.add(addressAndLabelJPane);

		/*
		 * Second Line Construction (Hostname introduction)
		 */
		hostnameJTextField = new JTextField(15);
		hostnameJTextField.setText(DEFAULT_IP);

		// Panel Construction
		JPanel hostnameAndLabelJPane = new JPanel(new FlowLayout(
				FlowLayout.LEFT, 3, 3));
		hostnameAndLabelJPane.add(secondLineRadioButton);
		hostnameAndLabelJPane.add(hostnameJTextField);
		dialogJPane.add(hostnameAndLabelJPane);

		/*
		 * Third Line Construction (Port introduction)
		 */
		portNumberJTextField = new JTextField(5);
		portNumberJTextField.setText(DEFAULT_PORT);

		JPanel portAndLabelJPane = new JPanel(new FlowLayout(FlowLayout.LEFT,
				3, 3));
		portAndLabelJPane.add(new JLabel("  Port:"));
		portAndLabelJPane.add(portNumberJTextField);
		dialogJPane.add(portAndLabelJPane);

		/*
		 * JRadio Buttons Management
		 */
		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(firstLineRadioButton);
		radioButtonGroup.add(secondLineRadioButton);

		firstLineRadioButton.addActionListener(new MyActionListener());
		secondLineRadioButton.addActionListener(new MyActionListener());
		firstLineRadioButton.setSelected(true);
		secondLineRadioButton.setSelected(false);

		hostnameJTextField.setEditable(false);
		hostnameJTextField.setEnabled(false);

		/*
		 * Build pane
		 */

		int returnCode = -1;
		while (returnCode < 0) {
			int result = JOptionPane.showConfirmDialog(null, dialogJPane,
					"Please Enter Neo4j Server Address",
					JOptionPane.OK_CANCEL_OPTION);

			returnCode = parseUserInformations(result);

			if (returnCode == 0) {
				System.exit(0);
			}
		}

	}

	private int parseUserInformations(int result) {
		if (result == JOptionPane.OK_OPTION) {
			/*
			 * Parse port number
			 */
			String portNumberText = portNumberJTextField.getText();
			boolean portNumberInError = false;
			if (portNumberText.matches("^[0-9]{1,5}$")) {
				int portNumberFromUser = Integer.parseInt(portNumberText);
				if (portNumberFromUser < 0 || portNumberFromUser > 65535) {
					portNumberInError = true;
				} else {
					portNumber = portNumberFromUser;
				}
			} else {
				portNumberInError = true;
			}

			/*
			 * Parse remaining stuff
			 */
			boolean ipAddressInError = false;
			boolean hostnameInError = false;
			if (firstLineRadioButton.isSelected()) {
				int add1 = Integer.parseInt(address1JTextField.getText());
				int add2 = Integer.parseInt(address2JTextField.getText());
				int add3 = Integer.parseInt(address3JTextField.getText());
				int add4 = Integer.parseInt(address4JTextField.getText());

				if (add1 < 0 || add1 > 255 || add2 < 0 || add2 > 255
						|| add3 < 0 || add3 > 255 || add4 < 0 || add4 > 255) {
					ipAddressInError = true;
				} else {
					try {
						ip_asString = address1JTextField.getText() + "."
								+ address2JTextField.getText() + "."
								+ address3JTextField.getText() + "."
								+ address4JTextField.getText();
						ip = InetAddress.getByName(address1JTextField.getText()
								+ "." + address2JTextField.getText() + "."
								+ address3JTextField.getText() + "."
								+ address4JTextField.getText());
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}

					if (!portNumberInError) {
						return 1;
					}
				}
			} else {
				String hostnameJTextFieldText=hostnameJTextField.getText();
				if (hostnameJTextFieldText.matches(IP_ADDRESS_REGEX) || hostnameJTextFieldText.matches(HOSTNAME_REGEX)) {
					try {
						ip_asString = hostnameJTextFieldText;
						ip = InetAddress.getByName(ip_asString);
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}

					if (!portNumberInError) {
						return 2;
					}
				} else {
					hostnameInError = true;
				}
			}

			String message = "The ";
			if (portNumberInError) {
				message += "port number";

				if (ipAddressInError || hostnameInError)
					message += " and ";
			}
			if (ipAddressInError) {
				message += "IP address";
			}
			if (hostnameInError) {
				message += "hostname";
			}

			if (portNumberInError && (ipAddressInError || hostnameInError)) {
				message += " are ";
			} else {
				message += " is ";
			}
			message += "invalid. Please Try Again!";
			JOptionPane.showMessageDialog(null, message, "Input Error",
					JOptionPane.ERROR_MESSAGE);
			return -1;
		} else {
			return 0;
		}
	}

	public InetAddress getIpAddress() {
		return ip;
	}

	public String getIpAddressAsString() {
		return ip_asString;
	}

	public String getPortNumberAsString() {
		return Integer.toString(portNumber);
	}

	public int getPortNumber() {
		return portNumber;
	}

	protected class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean veredict = false;
			if (e.getActionCommand().equals(
					firstLineRadioButton.getActionCommand())) {
				veredict = true;
			} else {
				veredict = false;
			}

			address1JTextField.setEditable(veredict);
			address1JTextField.setEnabled(veredict);

			address2JTextField.setEditable(veredict);
			address2JTextField.setEnabled(veredict);

			address3JTextField.setEditable(veredict);
			address3JTextField.setEnabled(veredict);

			address4JTextField.setEditable(veredict);
			address4JTextField.setEnabled(veredict);

			hostnameJTextField.setEditable(!veredict);
			hostnameJTextField.setEnabled(!veredict);
		}

	}
}
