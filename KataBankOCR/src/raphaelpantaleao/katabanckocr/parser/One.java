package raphaelpantaleao.katabanckocr.parser;

public class One extends AbstractNumberParser{

	@Override
	protected String getPattern() {
		return "   " + "  |" + "  |" + "   ";
	}

	@Override
	protected String getNumber() {
		return "1";
	}

	@Override
	protected String next(String digits, int pos) {
		return new Two().parse(digits, pos);
	}

}
