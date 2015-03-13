package iul.iscte.tsio.view.search;

import iul.iscte.tsio.utils.Labels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class SearchPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField searchText;
	private JButton searchButton;

	public SearchPanel() {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(5, 5, 5, 5) );
		add(searchText = new JTextField(), BorderLayout.CENTER);
		add(searchButton = new JButton(Labels.SEARCHLABEL.getValue()), BorderLayout.EAST);
		
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = searchText.getText();
				if(text.length() > 0){
					JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(SearchPanel.this);
					new SearchResultView(topFrame, text).setVisible(true);
					
				}
				
			}
		});
	}

}
