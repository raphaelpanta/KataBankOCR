package raphaelpantaleao.katabanckocr.interfaces;

import java.io.InputStream;

import raphaelpantaleao.katabanckocr.exceptions.StreamProviderException;

public interface StreamProvider {

	InputStream getStream() throws StreamProviderException;
}
