package raphaelpantaleao.katabanckocr.parser;


class Five extends AbstractNumberParser {

	@Override
	protected String getPattern() {
		return " _ " + "|_ " + " _|" + "   ";
	}

	@Override
	protected String getNumber() {
		return "5";
	}

	@Override
	protected String next(String digits, int pos) {
		return new Six().parse(digits, pos);
	}

}
