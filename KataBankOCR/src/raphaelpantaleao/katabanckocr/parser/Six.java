package raphaelpantaleao.katabanckocr.parser;

import raphaelpantaleao.katabanckocr.patterns.AccountPatterns;

class Six extends AbstractNumberParser {

    @Override
    protected String getPattern() {
	return AccountPatterns.SIX.getPattern();

    }

    @Override
    protected String getNumber() {
	return AccountPatterns.SIX.getNumber();
    }

    @Override
    protected String next(String digits, int pos) {
	return new Seven().parse(digits, pos);
    }

    @Override
    boolean matches(String digits, int pos) {

	return AccountPatterns.SIX.matches(digits, pos);
    }
}
