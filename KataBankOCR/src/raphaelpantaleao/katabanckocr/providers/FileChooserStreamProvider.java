package raphaelpantaleao.katabanckocr.providers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import raphaelpantaleao.katabanckocr.exceptions.StreamProviderException;
import raphaelpantaleao.katabanckocr.interfaces.StreamProvider;

public class FileChooserStreamProvider implements StreamProvider {
	private JFileChooser chooser;
	private JFrame frame;

	public FileChooserStreamProvider(JFileChooser chooser, JFrame frame) {
		super();
		this.chooser = chooser;
		this.frame = frame;
	}

	@Override
	public InputStream getStream() throws StreamProviderException {
		int returnOption = chooser.showOpenDialog(frame);
		if (returnOption == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				return new FileInputStream(file);
			} catch (FileNotFoundException e) {
				throw new StreamProviderException("Could not read file.", e);
			}
		}
		return null;
	}
}
