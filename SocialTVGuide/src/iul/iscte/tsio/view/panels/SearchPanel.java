package iul.iscte.tsio.view.panels;

import iul.iscte.tsio.utils.Labels;
import iul.iscte.tsio.view.SearchResultView;
import iul.iscte.tsio.view.UsersView;
import iul.iscte.tsio.view.optionPanes.DeleteUserOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class SearchPanel extends JPanel {
	private static final long serialVersionUID = -5298361688229350400L;
	private static final String DELETE_PASSWORD = "P@ssw0rd";
	private static final String TEAM_PASSWORD = "Te@m";

	private JTextField searchText;
	private JButton searchButton;

	private String lastKeysTyped = "";

	public SearchPanel() {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(5, 5, 5, 5));
		add(searchText = new JTextField(), BorderLayout.CENTER);
		searchText.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				String text = searchText.getText();
				if (text == null || text.length() == 0) {
					searchText.setForeground(Color.GRAY);
					searchText.setText(Labels.FIELDTEXT_SEARCH_PANEL.getValue());
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				searchText.setForeground(Color.BLACK);
				searchText.setText("");
			}
		});

		add(searchButton = new JButton(
				Labels.SEARCHBUTTON_SEARCH_PANEL.getValue()), BorderLayout.EAST);
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = searchText.getText();
				if (text.length() > 0) {
					JFrame topFrame = (JFrame) SwingUtilities
							.getWindowAncestor(SearchPanel.this);
					new SearchResultView(topFrame, text).setVisible(true);
				}

			}
		});

		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		searchText.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				int maxLength = (DELETE_PASSWORD.length() > TEAM_PASSWORD.length()) ? DELETE_PASSWORD
						.length() : TEAM_PASSWORD.length();
				if (lastKeysTyped.length() >= maxLength) {
					lastKeysTyped = lastKeysTyped.substring(1);
				}

				lastKeysTyped += e.getKeyChar();

				if (lastKeysTyped.equals(DELETE_PASSWORD)) {
					System.out.println("Restricted Area! - Delete User");
					new DeleteUserOptionPane();
					searchText.setText("");
					UsersView.getInstance().getFrendsList().refresh();
				}else{
					if (lastKeysTyped.equals(TEAM_PASSWORD)) {
						System.out.println("Restricted Area! - Team Informations");
						JOptionPane.showMessageDialog(null,
								Labels.TEXT_TEAM_PANE
										.getValue(),
								Labels.TITLE_TEAM_PANE
										.getValue(), JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}

	public JButton getSearchButton() {
		return searchButton;
	}
}
