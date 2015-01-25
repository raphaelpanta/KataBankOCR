package raphaelpantaleao.katabanckocr;

import static javax.swing.SwingUtilities.invokeAndWait;
import static raphaelpantaleao.katabanckocr.api.NonBlokingActionListener.invokeLater;
import static raphaelpantaleao.katabanckocr.constants.Constants.FILE_CHOOSER_NAME;
import static raphaelpantaleao.katabanckocr.constants.Constants.TITLE_NAME;
import static raphaelpantaleao.katabanckocr.constants.Constants.TITLE_TEXT;

import java.awt.HeadlessException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFileChooser;

import raphaelpantaleao.katabanckocr.api.ScanFileListener;
import raphaelpantaleao.katabanckocr.api.SelectFileListener;
import raphaelpantaleao.katabanckocr.models.DocumentProcessor;
import raphaelpantaleao.katabanckocr.models.EntryExtractor;
import raphaelpantaleao.katabanckocr.models.EntryValidator;
import raphaelpantaleao.katabankocr.ui.FileChooserStreamProvider;
import raphaelpantaleao.katabankocr.ui.JDialogErrorHandler;
import raphaelpantaleao.katabankocr.ui.UIFrame;

public class Main {

	public static void main(String[] args) throws HeadlessException,
			InvocationTargetException, InterruptedException {
		invokeAndWait(() -> {
			final UIFrame topLevelFrame = createUIFrame(
					creteDocumentProcessor(), createFileChooser());
			topLevelFrame.setVisible(true);
		});
	}

	private static DocumentProcessor creteDocumentProcessor() {
		return new DocumentProcessor(new EntryExtractor(new EntryValidator()));
	}

	private static UIFrame createUIFrame(final DocumentProcessor doc,
			final JFileChooser jFileChooser) {
		UIFrame topLevelFrame = new UIFrame(TITLE_NAME, TITLE_TEXT);
		topLevelFrame.addScanFileListener(invokeLater(new ScanFileListener(doc,
				topLevelFrame)));
		topLevelFrame.addSelectFileListener(invokeLater(new SelectFileListener(
				topLevelFrame, new FileChooserStreamProvider(jFileChooser,
						topLevelFrame), doc, new JDialogErrorHandler(
						topLevelFrame))));
		return topLevelFrame;
	}

	private static JFileChooser createFileChooser() {
		final JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setName(FILE_CHOOSER_NAME);
		return jFileChooser;
	}

}
