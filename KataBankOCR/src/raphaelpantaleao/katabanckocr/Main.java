package raphaelpantaleao.katabanckocr;

import static javax.swing.SwingUtilities.invokeAndWait;
import static javax.swing.SwingUtilities.invokeLater;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import raphaelpantaleao.katabanckocr.models.Document;

public class Main {

	public static void main(String[] args) throws HeadlessException,
			InvocationTargetException, InterruptedException {
		invokeAndWait(() -> {

			final JFrame topLevelFrame = new JFrame("BankOCR App") {
				Document doc;
				private static final long serialVersionUID = 189221029764492414L;

				{
					setName("Bank OCR");
					setLayout(new FlowLayout());
					setDefaultCloseOperation(EXIT_ON_CLOSE);
					setPreferredSize(new Dimension(350, 300));

					add(new JLabel("Input:") {
						{
							setName("input");
						}
					});

					final JTextArea inputTextArea = new JTextArea("") {
						{
							setName("input text area");
							setRows(3);
							setColumns(27);
							setEditable(false);
						}
					};

					final JTextArea outputTextArea = new JTextArea("") {
						{
							setName("output text area");
							setRows(1);
							setColumns(9);
							setEditable(false);
						}
					};

					final JButton scannerButton = new JButton("Scan File...") {
						{
							setName("scanner button");
							setEnabled(false);
							addActionListener((event) -> {
								invokeLater(() -> {
									outputTextArea.append(doc.entries());
								});
							});
						}
					};

					final JFileChooser jFileChooser = new JFileChooser() {
						{
							setName("file chooser");
						}
					};

					add(new JButton("Select File...") {
						{
							setName("select file button");
							addActionListener((event) -> {
								invokeLater(() -> {
									int returnOption = jFileChooser
											.showOpenDialog(this);
									if (returnOption == JFileChooser.APPROVE_OPTION) {
										File file = jFileChooser
												.getSelectedFile();
										try {
											doc = Document
													.process(new FileInputStream(
															file));
										} catch (Exception e) {
											throw new RuntimeException(
													"Could not open file", e);
										}
										inputTextArea.append(doc
												.unprocessedEntries());
										scannerButton.setEnabled(true);
									}
								});
							});
						}
					});

					add(new JScrollPane(inputTextArea));

					add(new JLabel("Output:") {
						{
							setName("output");
						}
					});

					add(scannerButton);

					add(new JScrollPane(outputTextArea));

					pack();
				}
			};
			topLevelFrame.setVisible(true);
		});
	}
}
