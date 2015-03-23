package iul.iscte.tsio.view.search;

import iul.iscte.tsio.controller.ProgramsController;
import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.server.Server;
import iul.iscte.tsio.utils.Labels;
import iul.iscte.tsio.view.program.ProgramDetailsView;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SearchResultView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton closeButton;
	private JButton moreDetailsButton;
	private JList<ProgramEntity> results;
	private JScrollPane scrollPane;

	public SearchResultView(final JFrame frame, String text) {
		super(frame);
		setTitle(Labels.SEARCHRESULTDIALOGTITLE.getValue() + " for \"" + text
				+ "\"");
		setSize(400, 500);

		setLayout(new BorderLayout());

		JPanel aux = new JPanel();
		aux.add(moreDetailsButton = new JButton(Labels.PROGRAMMOREDETAILS
				.getValue()));
		moreDetailsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProgramEntity program = results.getSelectedValue();
				if (program != null) {
					new ProgramDetailsView(frame, program, Server.getInstance()
							.getLoggedUser()).setVisible(true);
					;
				}

			}
		});
		aux.add(closeButton = new JButton(Labels.CLOSE.getValue()));
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SearchResultView.this.dispose();

			}
		});

		add(aux, BorderLayout.SOUTH);

		List<ProgramEntity> list = ProgramsController.getInstance()
				.getProgramsWithRegex(text);
		DefaultListModel<ProgramEntity> listModel = new DefaultListModel<ProgramEntity>();
		for (ProgramEntity programEntity : list) {
			listModel.addElement(programEntity);
		}
		results = new JList<ProgramEntity>(listModel);
		scrollPane = new JScrollPane(results);
		add(scrollPane, BorderLayout.CENTER);
		int count = listModel.getSize();
		if (count == 0) {
			add(new JLabel("No results found"), BorderLayout.NORTH);
		} else {
			add(new JLabel(count + " result(s) found"), BorderLayout.NORTH);
		}

	}
}
