package com.github.raphaelpanta.katabankocr.ui;

import static com.github.raphaelpanta.katabankocr.constants.Constants.INPUT_LABEL_NAME;
import static com.github.raphaelpanta.katabankocr.constants.Constants.INPUT_LABEL_TEXT;
import static com.github.raphaelpanta.katabankocr.constants.Constants.INPUT_TEXT_AREA_NAME;
import static com.github.raphaelpanta.katabankocr.constants.Constants.OUTPUT_LABEL_NAME;
import static com.github.raphaelpanta.katabankocr.constants.Constants.OUTPUT_LABEL_TEXT;
import static com.github.raphaelpanta.katabankocr.constants.Constants.OUTPUT_TEXT_AREA_NAME;
import static com.github.raphaelpanta.katabankocr.constants.Constants.SCANNER_BUTTON_NAME;
import static com.github.raphaelpanta.katabankocr.constants.Constants.SCANNER_BUTTON_TEXT;
import static com.github.raphaelpanta.katabankocr.constants.Constants.SELECT_FILE_BUTTON_NAME;
import static com.github.raphaelpanta.katabankocr.constants.Constants.SELECT_FILE_BUTTON_TEXT;
import static com.github.raphaelpanta.katabankocr.ui.builders.JButtonBuilder.newButton;
import static com.github.raphaelpanta.katabankocr.ui.builders.JLabelBuilder.newJLabel;
import static com.github.raphaelpanta.katabankocr.ui.builders.JTextAreaBuilder.newTextArea;
import static java.awt.FlowLayout.CENTER;
import static java.awt.FlowLayout.LEFT;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.github.raphaelpanta.katabankocr.api.NonBlokingActionListener;
import com.github.raphaelpanta.katabankocr.config.AccountProps;
import com.github.raphaelpanta.katabankocr.config.ScannerProps;
import com.github.raphaelpanta.katabankocr.constants.Constants;

public class UIFrame extends JFrame {

	private static final long serialVersionUID = 4752440571780079564L;

	private JTextArea outputTextArea;
	private JTextArea inputTextArea;

	private final JButton scannerButton = newButton()
			.named(SCANNER_BUTTON_NAME).withText(SCANNER_BUTTON_TEXT)
			.disabled().build();

	private final JButton selectFileButton = newButton()
			.named(SELECT_FILE_BUTTON_NAME).withText(SELECT_FILE_BUTTON_TEXT)
			.enabled().build();

	@Inject
	public UIFrame(final AccountProps accProps, final ScannerProps scnProps) {

		outputTextArea = newTextArea().named(OUTPUT_TEXT_AREA_NAME)
				.withRows(accProps.lines()).withColumns(accProps.lineLegth())
				.nonEditable().build();
		inputTextArea = newTextArea().named(INPUT_TEXT_AREA_NAME)
				.withRows(scnProps.lines())
				.withColumns(scnProps.lineLength()).nonEditable().build();
		configFrame(Constants.APP_NAME, Constants.APP_TITLE);

		JPanel panel1 = new JPanel(new FlowLayout(LEFT));
		JLabel inputLabel = newJLabel().named(INPUT_LABEL_NAME)
				.withText(INPUT_LABEL_TEXT).build();
		inputLabel.setPreferredSize(new Dimension(100, 30));
		panel1.add(inputLabel);
		panel1.add(selectFileButton);
		panel1.setPreferredSize(new Dimension(700, 35));
		add(panel1);

		JPanel panel2 = new JPanel(new FlowLayout(LEFT));
		JScrollPane scrollablePane = new JScrollPane(inputTextArea);
		scrollablePane.setPreferredSize(new Dimension(700, 200));
		panel2.add(scrollablePane);
		panel2.setPreferredSize(new Dimension(700, 200));
		add(panel2);

		JPanel panel3 = new JPanel(new FlowLayout(LEFT));
		JLabel outputLabel = newJLabel().named(OUTPUT_LABEL_NAME)
				.withText(OUTPUT_LABEL_TEXT).build();
		outputLabel.setPreferredSize(new Dimension(100, 30));
		panel3.add(outputLabel);
		panel3.add(scannerButton);
		panel3.setPreferredSize(new Dimension(700, 35));
		add(panel3);

		JPanel panel4 = new JPanel(new FlowLayout(LEFT));
		JScrollPane scrollableOutputPane = new JScrollPane(outputTextArea);
		scrollableOutputPane.setPreferredSize(new Dimension(700, 200));
		panel4.add(scrollableOutputPane);
		add(panel4);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}

	private void configFrame(final String name, final String title) {
		setName(name);
		setTitle(title);
		setLayout(new FlowLayout(CENTER));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800, 600));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * raphaelpantaleao.katabankocr.ui.ScannerFileUI#appendOutput(java.lang.
	 * String)
	 */
	public void appendOutput(String string) {
		outputTextArea.append(string);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * raphaelpantaleao.katabankocr.ui.ScannerFileUI#appendInput(java.lang.String
	 * )
	 */
	public void appendInput(String string) {
		boolean stringIsNotEmpty = string != null && !string.isEmpty();
		if (stringIsNotEmpty) {
			inputTextArea.append(string);
			scannerButton.setEnabled(true);
		}

	}

	@Inject
	public void addSelectFileListener(
			@Named("selectFileListener") ActionListener selectFileListener) {
		this.selectFileButton.addActionListener(NonBlokingActionListener
				.asNonBlocking(selectFileListener));
	}

	@Inject
	public void addScanFileListener(
			@Named("scanFileListener") ActionListener scanFileListener) {
		this.scannerButton.addActionListener(scanFileListener);
	}

}
