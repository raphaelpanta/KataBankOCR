package raphaelpantaleao.katabanckocr.parser;

public class NumberParserFactory {
	private NumberParserFactory() {

	}

	public static AbstractNumberParser createParser() {
		return new Zero();
	}
}
