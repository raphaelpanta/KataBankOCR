package raphaelpantaleao.katabanckocr.models;

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

	public String entries() {
		return "123456789\n";
	}

	public String unprocessedEntries() {
		return scannedBuilder.toString();
	}
}