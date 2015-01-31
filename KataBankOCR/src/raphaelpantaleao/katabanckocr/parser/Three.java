package raphaelpantaleao.katabanckocr.parser;

import raphaelpantaleao.katabanckocr.patterns.AccountPatterns;

class Three extends AbstractNumberParser {

    @Override
    protected String getPattern() {
	return AccountPatterns.THREE.getPattern();
    }

    @Override
    protected String getNumber() {
	return AccountPatterns.THREE.getNumber();
    }

    @Override
    protected String next(String digits, int pos) {
	return new Four().parse(digits, pos);
    }

}