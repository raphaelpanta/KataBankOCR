package raphaelpantaleao.katabanckocr.listeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import raphaelpantaleao.katabanckocr.models.DocumentProcessor;
import raphaelpantaleao.katabankocr.ui.UIFrame;

public class ScanFileListener implements ActionListener {

	private DocumentProcessor doc;
	private final UIFrame frame;

	public ScanFileListener(DocumentProcessor doc, final UIFrame frame) {
		this.doc = doc;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.appendOutput(doc.entries());
	}

}
