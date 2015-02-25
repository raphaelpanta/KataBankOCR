package raphaelpantaleao.katabanckocr.parser;

import raphaelpantaleao.katabanckocr.patterns.AccountPatterns;

class Nine extends AbstractNumberParser {

    @Override
    protected String getPattern() {
	return AccountPatterns.NINE.getPattern();
    }

    @Override
    protected String getNumber() {
	return AccountPatterns.NINE.getNumber();
    }

    @Override
    protected String next(String digits, int pos) {
	return null;
    }

    @Override
    boolean matches(String digits, int pos) {

	return AccountPatterns.NINE.matches(digits, pos);
    }
}
