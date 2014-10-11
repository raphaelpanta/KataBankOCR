package raphaelpantaleao.katabanckocr.exceptions;

public class DocumentProcessorException extends Exception {
	private static final long serialVersionUID = -2151131426473276347L;

	public DocumentProcessorException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DocumentProcessorException(String msg) {
		super(msg);
	}

	public DocumentProcessorException(Throwable cause) {
		super(cause);
	}
}
