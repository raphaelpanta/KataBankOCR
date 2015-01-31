package raphaelpantaleao.katabanckocr.parser;

import raphaelpantaleao.katabanckocr.patterns.AccountPatterns;


class One extends AbstractNumberParser{

	@Override
	protected String getPattern() {
		return AccountPatterns.ONE.getPattern();
	}

	@Override
	protected String getNumber() {
		return AccountPatterns.ONE.getNumber();
	}

	@Override
	protected String next(String digits, int pos)  {
		return new Two().parse(digits, pos);
	}

}
