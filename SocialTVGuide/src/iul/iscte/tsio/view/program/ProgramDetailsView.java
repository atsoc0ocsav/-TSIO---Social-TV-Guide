package iul.iscte.tsio.view.program;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import iul.iscte.tsio.model.ProgramDAOImpl;
import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.model.UserEntity;
import iul.iscte.tsio.utils.Labels;
import iul.iscte.tsio.view.UsersView;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ProgramDetailsView extends JDialog {

	private static final long serialVersionUID = 1L;

	private JButton close;
	private JButton watch;
	private JButton like;

	public ProgramDetailsView(JFrame topFrame, final ProgramEntity program,
			final UserEntity loggedUser) {
		super(topFrame);
		setSize(400, 500);
		setTitle("Program Details");
		setLayout(new BorderLayout());
		JPanel aux = new JPanel();
		add(aux, BorderLayout.SOUTH);

		aux.add(watch = new JButton(Labels.WATCH.getValue()));
		watch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProgramDAOImpl.getInstance().createWatchedRelationship(loggedUser, program);
				watch.setEnabled(false);
				like.setEnabled(true);
				UsersView.getInstance().getUserRecommendations().refresh();
			}
		});

		aux.add(like = new LikeButton(program, loggedUser));
		aux.add(close = new JButton(Labels.CLOSE.getValue()));
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProgramDetailsView.this.dispose();

			}
		});

		if (program.getType().equals("Documentary Series")
				|| program.getType().equals("TVSeries")) {
			add(buildOtherProgramsDetails(program), BorderLayout.CENTER);
		} else {
			add(buildMovieDetails(program), BorderLayout.CENTER);
		}
		
		if(ProgramDAOImpl.getInstance().hasUserWatchedProgram(loggedUser, program)){
			watch.setEnabled(false);
		}else{
			like.setEnabled(false);
		}

	}

	private JComponent buildOtherProgramsDetails(ProgramEntity value) {
		JPanel details = new JPanel();
		GroupLayout layout = new GroupLayout(details);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		details.setLayout(layout);

		JLabel title = new JLabel(value.getTitle());
		JLabel description = new JLabel(value.getDescription());
		JLabel type = new JLabel(value.getType());
		JLabel runtime = new JLabel(value.getRuntime() + "");
		JLabel season = new JLabel(value.getSeason() + "");
		JLabel episode = new JLabel(value.getEpisodeNumber() + "");

		JLabel titleLbl;
		JLabel descriptioLbl;
		JLabel typeLbl;
		JLabel runtimeLbl;
		JLabel seasonLbl;
		JLabel episodeLbl;

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addComponent(titleLbl = new JLabel("Title :"))
								.addComponent(
										descriptioLbl = new JLabel(
												"Description :"))
								.addComponent(typeLbl = new JLabel("Type :"))
								.addComponent(
										runtimeLbl = new JLabel("Runtime :"))
								.addComponent(
										seasonLbl = new JLabel("Season :"))
								.addComponent(
										episodeLbl = new JLabel("Episode :")))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addComponent(title).addComponent(description)
								.addComponent(type).addComponent(runtime)
								.addComponent(season).addComponent(episode)));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(titleLbl).addComponent(title))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(descriptioLbl)
								.addComponent(description))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(typeLbl).addComponent(type))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(runtimeLbl).addComponent(runtime))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(seasonLbl).addComponent(season))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(episodeLbl).addComponent(episode)));

		JScrollPane scroll = new JScrollPane(details);
		return scroll;

	}

	private JComponent buildMovieDetails(ProgramEntity value) {
		JPanel details = new JPanel();
		GroupLayout layout = new GroupLayout(details);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		details.setLayout(layout);

		JLabel title = new JLabel(value.getTitle());
		JLabel description = new JLabel(value.getDescription());
		JLabel type = new JLabel(value.getType());
		JLabel runtime = new JLabel(value.getRuntime() + "");

		JLabel titleLbl;
		JLabel descriptioLbl;
		JLabel typeLbl;
		JLabel runtimeLbl;

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addComponent(titleLbl = new JLabel("Title :"))
								.addComponent(
										descriptioLbl = new JLabel(
												"Description :"))
								.addComponent(typeLbl = new JLabel("Type :"))
								.addComponent(
										runtimeLbl = new JLabel("Runtime :")))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addComponent(title).addComponent(description)
								.addComponent(type).addComponent(runtime)));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(titleLbl).addComponent(title))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(descriptioLbl)
								.addComponent(description))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(typeLbl).addComponent(type))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(runtimeLbl).addComponent(runtime)));

		JScrollPane scroll = new JScrollPane(details);
		return scroll;
	}

}
