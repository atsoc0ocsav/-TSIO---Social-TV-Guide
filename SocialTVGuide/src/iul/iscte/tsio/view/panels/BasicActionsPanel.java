package iul.iscte.tsio.view.panels;

import iul.iscte.tsio.utils.Labels;
import iul.iscte.tsio.view.optionPanes.AddProgramOptionPane;
import iul.iscte.tsio.view.optionPanes.AddUserOptionPane;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		addNewUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddUserOptionPane();
			}
		});
		add(addNewProgram = new JButton(Labels.ADDPROGRAMBUTTON.getValue()));
		addNewProgram.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddProgramOptionPane();
			}
		});
	}
}
