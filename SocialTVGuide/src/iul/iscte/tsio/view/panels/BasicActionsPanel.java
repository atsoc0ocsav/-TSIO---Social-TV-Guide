package iul.iscte.tsio.view.panels;

import iul.iscte.tsio.utils.Labels;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BasicActionsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton addNewUser;
	private JButton addNewProgram;

	public BasicActionsPanel() {
		setLayout(new FlowLayout());
		setBorder(new EmptyBorder(5, 5, 5, 5) );
		add(addNewUser = new JButton(Labels.ADDUSERBUTTON.getValue()));
		add(addNewProgram = new JButton(Labels.ADDPROGRAMBUTTON.getValue()));
	}
}
