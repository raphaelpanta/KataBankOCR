package raphaelpantaleao.katabankocr.ui;

import static raphaelpantaleao.katabanckocr.appconstants.Constants.INPUT_LABEL_NAME;
import static raphaelpantaleao.katabanckocr.appconstants.Constants.INPUT_LABEL_TEXT;
import static raphaelpantaleao.katabanckocr.appconstants.Constants.INPUT_TEXT_AREA_NAME;
import static raphaelpantaleao.katabanckocr.appconstants.Constants.OUTPUT_LABEL_NAME;
import static raphaelpantaleao.katabanckocr.appconstants.Constants.OUTPUT_LABEL_TEXT;
import static raphaelpantaleao.katabanckocr.appconstants.Constants.OUTPUT_TEXT_AREA_NAME;
import static raphaelpantaleao.katabanckocr.appconstants.Constants.SCANNER_BUTTON_NAME;
import static raphaelpantaleao.katabanckocr.appconstants.Constants.SCANNER_BUTTON_TEXT;
import static raphaelpantaleao.katabanckocr.appconstants.Constants.SELECT_FILE_BUTTON_NAME;
import static raphaelpantaleao.katabanckocr.appconstants.Constants.SELECT_FILE_BUTTON_TEXT;
import static raphaelpantaleao.katabanckocr.builders.JButtonBuilder.newButton;
import static raphaelpantaleao.katabanckocr.builders.JLabelBuilder.newJLabel;
import static raphaelpantaleao.katabanckocr.builders.JTextAreaBuilder.newTextArea;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UIFrame extends JFrame {

	private static final long serialVersionUID = 4752440571780079564L;
	private final JTextArea outputTextArea;
	private final JTextArea inputTextArea;

	private final JButton scannerButton = newButton()
			.named(SCANNER_BUTTON_NAME).withText(SCANNER_BUTTON_TEXT)
			.disabled().build();

	private final JButton selectFileButton = newButton()
			.named(SELECT_FILE_BUTTON_NAME).withText(SELECT_FILE_BUTTON_TEXT)
			.enabled().build();

	public UIFrame(String name, String title) {
		configFrame(name, title);

		inputTextArea = newTextArea().named(INPUT_TEXT_AREA_NAME).withRows(4)
				.withColumns(27).nonEditable().build();
		outputTextArea = newTextArea().named(OUTPUT_TEXT_AREA_NAME).withRows(1)
				.withColumns(9).nonEditable().build();

		add(newJLabel().named(INPUT_LABEL_NAME).withText(INPUT_LABEL_TEXT)
				.build());
		add(selectFileButton);
		add(new JScrollPane(inputTextArea));
		add(newJLabel().named(OUTPUT_LABEL_NAME).withText(OUTPUT_LABEL_TEXT)
				.build());
		add(scannerButton);
		add(new JScrollPane(outputTextArea));

		pack();
	}

	private void configFrame(String name, String title) {
		setName(name);
		setTitle(title);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(350, 300));
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
		inputTextArea.append(string);
		scannerButton.setEnabled(true);
	}

}
