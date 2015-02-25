package raphaelpantaleao.katabanckocr.patterns;

public enum AccountPatterns {

    ZERO {
	@Override
	public String getPattern() {
	    return " _ " + "| |" + "|_|" + "   ";
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
	return compareLinesOf(digits, 0, 3, 0, 3, pos)
		&& compareLinesOf(digits, 28, 31, 3, 6, pos)
		&& compareLinesOf(digits, 56, 59, 6, 9, pos)
		&& compareLinesOf(digits, 84, 87, 9, 12, pos);
    }

    private boolean compareLinesOf(final String digits, int index, int end,
	    int compIndex, int compEnd, int digitPos) {
	String line = digits
		.substring(index + digitPos * 3, end + digitPos * 3);
	String comparisonLine = this.getPattern().substring(compIndex, compEnd);

	return line.equals(comparisonLine);
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
