package raphaelpantaleao.katabanckocr.models.values;

import java.util.Scanner;

import raphaelpantaleao.katabanckocr.exceptions.EntryValidationException;

public class EntryBuilder {

	private StringBuilder textBuilder = new StringBuilder();

	public Entry with(Scanner aScanner) throws EntryValidationException {
		for (int i = 0; i < 4; i++) {
			String line = aScanner.nextLine();
			if (line.length() != 27) {
				throw new EntryValidationException(
						"Line lenght is greater than " + 27);
			}
			textBuilder.append(line).append("\n");
		}
		return new Entry(textBuilder.toString());
	}
}
