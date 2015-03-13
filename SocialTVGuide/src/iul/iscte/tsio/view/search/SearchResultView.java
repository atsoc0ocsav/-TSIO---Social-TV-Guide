package iul.iscte.tsio.view.search;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.utils.Labels;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SearchResultView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton closeButton;
	private JButton moreDetailsButton;
	private JList<ProgramEntity> results;
	private JScrollPane scrollPane;

	public SearchResultView(JFrame frame, String text) {
		super(frame);
		setTitle(Labels.SEARCHRESULTDIALOGTITLE.getValue() + " for \"" + text +  "\"");
		setSize(400, 500);

		setLayout(new BorderLayout());

		JPanel aux = new JPanel();
		aux.add(moreDetailsButton = new JButton(Labels.PROGRAMMOREDETAILS.getValue()));
		aux.add(closeButton = new JButton(Labels.CLOSE.getValue()));
		closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SearchResultView.this.dispose();
				
			}
		});

		add(aux, BorderLayout.SOUTH);
		
		// TODO falta ir buscar os dados. saber qual é o metodo
		results = new JList<ProgramEntity>();
		scrollPane = new JScrollPane(results);
		add(scrollPane, BorderLayout.CENTER);

	}
}
