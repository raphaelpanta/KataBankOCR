package raphaelpantaleao.katabanckocr.controller;

import java.io.InputStream;

import javax.inject.Inject;

import raphaelpantaleao.katabanckocr.exceptions.DocumentProcessorException;
import raphaelpantaleao.katabanckocr.models.DocumentProcessor;
import raphaelpantaleao.katabankocr.ui.UIFrame;

public class DocumentController {

    private final UIFrame frame;
    private final DocumentProcessor doc;

    @Inject
    public DocumentController(final UIFrame frame, final DocumentProcessor doc) {
	this.frame = frame;
	this.doc = doc;
    }

    public void appendOutput() {
	    String entries = doc.entries();
	    frame.appendOutput(entries);
    }

    public void process(InputStream stream) throws DocumentProcessorException {
	doc.process(stream);
	frame.appendInput(doc.unprocessedEntries());

    }
}
