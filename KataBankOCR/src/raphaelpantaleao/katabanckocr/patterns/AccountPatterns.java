package raphaelpantaleao.katabanckocr.patterns;

import static raphaelpantaleao.katabanckocr.constants.Constants.MAX_SCANNER_LINES;
import static raphaelpantaleao.katabanckocr.constants.Constants.MAX_SCANNER_LINE_LENGTH;

public enum AccountPatterns {

    ZERO {
	@Override
	public String getPattern() {
	    return " _ " + "| |" + "|_|" + " ";
	}

	@Override
	public String getNumber() {
	    return "0";
	}
    },
    ONE {
	@Override
	public String getPattern() {
	    return "   " + "  |" + "  |" + "   ";
	}

	@Override
	public String getNumber() {
	    return "1";
	}
    },
    TWO {
	@Override
	public String getPattern() {
	    return " _ " + " _|" + "|_ " + "   ";
	}

	@Override
	public String getNumber() {
	    return "2";
	}
    },
    THREE {
	@Override
	public String getPattern() {
	    return " _ " + " _|" + " _|" + "   ";
	}

	@Override
	public String getNumber() {
	    return "3";
	}
    },
    FOUR {
	@Override
	public String getPattern() {
	    return "   " + "|_|" + "  |" + "   ";
	}

	@Override
	public String getNumber() {
	    return "4";
	}
    },
    FIVE {
	@Override
	public String getPattern() {
	    return " _ " + "|_ " + " _|" + "   ";
	}

	@Override
	public String getNumber() {
	    return "5";
	}
    },
    SIX {
	@Override
	public String getPattern() {
	    return " _ " + "|_ " + "|_|" + "   ";
	}

	@Override
	public String getNumber() {
	    return "6";
	}
    },
    SEVEN {
	@Override
	public String getPattern() {
	    return " _ " + "  |" + "  |" + "   ";
	}

	@Override
	public String getNumber() {
	    return "7";
	}
    },
    EIGHT {
	@Override
	public String getPattern() {
	    return " _ " + "|_|" + "|_|" + "   ";
	}

	@Override
	public String getNumber() {
	    return "8";
	}
    },
    NINE {
	@Override
	public String getPattern() {
	    return " _ " + "|_|" + " _|" + "   ";
	}

	@Override
	public String getNumber() {
	    return "9";
	}
    };

    public abstract String getPattern();

    public abstract String getNumber();

    public boolean matches(final String digits, final int pos) {
	int i = pos * 3;
	boolean identified = true;
	for (int j = 0; j < MAX_SCANNER_LINES; j++) {
	    identified &= digits.charAt(i + MAX_SCANNER_LINE_LENGTH * j) == getPattern()
		    .charAt(j * 3)
		    && digits.charAt(i + 1 + MAX_SCANNER_LINE_LENGTH * j) == getPattern()
			    .charAt(j * 3 + 1)
		    && digits.charAt(i + 2 + MAX_SCANNER_LINE_LENGTH * j) == getPattern()
			    .charAt(j * 3 + 2);
	}
	return identified;
    }

    public static boolean validatePatternOf(String digits) {
	boolean matches = true;
	for (int pos = 0; pos < 9; pos++) {
	    boolean matchsOne = false;
	    for (AccountPatterns patterns : AccountPatterns.values()) {
		if (patterns.matches(digits, pos)) {
		    matchsOne = true;
		    break;
		}
	    }
	    matches &= matchsOne;
	}
	return matches;
    }
}
