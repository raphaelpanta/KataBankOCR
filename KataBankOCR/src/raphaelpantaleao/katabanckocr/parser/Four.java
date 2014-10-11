package raphaelpantaleao.katabanckocr.parser;

class Four extends AbstractNumberParser {

	@Override
	protected String getPattern() {
		return "   " 
	         + "|_|" 
			 + "  |" 
	         + "   ";
	}

	@Override
	protected String getNumber() {
		return "4";
	}

	@Override
	protected String next(String digits, int pos) {
		return new Five().parse(digits, pos);
	}

}