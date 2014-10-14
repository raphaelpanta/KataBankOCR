package raphaelpantaleao.katabanckocr.parser;


class Six extends AbstractNumberParser {

	@Override
	protected String getPattern() {
		return " _ " + "|_ " + "|_|" + "   ";

	}

	@Override
	protected String getNumber() {
		return "6";
	}

	@Override
	protected String next(String digits, int pos) {
		return new Seven().parse(digits, pos);
	}

}
