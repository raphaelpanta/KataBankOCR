package raphaelpantaleao.katabanckocr.parser;

public class Zero extends AbstractNumberParser {

	private String zero = " _ " + "| |" + "|_|" + "   ";

	@Override
	protected String getPattern() {
		return zero;
	}

	@Override
	protected String next(String digits, int pos) {
		return new One().parse(digits, pos);
	}

	@Override
	protected String getNumber() {
		return "0";
	}
}
