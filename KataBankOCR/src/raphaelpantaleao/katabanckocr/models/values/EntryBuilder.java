package raphaelpantaleao.katabanckocr.models.values;

import static raphaelpantaleao.katabanckocr.appconstants.Constants.MAX_SCANNER_LINES;
import static raphaelpantaleao.katabanckocr.appconstants.Constants.MAX_SCANNER_LINE_LENGTH;

import java.util.Scanner;

import raphaelpantaleao.katabanckocr.exceptions.EntryValidationException;

public class EntryBuilder {

	private StringBuilder textBuilder = new StringBuilder();

	public Entry with(Scanner aScanner) throws EntryValidationException {
		for (int i = 0; i < MAX_SCANNER_LINES; i++) {
			String line = aScanner.nextLine();
			if (line.length() != MAX_SCANNER_LINE_LENGTH) {
				throw new EntryValidationException(
						"Line lenght is greater than "
								+ MAX_SCANNER_LINE_LENGTH);
			}
			textBuilder.append(line).append("\n");
		}
		return new Entry(textBuilder.toString());
	}
}
