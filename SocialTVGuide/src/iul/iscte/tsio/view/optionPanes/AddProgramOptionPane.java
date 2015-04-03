package iul.iscte.tsio.view.optionPanes;

import iul.iscte.tsio.model.ProgramDAOImpl;
import iul.iscte.tsio.model.ProgramEntity;
import iul.iscte.tsio.utils.Labels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AddProgramOptionPane {
	private static final String TEXT_ONLY_REGEX = "^[a-zA-Z0-9\\s-_]*$";
	private static final String NUMBERS_ONLY_REGEX = "^[0-9]{1,3}$";

	private JTextField title, type, runtime, description, season,
			episodeNumber;
	private JRadioButton seriesJRadioButton, movieJRadioButton;
	private ProgramEntity newProgram;

	public AddProgramOptionPane() {
		Object[] buttonsLabels = new Object[] {
				Labels.ADDPROGRAMBUTTON_ADDPROGRAM_PANE.getValue(),
				Labels.CANCELBUTTON_ADDPROGRAM_PANE.getValue() };

		JPanel panel = buildPanel();
		boolean exit = false;
		while (!exit) {
			int result = JOptionPane.showOptionDialog(null, panel,
					Labels.TITLE_ADDPROGAM_PANE.getValue(),
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, buttonsLabels, buttonsLabels[0]);

			if (result == JOptionPane.OK_OPTION) {
				String returnMessage = parseProgramInformations();

				if (returnMessage == null) {
					System.out.print("Adding new program \""
							+ newProgram.getTitle() + "\"... ");

					long opResult = ProgramDAOImpl.getInstance().insertProgram(
							newProgram);
					System.out.println("opResult: " + opResult + " ");

					System.out.println(opResult > 0 ? "with success!"
							: "with errors!");

					if (opResult > 0) {
						String message = String
								.format(Labels.TEXT_SUCCESSADDINGPROGRAM_ADDPROGRAM_PANE
										.getValue(), newProgram.getTitle());
						JOptionPane
								.showMessageDialog(
										null,
										message,
										Labels.TITLE_SUCCESSADDINGPROGRAM_ADDPROGRAM_PANE
												.getValue(),
										JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,
								Labels.TEXT_ERRORADDINGPROGRAM_ADDPROGRAM_PANE
										.getValue(),
								Labels.TITLE_ERRORADDINGPROGRAM_ADDPROGRAM_PANE
										.getValue(), JOptionPane.ERROR_MESSAGE);
					}

					exit = true;
				} else {
					JOptionPane.showMessageDialog(null, returnMessage,
							Labels.TITLE_INVALIDADDINGPROGRAM_ADDPROGRAM_PANE
									.getValue(), JOptionPane.ERROR_MESSAGE);
				}
			} else {
				exit = true;
			}
		}
	}

	private JPanel buildPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 2));
		panel.add(new JLabel("Title: "));
		panel.add(title = new JTextField());
		panel.add(new JLabel("Type: "));
		panel.add(type = new JTextField());
		panel.add(new JLabel("Runtime: "));
		panel.add(runtime = new JTextField());

		movieJRadioButton = new JRadioButton("Movie");
		movieJRadioButton.addActionListener(new RadioButtonActionListener());
		movieJRadioButton.setSelected(true);

		seriesJRadioButton = new JRadioButton("Series");
		seriesJRadioButton.addActionListener(new RadioButtonActionListener());
		seriesJRadioButton.setSelected(false);

		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(movieJRadioButton);
		radioButtonGroup.add(seriesJRadioButton);

		panel.add(movieJRadioButton);
		panel.add(seriesJRadioButton);

		panel.add(new JLabel("Season: "));
		panel.add(season = new JTextField());
		season.setEnabled(false);
		season.setEditable(false);

		panel.add(new JLabel("Episode Number: "));
		panel.add(episodeNumber = new JTextField());
		episodeNumber.setEnabled(false);
		episodeNumber.setEditable(false);

		panel.add(new JLabel("Description: "));
		panel.add(description = new JTextField());

		return panel;
	}

	private class RadioButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean veredict = false;
			if (e.getActionCommand().equals(
					seriesJRadioButton.getActionCommand())) {
				veredict = true;
			} else {
				veredict = false;
			}

			season.setEditable(veredict);
			season.setEnabled(veredict);

			episodeNumber.setEditable(veredict);
			episodeNumber.setEnabled(veredict);
		}

	}

	private String parseProgramInformations() {
		String titleStr = title.getText();
		String typeStr = type.getText();
		String runtimeStr = runtime.getText();
		String descriptionStr = description.getText();
		String seasonStr = season.getText();
		String episodeNumberStr = episodeNumber.getText();

		int runtimeVal = 0;

		if (!titleStr.matches(TEXT_ONLY_REGEX)) {
			return "The inserted title is invalid. Please correct it and try again!";
		}

		if (!typeStr.matches(TEXT_ONLY_REGEX)) {
			return "The inserted type is invalid. Please correct it and try again!";
		}

		if (!runtimeStr.matches(NUMBERS_ONLY_REGEX)) {
			return "The inserted runtime value is invalid. Please correct it and try again!";
		} else {
			runtimeVal = Integer.parseInt(runtimeStr);
		}

		if (!descriptionStr.matches(TEXT_ONLY_REGEX)) {
			return "The inserted description is invalid. Please correct it and try again!";
		}

		if (seriesJRadioButton.isSelected()) {
			int seasonVal = 0;
			int episodeNumberVal = 0;

			if (!seasonStr.matches(NUMBERS_ONLY_REGEX)) {
				return "The inserted season number is invalid. Please correct it and try again!";
			} else {
				seasonVal = Integer.parseInt(seasonStr);
			}

			if (!episodeNumberStr.matches(NUMBERS_ONLY_REGEX)) {
				return "The inserted episode number is invalid. Please correct it and try again!";
			} else {
				episodeNumberVal = Integer.parseInt(episodeNumberStr);
			}

			newProgram = new ProgramEntity(titleStr, typeStr, runtimeVal,
					descriptionStr, seasonVal, episodeNumberVal);

			return null;
		} else {
			newProgram = new ProgramEntity(titleStr, typeStr, runtimeVal,
					descriptionStr);

			return null;
		}
	}
}
