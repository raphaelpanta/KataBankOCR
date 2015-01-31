package raphaelpantaleao.katabanckocr.parser;

import raphaelpantaleao.katabanckocr.patterns.AccountPatterns;

class Four extends AbstractNumberParser {

    @Override
    protected String getPattern() {
	return AccountPatterns.FOUR.getPattern();
    }

    @Override
    protected String getNumber() {
	return AccountPatterns.FOUR.getNumber();
    }

    @Override
    protected String next(String digits, int pos) {
	return new Five().parse(digits, pos);
    }

}
