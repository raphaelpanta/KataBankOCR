package com.github.raphaelpanta.katabankocr.parser;

import static java.util.Arrays.asList;

import java.util.stream.Stream;

import com.github.raphaelpanta.katabankocr.api.NumberParser;

public class EnumNumberParser implements NumberParser {

	@Override
	public String parse(String digits) {
		return AccountPatterns.start(digits, 0);
	}
	
	public static boolean validatePatternOf(String digits) {
		return AccountPatterns.validatePatternOf(digits);
	}

	private enum AccountPatterns {

		ZERO {
			@Override
			public String getPattern() {
				return " _ " + "| |" + "|_|" + "   ";
			}

			@Override
			public String getNumber() {
				return "0";
			}

			@Override
			protected String next(String digits, int pos) {
				return ONE.parse(digits, pos);
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

			@Override
			protected String next(String digits, int pos) {
				return TWO.parse(digits, pos);
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

			@Override
			protected String next(String digits, int pos) {
				return THREE.parse(digits, pos);
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

			@Override
			protected String next(String digits, int pos) {
				return FOUR.parse(digits, pos);
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

			@Override
			protected String next(String digits, int pos) {
				return FIVE.parse(digits, pos);
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

			@Override
			protected String next(String digits, int pos) {
				return SIX.parse(digits, pos);
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

			@Override
			protected String next(String digits, int pos) {
				return SEVEN.parse(digits, pos);
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

			@Override
			protected String next(String digits, int pos) {
				return EIGHT.parse(digits, pos);
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

			@Override
			protected String next(String digits, int pos) {
				return NINE.parse(digits, pos);
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

			@Override
			protected String next(String digits, int pos) {
				return "?";
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
			String line = digits.substring(index + digitPos * 3, end + digitPos
					* 3);
			String comparisonLine = this.getPattern().substring(compIndex,
					compEnd);

			return line.equals(comparisonLine);
		}

		public static boolean validatePatternOf(String digits) {

			boolean matches = true;

			for (int pos = 0; pos < 9; pos++) {
				final int position = pos;
				matches &= AccountPatterns.stream()
						.filter((p) -> p.matches(digits, position)).findFirst()
						.isPresent();
			}
			return matches;
		}

		public static Stream<AccountPatterns> stream() {
			return asList(AccountPatterns.values()).stream();
		}

		public static final String start(String digits, int pos) {
			return ZERO.parse(digits, pos);
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

		protected abstract String next(String digits, int pos);

	}
}