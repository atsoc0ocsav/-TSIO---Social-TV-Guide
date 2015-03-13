package iul.iscte.tsio.view.program;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.utils.Labels;
import iul.iscte.tsio.view.search.SearchResultView;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ProgramDetails extends JDialog {

	private static final long serialVersionUID = 1L;

	private JButton close;
	private JButton watch;
	private JButton like;

	public ProgramDetails(JFrame topFrame, ProgramEntity value) {
		super(topFrame);
		setLayout(new BorderLayout());
		JPanel aux = new JPanel();
		add(aux, BorderLayout.SOUTH);

		aux.add(watch = new JButton(Labels.WATCH.getValue()));
		aux.add(like = new JButton(Labels.LIKE.getValue()));
		aux.add(close = new JButton(Labels.CLOSE.getValue()));
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProgramDetails.this.dispose();

			}
		});

	}

}
