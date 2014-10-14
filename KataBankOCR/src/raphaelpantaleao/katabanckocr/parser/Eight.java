package raphaelpantaleao.katabanckocr.parser;


class Eight extends AbstractNumberParser {

	@Override
	protected String getPattern() {
		return " _ " + "|_|" + "|_|" + "   ";
	}

	@Override
	protected String getNumber() {
		return "8";
	}

	@Override
	protected String next(String digits, int pos)  {
		return new Nine().parse(digits, pos);
	}

}
