package raphaelpantaleao.katabanckocr.parser;


class Three extends AbstractNumberParser {

	@Override
	protected String getPattern() {
		return " _ " + " _|" + " _|" + "   ";
	}

	@Override
	protected String getNumber() {
		return "3";
	}

	@Override
	protected String next(String digits, int pos) {
		return new Four().parse(digits, pos);
	}

}
