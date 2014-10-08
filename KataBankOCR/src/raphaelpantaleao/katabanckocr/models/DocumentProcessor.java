package raphaelpantaleao.katabanckocr.models;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import raphaelpantaleao.katabanckocr.interfaces.NumberParser;
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
		return parseDigitsFrom(scanner);
	}

	private String parseDigitsFrom(Scanner scanner) {
		NumberParser parser = new Zero();
		String result = "";
		for (String string : extractDigitsFrom(scanner)) {
			result += parser.parse(string) + "\n";
		}
		return result;
	}

	private List<String> extractDigitsFrom(Scanner scanner) {
		List<String> digits = new ArrayList<>();
		int i = 1;
		String digit = "";
		while (scanner.hasNextLine()) {
			digit += scanner.nextLine();
			if (i % 4 == 0) {
				digits.add(digit);
				digit = "";
			}
			i++;
		}
		return digits;
	}

	public String unprocessedEntries() {
		return scannedBuilder.toString();
	}
}
