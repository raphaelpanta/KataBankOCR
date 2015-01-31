package raphaelpantaleao.katabankocr.ui;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import javax.inject.Inject;
import javax.swing.JFrame;

import raphaelpantaleao.katabanckocr.api.ErrorHandlerListener;

public class JDialogErrorHandler implements ErrorHandlerListener {

	private final JFrame frame;

	@Inject
	public JDialogErrorHandler(final JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void onError(Exception e) {
		showMessageDialog(frame, e.getMessage(), "Error",
				ERROR_MESSAGE);
		e.printStackTrace();
	}

}
