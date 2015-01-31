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

}
