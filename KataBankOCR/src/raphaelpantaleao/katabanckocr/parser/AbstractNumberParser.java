package raphaelpantaleao.katabanckocr.parser;

import static raphaelpantaleao.katabanckocr.appconstants.Constants.MAX_SCANNER_LINES;
import static raphaelpantaleao.katabanckocr.appconstants.Constants.MAX_SCANNER_LINE_LENGTH;
import raphaelpantaleao.katabanckocr.interfaces.NumberParser;

abstract class AbstractNumberParser implements NumberParser {

	abstract protected String getPattern();

	abstract protected String getNumber();

	abstract protected String next(String digits, int pos);

	protected final String start(String digits, int pos) {
		return new Zero().parse(digits, pos);
	}

	protected final String parse(String digits, int pos) {
		int i = pos * 3;
		boolean identified = true;
		for (int j = 0; j < MAX_SCANNER_LINES; j++) {
			identified &= digits.charAt(i + MAX_SCANNER_LINE_LENGTH * j) == getPattern().charAt(
					j * 3)
					&& digits.charAt(i + 1 + MAX_SCANNER_LINE_LENGTH * j) == getPattern().charAt(
							j * 3 + 1)
					&& digits.charAt(i + 2 + MAX_SCANNER_LINE_LENGTH * j) == getPattern().charAt(
							j * 3 + 2);
		}
		if (identified) {
			if (i >= 24)
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

}
