package raphaelpantaleao.katabanckocr.parser;

import raphaelpantaleao.katabanckocr.patterns.AccountPatterns;

class Two extends AbstractNumberParser {

    @Override
    protected String getPattern() {
	return AccountPatterns.TWO.getPattern();
    }

    @Override
    protected String getNumber() {
	return AccountPatterns.TWO.getNumber();
    }

    @Override
    protected String next(String digits, int pos) {
	return new Three().parse(digits, pos);
    }

}
