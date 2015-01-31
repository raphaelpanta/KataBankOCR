package raphaelpantaleao.katabanckocr.parser;

import raphaelpantaleao.katabanckocr.patterns.AccountPatterns;

class Five extends AbstractNumberParser {

    @Override
    protected String getPattern() {
	return AccountPatterns.FIVE.getPattern();
    }

    @Override
    protected String getNumber() {
	return AccountPatterns.FIVE.getNumber();
    }

    @Override
    protected String next(String digits, int pos) {
	return new Six().parse(digits, pos);
    }

}
