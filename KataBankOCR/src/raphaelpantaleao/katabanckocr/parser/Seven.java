package raphaelpantaleao.katabanckocr.parser;

import raphaelpantaleao.katabanckocr.patterns.AccountPatterns;

class Seven extends AbstractNumberParser {

    @Override
    protected String getPattern() {
	return AccountPatterns.SEVEN.getPattern();
    }

    @Override
    protected String getNumber() {
	return AccountPatterns.SEVEN.getNumber();
    }

    @Override
    protected String next(String digits, int pos) {
	return new Eight().parse(digits, pos);
    }

    @Override
    boolean matches(String digits, int pos) {

	return AccountPatterns.SEVEN.matches(digits, pos);
    }
}
