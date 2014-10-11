package raphaelpantaleao.katabanckocr.parser;

class Nine extends AbstractNumberParser {

	@Override
	protected String getPattern() {
		return " _ " + "|_|" + " _|" + "   ";
	}

	@Override
	protected String getNumber() {
		return "9";
	}

	@Override
	protected String next(String digits, int pos) {
		return "?";
	}

}