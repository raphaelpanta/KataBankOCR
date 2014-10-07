package raphaelpantaleao.katabanckocr.parser;

public class Zero extends AbstractNumberParser {

	private String zero = " _ " + "| |" + "|_|" + "   ";

	@Override
	protected String getPattern() {
		return zero;
	}

	@Override
	protected String next(String digits, int pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getNumber() {
		return "0";
	}
}
