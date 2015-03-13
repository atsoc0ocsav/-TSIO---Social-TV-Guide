package iul.iscte.tsio.view.program;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

import iul.iscte.tsio.controller.ProgramsController;
import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.model.UserEntity;
import iul.iscte.tsio.utils.Labels;
import iul.iscte.tsio.view.search.SearchPanel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class UserRecommendationsListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton moreDetails;
	private JScrollPane scrollPane;
	private JList<ProgramEntity> programList;

	public UserRecommendationsListPanel(UserEntity loggedUser) {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(5, 5, 5, 5) );
		add(moreDetails = new JButton(Labels.PROGRAMMOREDETAILS.getValue()), BorderLayout.SOUTH);
		
		// TODO - analisar melhor esta solução
		ArrayList<ProgramEntity> auxData = ProgramsController.getInstance().getRecommendShowsForUser(loggedUser);
		Vector<ProgramEntity> data = new Vector<ProgramEntity>();
		for (ProgramEntity programEntity : auxData) {
			data.add(programEntity);
		}
		
		programList = new JList<ProgramEntity>(data);
		programList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane = new JScrollPane(programList);
		add(scrollPane, BorderLayout.CENTER);
		
		moreDetails.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ProgramEntity value = programList.getSelectedValue();
				if(value != null){
					JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(UserRecommendationsListPanel.this);
					new ProgramDetails(topFrame, value).setVisible(true);;
				}
				
			}
		});

	}
}
