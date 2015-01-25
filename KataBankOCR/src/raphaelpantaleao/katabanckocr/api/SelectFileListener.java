package raphaelpantaleao.katabanckocr.api;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import raphaelpantaleao.katabanckocr.exceptions.DocumentProcessorException;
import raphaelpantaleao.katabanckocr.exceptions.StreamProviderException;
import raphaelpantaleao.katabanckocr.models.DocumentProcessor;
import raphaelpantaleao.katabankocr.ui.UIFrame;

public class SelectFileListener implements ActionListener {

	private final UIFrame frame;
	private final StreamProvider provider;
	private final DocumentProcessor doc;
	private final ErrorHandlerListener errorHandler;

	public SelectFileListener(final UIFrame frame,
			final StreamProvider provider, final DocumentProcessor doc,
			final ErrorHandlerListener errorHandler) {
		this.frame = frame;
		this.provider = provider;
		this.doc = doc;
		this.errorHandler = errorHandler;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		try {
			InputStream stream = provider.getStream();
				doc.process(stream);
		} catch (DocumentProcessorException e) {
			DocumentProcessorException docException = new DocumentProcessorException(
					"File is malformed: " + e.getMessage(), e);
			errorHandler.onError(docException);
		} catch (StreamProviderException e) {
			errorHandler.onError(e);
		}
		frame.appendInput(doc.unprocessedEntries());
	}
}
