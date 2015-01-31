package raphaelpantaleao.katabanckocr.parser;

import raphaelpantaleao.katabanckocr.patterns.AccountPatterns;

class Zero extends AbstractNumberParser {

    @Override
    protected String getPattern() {
	return AccountPatterns.ZERO.getPattern();
    }

    @Override
    protected String next(String digits, int pos) {
	return new One().parse(digits, pos);
    }

    @Override
    protected String getNumber() {
	return AccountPatterns.ZERO.getNumber();
    }
}
