package raphaelpantaleao.katabanckocr.models;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import raphaelpantaleao.katabanckocr.exceptions.DocumentProcessorException;
import raphaelpantaleao.katabanckocr.interfaces.NumberParser;
import raphaelpantaleao.katabanckocr.parser.NumberParserFactory;

public class DocumentProcessor {
	private StringBuilder scannedBuilder;

	public DocumentProcessor() {
		scannedBuilder = new StringBuilder();
	}

	public void process(InputStream streamedDoc)
			throws DocumentProcessorException {
		Scanner scanner = new Scanner(streamedDoc);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.length() != 27) {
				throw new DocumentProcessorException(
						"Line lenght is greater than " + 27);
			}
			this.scannedBuilder.append(line + "\n");
		}
		scanner.close();
	}

	public String entries() {
		Scanner scanner = new Scanner(new BufferedInputStream(
				new ByteArrayInputStream(scannedBuilder.toString().getBytes())));
		return parseDigitsFrom(scanner);
	}

	private String parseDigitsFrom(Scanner scanner) {
		NumberParser parser = NumberParserFactory.getParserInstance();
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
