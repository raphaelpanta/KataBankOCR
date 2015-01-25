package raphaelpantaleao.katabankocr.ui;

import static java.awt.FlowLayout.CENTER;
import static java.awt.FlowLayout.LEFT;
import static raphaelpantaleao.katabanckocr.constants.Constants.INPUT_LABEL_NAME;
import static raphaelpantaleao.katabanckocr.constants.Constants.INPUT_LABEL_TEXT;
import static raphaelpantaleao.katabanckocr.constants.Constants.INPUT_TEXT_AREA_NAME;
import static raphaelpantaleao.katabanckocr.constants.Constants.MAX_ACCOUNT_LINES;
import static raphaelpantaleao.katabanckocr.constants.Constants.MAX_ACCOUNT_LINE_LENGTH;
import static raphaelpantaleao.katabanckocr.constants.Constants.MAX_SCANNER_LINES;
import static raphaelpantaleao.katabanckocr.constants.Constants.MAX_SCANNER_LINE_LENGTH;
import static raphaelpantaleao.katabanckocr.constants.Constants.OUTPUT_LABEL_NAME;
import static raphaelpantaleao.katabanckocr.constants.Constants.OUTPUT_LABEL_TEXT;
import static raphaelpantaleao.katabanckocr.constants.Constants.OUTPUT_TEXT_AREA_NAME;
import static raphaelpantaleao.katabanckocr.constants.Constants.SCANNER_BUTTON_NAME;
import static raphaelpantaleao.katabanckocr.constants.Constants.SCANNER_BUTTON_TEXT;
import static raphaelpantaleao.katabanckocr.constants.Constants.SELECT_FILE_BUTTON_NAME;
import static raphaelpantaleao.katabanckocr.constants.Constants.SELECT_FILE_BUTTON_TEXT;
import static raphaelpantaleao.katabankocr.ui.JButtonBuilder.newButton;
import static raphaelpantaleao.katabankocr.ui.JLabelBuilder.newJLabel;
import static raphaelpantaleao.katabankocr.ui.JTextAreaBuilder.newTextArea;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UIFrame extends JFrame {

	private static final long serialVersionUID = 4752440571780079564L;
	private final JTextArea outputTextArea = newTextArea()
			.named(OUTPUT_TEXT_AREA_NAME).withRows(MAX_ACCOUNT_LINES)
			.withColumns(MAX_ACCOUNT_LINE_LENGTH).nonEditable().build();
	private final JTextArea inputTextArea = newTextArea()
			.named(INPUT_TEXT_AREA_NAME).withRows(MAX_SCANNER_LINES)
			.withColumns(MAX_SCANNER_LINE_LENGTH).nonEditable().build();

	private final JButton scannerButton = newButton()
			.named(SCANNER_BUTTON_NAME).withText(SCANNER_BUTTON_TEXT)
			.disabled().build();

	private final JButton selectFileButton = newButton()
			.named(SELECT_FILE_BUTTON_NAME).withText(SELECT_FILE_BUTTON_TEXT)
			.enabled().build();

	public UIFrame(String name, String title) {
		configFrame(name, title);

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

		pack();
	}

	private void configFrame(String name, String title) {
		setName(name);
		setTitle(title);
		setLayout(new FlowLayout(CENTER));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800, 600));
	}

	public void addSelectFileListener(ActionListener listener) {
		selectFileButton.addActionListener(listener);
	}

	public void addScanFileListener(ActionListener listener) {
		scannerButton.addActionListener(listener);
	}

	public void appendOutput(String string) {
		outputTextArea.append(string);
	}

	public void appendInput(String string) {
		boolean stringIsNotEmpty = string != null && !string.isEmpty();
		if (stringIsNotEmpty) {
			inputTextArea.append(string);
			scannerButton.setEnabled(true);
		}
	}

}
