package raphaelpantaleao.katabanckocr.parser;

class Seven extends AbstractNumberParser {

	@Override
	protected String getPattern() {
		return " _ " + "  |" + "  |" + "   ";
	}

	@Override
	protected String getNumber() {
		return "7";
	}

	@Override
	protected String next(String digits, int pos) {
		return new Eight().parse(digits, pos);
	}

}