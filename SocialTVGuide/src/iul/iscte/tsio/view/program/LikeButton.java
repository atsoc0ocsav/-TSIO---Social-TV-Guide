package iul.iscte.tsio.view.program;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import iul.iscte.tsio.model.ProgramDAOImpl;
import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.model.UserEntity;
import iul.iscte.tsio.utils.Labels;

import javax.swing.JButton;

public class LikeButton extends JButton {

	private static final long serialVersionUID = 1L;

	private ProgramEntity program;

	private UserEntity loggedUser;

	private ActionListener likeProgram = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			ProgramDAOImpl.getInstance().createLikedRelationship(loggedUser, program);

			removeActionListener(this);
			addActionListener(unLikeProgram);
			setText(Labels.UNLIKE.getValue());
		}
	};

	private ActionListener unLikeProgram = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			ProgramDAOImpl.getInstance().deleteLikedRelationship(loggedUser, program);

			removeActionListener(this);
			addActionListener(likeProgram);
			setText(Labels.LIKE.getValue());

		}
	};

	public LikeButton(ProgramEntity program, UserEntity loggedUser) {
		this.program = program;
		this.loggedUser = loggedUser;
		boolean likeResult = ProgramDAOImpl.getInstance().hasUserLikedProgram(
				loggedUser, program);
		if (likeResult) {
			setText(Labels.UNLIKE.getValue());
			addActionListener(unLikeProgram);
		} else {
			setText(Labels.LIKE.getValue());
			addActionListener(likeProgram);
		}
	}
}
