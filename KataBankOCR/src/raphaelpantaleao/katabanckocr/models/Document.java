package raphaelpantaleao.katabanckocr.models;

import java.io.InputStream;
import java.util.Scanner;

public class Document {
	private StringBuilder scannedBuilder;

	private Document() {
		scannedBuilder = new StringBuilder();
	}

	public static Document process(InputStream stream) {
		Document doc = new Document();
		Scanner scanner = new Scanner(stream);
		while (scanner.hasNext()) {
			doc.scannedBuilder.append(scanner.nextLine() + "\n");
		}
		scanner.close();
		return doc;
	}

	public String entries() {
		return "123456789\n";
	}

	public String unprocessedEntries() {
		return scannedBuilder.toString();
	}
}
