package raphaelpantaleao.katabanckocr.models;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import raphaelpantaleao.katabanckocr.parser.NumberParser;
import raphaelpantaleao.katabanckocr.parser.Zero;

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



	public String entries() {
		Scanner scanner = new Scanner(new BufferedInputStream(
				new ByteArrayInputStream(scannedBuilder.toString().getBytes())));
		String digits = "";

		for (int i = 0; scanner.hasNextLine() || i < 4; i++) {
			digits += scanner.nextLine();
		}

		NumberParser parser = new Zero();
		return parser.parse(digits) + "\n";
	}

	public String unprocessedEntries() {
		return scannedBuilder.toString();
	}
}
