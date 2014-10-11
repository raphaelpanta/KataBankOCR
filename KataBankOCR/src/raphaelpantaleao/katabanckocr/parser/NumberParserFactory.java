package raphaelpantaleao.katabanckocr.parser;

public class NumberParserFactory {
	private NumberParserFactory() {

	}

	public static AbstractNumberParser getParserInstance() {
		return new Zero();
	}
}
