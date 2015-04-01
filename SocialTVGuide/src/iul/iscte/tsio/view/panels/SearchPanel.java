package iul.iscte.tsio.view.panels;

import iul.iscte.tsio.utils.Labels;
import iul.iscte.tsio.view.SearchResultView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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
		searchText.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				String text = searchText.getText();
				if(text == null || text.length()==0){
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
		
		add(searchButton = new JButton(Labels.SEARCHBUTTON_SEARCH_PANEL.getValue()), BorderLayout.EAST);
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = searchText.getText();
			
				JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(SearchPanel.this);
				new SearchResultView(topFrame, text).setVisible(true);
				
				
			}
		});
	}

	public JButton getSearchButton(){
		return searchButton;
	}
}
