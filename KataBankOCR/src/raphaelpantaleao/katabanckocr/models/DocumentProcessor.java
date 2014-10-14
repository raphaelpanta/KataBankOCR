package raphaelpantaleao.katabanckocr.models;

import static java.util.stream.Collectors.joining;
import static raphaelpantaleao.katabanckocr.parser.NumberParserFactory.createParser;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import raphaelpantaleao.katabanckocr.exceptions.DocumentProcessorException;
import raphaelpantaleao.katabanckocr.exceptions.EntryValidationException;
import raphaelpantaleao.katabanckocr.interfaces.NumberParser;
import raphaelpantaleao.katabanckocr.models.values.Entry;

public class DocumentProcessor {
	private final List<Entry> accountEntries;

	public DocumentProcessor() {
		accountEntries = new LinkedList<>();
	}

	public void process(InputStream streamedDoc)
			throws DocumentProcessorException {
		try (Scanner scanner = new Scanner(streamedDoc)) {
			while (scanner.hasNextLine()) {
				accountEntries.add(extractEntryFrom(scanner));
			}
		}
	}

	private Entry extractEntryFrom(Scanner scanner)
			throws DocumentProcessorException {
		Entry entry;
		try {
			entry = Entry.create().with(scanner);
		} catch (EntryValidationException e) {
			throw new DocumentProcessorException(e);
		}
		return entry;
	}

	public String entries() {
		final NumberParser parser = createParser();
		return accountEntries
				.stream()
				.map((entry) -> parser.parse(entry.entry.replaceAll("\\n", "")))
				.collect(joining("\n")) + "\n";
	}

	public String unprocessedEntries() {
		return accountEntries.stream().map((entry) -> entry.entry)
				.collect(joining());
	}
}
