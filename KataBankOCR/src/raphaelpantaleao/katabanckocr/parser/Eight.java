package raphaelpantaleao.katabanckocr.parser;

import raphaelpantaleao.katabanckocr.patterns.AccountPatterns;

class Eight extends AbstractNumberParser {

    @Override
    protected String getPattern() {
	return AccountPatterns.EIGHT.getPattern();
    }

    @Override
    protected String getNumber() {
	return AccountPatterns.EIGHT.getNumber();
    }

    @Override
    protected String next(String digits, int pos) {
	return new Nine().parse(digits, pos);
    }

}
