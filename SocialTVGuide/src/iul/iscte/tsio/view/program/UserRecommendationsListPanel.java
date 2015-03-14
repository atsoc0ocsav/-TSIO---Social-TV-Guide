package iul.iscte.tsio.view.program;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import iul.iscte.tsio.controller.ProgramsController;
import iul.iscte.tsio.interfaces.Refreshable;
import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.model.UserEntity;
import iul.iscte.tsio.utils.Labels;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class UserRecommendationsListPanel extends JPanel implements Refreshable{

	private static final long serialVersionUID = 1L;
	private JButton moreDetails;
	private JScrollPane scrollPane;
	private JList<ProgramEntity> programList;
	private UserEntity loggedUser;
	private DefaultListModel<ProgramEntity> listModel;

	public UserRecommendationsListPanel(final UserEntity loggedUser) {
		this.loggedUser = loggedUser;
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(5, 5, 5, 5) );
		add(moreDetails = new JButton(Labels.PROGRAMMOREDETAILS.getValue()), BorderLayout.SOUTH);
		
		listModel = new DefaultListModel<ProgramEntity>();
		programList = new JList<ProgramEntity>(listModel);
		programList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane = new JScrollPane(programList);
		add(scrollPane, BorderLayout.CENTER);
		
		moreDetails.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ProgramEntity value = programList.getSelectedValue();
				if(value != null){
					JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(UserRecommendationsListPanel.this);
					new ProgramDetailsView(topFrame, value, loggedUser).setVisible(true);;
				}
				
			}
		});
		
		refresh();
	}

	@Override
	public void refresh() {
		listModel.clear();
		ArrayList<ProgramEntity> auxData = ProgramsController.getInstance().getRecommendShowsForUser(loggedUser);
		for (ProgramEntity programEntity : auxData) {
			listModel.addElement(programEntity);
		}
		
	}
}
