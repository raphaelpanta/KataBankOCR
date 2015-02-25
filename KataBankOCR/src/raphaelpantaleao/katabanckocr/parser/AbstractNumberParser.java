package raphaelpantaleao.katabanckocr.parser;

import raphaelpantaleao.katabanckocr.api.NumberParser;

abstract class AbstractNumberParser implements NumberParser {

    abstract protected String getPattern();

    abstract protected String getNumber();

    abstract protected String next(String digits, int pos);

    protected final String start(String digits, int pos) {
	return new Zero().parse(digits, pos);
    }

    protected final String parse(String digits, int pos) {
	if (matches(digits, pos)) {
	    if (pos >= 8)
		return getNumber();
	    else
		return getNumber() + start(digits, ++pos);
	} else {
	    return next(digits, pos);
	}
    }

    public final String parse(String digits) {
	return start(digits, 0);
    }

    abstract boolean matches(final String digits, final int pos);
}
