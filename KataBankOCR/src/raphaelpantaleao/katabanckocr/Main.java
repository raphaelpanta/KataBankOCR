package raphaelpantaleao.katabanckocr;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.SwingUtilities.invokeAndWait;
import static raphaelpantaleao.katabanckocr.appconstants.Constants.FILE_CHOOSER_NAME;
import static raphaelpantaleao.katabanckocr.appconstants.Constants.TITLE_NAME;
import static raphaelpantaleao.katabanckocr.appconstants.Constants.TITLE_TEXT;
import static raphaelpantaleao.katabanckocr.listeners.NonBlokingActionListener.invokeLater;

import java.awt.HeadlessException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import raphaelpantaleao.katabanckocr.interfaces.ErrorHandlerListener;
import raphaelpantaleao.katabanckocr.listeners.ScanFileListener;
import raphaelpantaleao.katabanckocr.listeners.SelectFileListener;
import raphaelpantaleao.katabanckocr.models.DocumentProcessor;
import raphaelpantaleao.katabanckocr.models.values.EntryExtractor;
import raphaelpantaleao.katabanckocr.models.values.EntryValidator;
import raphaelpantaleao.katabanckocr.providers.FileChooserStreamProvider;
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
						createErrorHandlerFor(this))));
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

	private static ErrorHandlerListener createErrorHandlerFor(JFrame frame) {
		return (exception) -> {
			showMessageDialog(frame, exception.getMessage(), "Error",
					ERROR_MESSAGE);
			exception.printStackTrace();
		};
	}
}
