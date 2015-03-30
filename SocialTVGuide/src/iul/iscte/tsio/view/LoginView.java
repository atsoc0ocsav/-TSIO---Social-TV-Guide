package iul.iscte.tsio.view;

import iul.iscte.tsio.server.Server;
import iul.iscte.tsio.server.ServerObservator;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LoginView extends JFrame implements ServerObservator {
	private static final long serialVersionUID = -7554010640119485816L;

	private static final String IP_ADDRESS_REGEX = "^([1-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3}$";
	private static final String HOSTNAME_REGEX = "^(([a-zA-Z]|[a-zA-Z][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z]|[A-Za-z][A-Za-z0-9\\-]*[A-Za-z0-9])$";
	private static final String USERNAME_REGEX = "[A-Za-z0-9._+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
	private static final String IP_ELEMENT_REGEX = "^(([0-1]?[0-9]?[0-9])|([2][0-4][0-9])|(25[0-4]))$";

	private String DEFAULT_IP = "52.10.21.89";
	private String DEFAULT_PORT = "7474";
	private String DEFAULT_USERNAME = "techsupport@lemonparty.com";

	private InetAddress ip = null;
	private String ip_asString = "";
	private int portNumber = -1;
	private String username = "";

	private JRadioButton firstLineRadioButton;
	private JRadioButton secondLineRadioButton;

	private JTextField address1JTextField, address2JTextField,
			address3JTextField, address4JTextField;
	private JTextField hostnameJTextField;
	private JTextField portNumberJTextField;
	private JTextField usernameJTextField;

	private JTextArea messageArea;

	private JButton cancelButton;
	private JButton connectButton;
	private JSeparator separator;

	private boolean connected = false;
	private boolean authenticated = false;
	
	private Server observed;

	public LoginView() {
		buildGUI();

		observed=Server.getInstance();
		observed.addObserver(this);

		setVisible(true);
	}

	private void buildGUI() {
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(400, 320);
		setTitle("Social TV Guide - Connect to Server");

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			System.err
					.println("Not able to set LookAndFeel for the current OS");
		}

		separator = new JSeparator();
		separator.setBounds(0, 163, 394, 9);
		getContentPane().add(separator);

		buildCentralPane();
		buildSouthPane();

		addMessage("Welcome to Social TV Guide!\n");
	}

	private void buildCentralPane() {
		JPanel centralPane = new JPanel(null);
		centralPane.setLocation(0, 0);
		centralPane.setSize(394, 154);

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
				FlowLayout.CENTER, 6, 5));
		addressAndLabelJPane.setBounds(146, 10, 175, 29);
		addressAndLabelJPane.add(address1JTextField);
		addressAndLabelJPane.add(new JLabel("."));
		addressAndLabelJPane.add(address2JTextField);
		addressAndLabelJPane.add(new JLabel("."));
		addressAndLabelJPane.add(address3JTextField);
		addressAndLabelJPane.add(new JLabel("."));
		addressAndLabelJPane.add(address4JTextField);
		centralPane.add(addressAndLabelJPane);

		/*
		 * Hostname introduction
		 */
		hostnameJTextField = new JTextField(15);
		hostnameJTextField.setLocation(150, 50);
		hostnameJTextField.setSize(168, 20);
		hostnameJTextField.setText(DEFAULT_IP);
		hostnameJTextField.setEditable(false);
		hostnameJTextField.setEnabled(false);
		centralPane.add(hostnameJTextField);

		/*
		 * Port introduction
		 */
		portNumberJTextField = new JTextField(5);
		portNumberJTextField.setLocation(150, 81);
		portNumberJTextField.setSize(50, 20);
		portNumberJTextField.setText(DEFAULT_PORT);

		JLabel portLabel = new JLabel("Port");
		portLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		portLabel.setLocation(89, 81);
		portLabel.setSize(39, 20);

		centralPane.add(portLabel);
		centralPane.add(portNumberJTextField);

		/*
		 * JRadio Buttons Management
		 */
		firstLineRadioButton = new JRadioButton("IP Address");
		firstLineRadioButton.addActionListener(new RadioButtonActionListener());
		firstLineRadioButton.setSelected(true);
		firstLineRadioButton.setBounds(57, 14, 82, 20);

		secondLineRadioButton = new JRadioButton("Hostname");
		secondLineRadioButton
				.addActionListener(new RadioButtonActionListener());
		secondLineRadioButton.setSelected(false);
		secondLineRadioButton.setBounds(58, 50, 82, 20);

		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(firstLineRadioButton);
		radioButtonGroup.add(secondLineRadioButton);

		centralPane.add(firstLineRadioButton);
		centralPane.add(secondLineRadioButton);

		/*
		 * Username introduction
		 */
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		usernameLabel.setLocation(58, 130);
		usernameLabel.setSize(70, 20);

		usernameJTextField = new JTextField();
		usernameJTextField.setLocation(150, 130);
		usernameJTextField.setSize(168, 20);
		usernameJTextField.setText(DEFAULT_USERNAME);

		centralPane.add(usernameLabel);
		centralPane.add(usernameJTextField);

		getContentPane().add(centralPane);
	}

	private void buildSouthPane() {
		JPanel southPanel = new JPanel();
		southPanel.setBounds(0, 174, 394, 118);
		southPanel.setLayout(null);

		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setBounds(0, 0, 394, 33);
		connectButton = new JButton("Connect");
		connectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				connectButtonAction();
			}
		});
		buttonPanel.add(connectButton);

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		buttonPanel.add(cancelButton);

		messageArea = new JTextArea();
		messageArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
		messageArea.setEnabled(false);
		messageArea.setEditable(false);
		messageArea.setBounds(0, 33, 314, 65);
		JScrollPane scrollPane = new JScrollPane(messageArea);
		scrollPane.setLocation(0, 36);
		scrollPane.setSize(394, 82);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		southPanel.add(buttonPanel);
		southPanel.add(scrollPane);
		getContentPane().add(southPanel);
	}

	private int parseUserInformations() {
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
			portNumber = -1;
			portNumberInError = true;
		}

		/*
		 * Parse Username (just check if it is valid
		 */
		String usernameTempText = usernameJTextField.getText();
		boolean usernameInError = false;
		if (usernameTempText.matches(USERNAME_REGEX)) {
			username = usernameTempText;
		} else {
			username = null;
			usernameInError = true;
		}

		/*
		 * Parse remaining stuff
		 */
		boolean ipAddressInError = false;
		boolean hostnameInError = false;
		if (firstLineRadioButton.isSelected()) {
			String address1JTextFieldText = address1JTextField.getText();
			String address2JTextFieldText = address2JTextField.getText();
			String address3JTextFieldText = address3JTextField.getText();
			String address4JTextFieldText = address4JTextField.getText();

			if (address1JTextFieldText.matches(IP_ELEMENT_REGEX)
					&& address2JTextFieldText.matches(IP_ELEMENT_REGEX)
					&& address3JTextFieldText.matches(IP_ELEMENT_REGEX)
					&& address4JTextFieldText.matches(IP_ELEMENT_REGEX)) {

				try {
					ip_asString = address1JTextFieldText + "."
							+ address2JTextFieldText + "."
							+ address3JTextFieldText + "."
							+ address4JTextFieldText;
					ip = InetAddress.getByName(ip_asString);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			} else {
				ipAddressInError = true;
				ip_asString = null;
				ip = null;
			}
		} else {
			String hostnameJTextFieldText = hostnameJTextField.getText();
			if (hostnameJTextFieldText.matches(IP_ADDRESS_REGEX)
					|| hostnameJTextFieldText.matches(HOSTNAME_REGEX)) {
				try {
					ip_asString = hostnameJTextFieldText;
					ip = InetAddress.getByName(ip_asString);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			} else {
				ip_asString = null;
				ip = null;
				hostnameInError = true;
			}
		}

		if (portNumberInError || ipAddressInError || hostnameInError
				|| usernameInError) {
			String message = "The ";

			if (portNumberInError) {
				message += "port number";

				int val = (ipAddressInError ? 1 : 0)
						+ (hostnameInError ? 1 : 0) + (usernameInError ? 1 : 0);
				if (val > 1) {
					message += ", ";
				} else {
					if (val == 1) {
						message += " and ";
					}
				}
			}

			if (usernameInError) {
				message += "username";

				int val = (ipAddressInError ? 1 : 0)
						+ (hostnameInError ? 1 : 0);
				if (val >= 1) {
					message += " and ";
				}
			}

			if (ipAddressInError) {
				message += "IP address";
			}

			if (hostnameInError) {
				message += "hostname";
			}

			int val = (portNumberInError ? 1 : 0) + (ipAddressInError ? 1 : 0)
					+ (hostnameInError ? 1 : 0) + (usernameInError ? 1 : 0);
			if (val > 1) {
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

	public String getUsername() {
		return username;
	}

	private class RadioButtonActionListener implements ActionListener {
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

	public void addMessage(String message) {
		String str = messageArea.getText();
		str += message;
		messageArea.setText(str);
	}

	@Override
	public void showMessage(String message) {
		messageArea.setText(messageArea.getText());
	}

	@Override
	public void clearMessages() {
		messageArea.setText("");
	}

	private void connectButtonAction() {
		if (parseUserInformations() >= 0) {
			lockIPHostnameInput();
			if (!observed.isConnected()) {
				if (connectToServer() < 0) {
					boolean veredict = false;
					if (firstLineRadioButton.isSelected()) {
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

					portNumberJTextField.setEditable(true);
					portNumberJTextField.setEnabled(true);

					firstLineRadioButton.setEnabled(true);
					secondLineRadioButton.setEnabled(true);
				}
			}

			if (!observed.isAuthenticated() && observed.isConnected()) {
				authenticateUser();
			}
		}
	}

	public int connectToServer() {
		String serverAddress = "http://" + ip_asString + ":" + portNumber
				+ "/db/data/";

		addMessage("Trying to connect to server " + ip_asString + ":"
				+ portNumber + "...\n");

		try {
			if (!observed.isConnected()) {
				observed.login(serverAddress);
			} else {
				addMessage("Already connected to server!");
			}

			return 0;
		} catch (com.sun.jersey.api.client.ClientHandlerException e) {
			// When connection time out occurs (usually bad IP address/ hostname
			// or port
			addMessage("Unable to connect. Please check address and port!");
			return -1;
		}
	}

	@Override
	public void authenticateUser() {
		addMessage("Authenticating user..... ");
		observed.logUser(username);
	}

	public void lockIPHostnameInput() {
		address1JTextField.setEditable(false);
		address1JTextField.setEnabled(false);
		address2JTextField.setEditable(false);
		address2JTextField.setEnabled(false);
		address3JTextField.setEditable(false);
		address3JTextField.setEnabled(false);
		address4JTextField.setEditable(false);
		address4JTextField.setEnabled(false);

		hostnameJTextField.setEditable(false);
		hostnameJTextField.setEnabled(false);

		portNumberJTextField.setEditable(false);
		portNumberJTextField.setEnabled(false);

		firstLineRadioButton.setEnabled(false);
		secondLineRadioButton.setEnabled(false);
	}

	public void unlockIPHostnameInput() {
		boolean veredict = false;
		if (firstLineRadioButton.isSelected()) {
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

		portNumberJTextField.setEditable(true);
		portNumberJTextField.setEnabled(true);

		firstLineRadioButton.setEnabled(true);
		secondLineRadioButton.setEnabled(true);
	}

	@Override
	public void launchGUI() {
		UsersView.getInstance().setVisible(true);
	}
}
