package raphaelpantaleao.katabanckocr.api;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Provider;

import raphaelpantaleao.katabanckocr.controller.DocumentController;
import raphaelpantaleao.katabanckocr.exceptions.DocumentProcessorException;
import raphaelpantaleao.katabanckocr.exceptions.StreamProviderException;

public class SelectFileListener implements ActionListener {

    private final Provider<DocumentController> controller;
    private final StreamProvider provider;
    private final ErrorHandlerListener errorHandler;

    @Inject
    public SelectFileListener(final Provider<DocumentController> controller,
	    final StreamProvider provider,
	    final ErrorHandlerListener errorHandler) {
	this.controller = controller;
	this.provider = provider;
	this.errorHandler = errorHandler;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
	try {
	    InputStream stream = provider.getStream();
	    controller.get().process(stream);
	} catch (DocumentProcessorException e) {
	    DocumentProcessorException docException = new DocumentProcessorException(
		    "File is malformed: " + e.getMessage(), e);
	    errorHandler.onError(docException);
	} catch (StreamProviderException e) {
	    errorHandler.onError(e);
	}

    }
}
