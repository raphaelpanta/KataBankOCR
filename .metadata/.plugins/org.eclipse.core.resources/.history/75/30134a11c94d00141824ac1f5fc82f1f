package raphaelpantaleao.katabanckocr.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import raphaelpantaleao.katabanckocr.exceptions.StreamProviderException;
import raphaelpantaleao.katabanckocr.interfaces.StreamProvider;
import raphaelpantaleao.katabanckocr.models.Document;
import raphaelpantaleao.katabankocr.ui.UIFrame;

public class SelectFileListener implements ActionListener {

	private UIFrame frame;
	private StreamProvider provider;
	private Document doc;

	public SelectFileListener(UIFrame frame, StreamProvider provider,
			Document doc) {
		this.frame = frame;
		this.provider = provider;
		this.doc = doc;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		try {
			doc.process(provider.getStream());
		} catch (StreamProviderException e) {
			throw new RuntimeException(e);
		}
		frame.appendInput(doc.unprocessedEntries());
	}
}
