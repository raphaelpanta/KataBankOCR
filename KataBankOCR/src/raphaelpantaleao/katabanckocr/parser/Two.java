package raphaelpantaleao.katabanckocr.parser;

class Two extends AbstractNumberParser {

	@Override
	protected String getPattern() {
		return " _ " + " _|" + "|_ " + "   ";
	}

	@Override
	protected String getNumber() {
		return "2";
	}

	@Override
	protected String next(String digits, int pos) {
		return new Three().parse(digits, pos);
	}

}