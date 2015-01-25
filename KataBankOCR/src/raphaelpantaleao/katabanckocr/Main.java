package raphaelpantaleao.katabanckocr;

import static javax.swing.SwingUtilities.invokeAndWait;
import static raphaelpantaleao.katabanckocr.api.NonBlokingActionListener.invokeLater;
import static raphaelpantaleao.katabanckocr.constants.Constants.FILE_CHOOSER_NAME;
import static raphaelpantaleao.katabanckocr.constants.Constants.TITLE_NAME;
import static raphaelpantaleao.katabanckocr.constants.Constants.TITLE_TEXT;

import java.awt.HeadlessException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

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
			final DocumentProcessor doc = new DocumentProcessor(
					new EntryExtractor(new EntryValidator()));
			final JFileChooser jFileChooser = createFileChooser();
			final UIFrame topLevelFrame = createUIFrame(doc, jFileChooser);
			topLevelFrame.setVisible(true);
		});
	}

	private static UIFrame createUIFrame(final DocumentProcessor doc,
			final JFileChooser jFileChooser) {
		UIFrame topLevelFrame = new UIFrame(TITLE_NAME, TITLE_TEXT) {
			private static final long serialVersionUID = -4459872068101440744L;

			{
				addScanFileListener(invokeLater(new ScanFileListener(doc, this)));
				addSelectFileListener(invokeLater(new SelectFileListener(this,
						createStreamProviderFor(this), doc,
						new JDialogErrorHandler(this))));
			}

		};
		return topLevelFrame;
	}

	private static FileChooserStreamProvider createStreamProviderFor(
			JFrame frame) {
		return new FileChooserStreamProvider(createFileChooser(), frame);
	}

	private static JFileChooser createFileChooser() {
		final JFileChooser jFileChooser = new JFileChooser() {
			private static final long serialVersionUID = 4487606734991064569L;

			{
				setName(FILE_CHOOSER_NAME);
			}
		};
		return jFileChooser;
	}

}
