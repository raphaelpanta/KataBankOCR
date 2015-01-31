package raphaelpantaleao.katabanckocr.models;

import static raphaelpantaleao.katabanckocr.constants.Constants.MAX_SCANNER_LINE_LENGTH;

import java.util.NoSuchElementException;
import java.util.Scanner;

import raphaelpantaleao.katabanckocr.exceptions.EntryValidationException;

public class EntryValidator {

	void verifyEntry(Entry aEntry) throws EntryValidationException {
		boolean notEndsWithWhitespace = !aEntry.entry
				.endsWith("                           \n");
		if (notEndsWithWhitespace) {
			throw new EntryValidationException(
					"Last Line could only have whitespaces.");
		}
		boolean notHaveOnlyPipesUnderscoresOrWhitespaces = !aEntry.entry
				.matches("[\\s\\|\\_\\\\n]+");
		if (notHaveOnlyPipesUnderscoresOrWhitespaces) {
			throw new EntryValidationException(
					"First Three lines could only have pipes, underscores and whitespaces.");
		}
	}

	String validateIfHasSufficientLinesIn(Scanner aScanner)
			throws EntryValidationException {
		String line;
		try {
			line = aScanner.nextLine();
		} catch (NoSuchElementException e) {
			throw new EntryValidationException("Entry has insuficient lines.",
					e);
		}
		return line;
	}

	void validateLengthOf(String line) throws EntryValidationException {
		if (line.length() > MAX_SCANNER_LINE_LENGTH) {
			throw new EntryValidationException(
					"Entry has lines with length greater than "
							+ MAX_SCANNER_LINE_LENGTH + ".");
		}
		if (line.length() < MAX_SCANNER_LINE_LENGTH) {
			throw new EntryValidationException(
					"Entry has lines with length less than "
							+ MAX_SCANNER_LINE_LENGTH + ".");
		}
	}
}
