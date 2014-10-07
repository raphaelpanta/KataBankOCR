package raphaelpantaleao.katabanckocr.models;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class DocumentProcessor {
	private StringBuilder scannedBuilder;

	public DocumentProcessor() {
		scannedBuilder = new StringBuilder();
	}

	public void process(InputStream streamedDoc) {
		Scanner scanner = new Scanner(streamedDoc);
		while (scanner.hasNextLine()) {
			this.scannedBuilder.append(scanner.nextLine() + "\n");
		}
		scanner.close();
	}

	private String zero = " _ | ||_|   ";

	public String entries() {
		Scanner scanner = new Scanner(new BufferedInputStream(
				new ByteArrayInputStream(scannedBuilder.toString().getBytes())));
		String number = "";
		String digits = "";

		for (int i = 0; scanner.hasNextLine() || i < 4; i++) {
			digits += scanner.nextLine();
		}

		for (int i = 0; i < 27; i = i + 3) {
			boolean isZero = false;
			for (int j = 0; j < 27 * 4; j = j + 27)
				isZero = digits.charAt(i + j) == digits.charAt(i + j)
						&& digits.charAt(i + j + 1) == digits.charAt(i + j + 1)
						&& digits.charAt(i + j + 2) == digits.charAt(i + j + 2);
			if (isZero) {
				number += "0";
			}
		}

		return number + "\n";
	}

	public String unprocessedEntries() {
		return scannedBuilder.toString();
	}
}
