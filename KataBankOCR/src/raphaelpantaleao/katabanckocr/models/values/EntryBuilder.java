package raphaelpantaleao.katabanckocr.models.values;

import static raphaelpantaleao.katabanckocr.appconstants.Constants.MAX_SCANNER_LINES;
import static raphaelpantaleao.katabanckocr.appconstants.Constants.MAX_SCANNER_LINE_LENGTH;

import java.util.NoSuchElementException;
import java.util.Scanner;

import raphaelpantaleao.katabanckocr.exceptions.EntryValidationException;

public class EntryBuilder {

	private StringBuilder textBuilder = new StringBuilder();

	public Entry with(Scanner scanner) throws EntryValidationException {

		for (int i = 0; i < MAX_SCANNER_LINES; i++) {
			String line = validadeIfHasSufficientLinesIn(scanner);
			validateLengthOf(line);
			textBuilder.append(line).append("\n");
		}
		return new Entry(textBuilder.toString());
	}

	private String validadeIfHasSufficientLinesIn(Scanner aScanner)
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

	private void validateLengthOf(String line) throws EntryValidationException {
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
