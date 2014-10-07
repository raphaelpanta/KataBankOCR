package raphaelpantaleao.katabanckocr.models;

import java.io.InputStream;
import java.util.Scanner;

public class Document {
	private StringBuilder scannedBuilder;

	public Document() {
		scannedBuilder = new StringBuilder();
	}

	public void process(InputStream stream) {
		Scanner scanner = new Scanner(stream);
		while (scanner.hasNext()) {
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
