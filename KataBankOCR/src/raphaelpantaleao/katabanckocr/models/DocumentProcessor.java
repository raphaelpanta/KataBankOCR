package raphaelpantaleao.katabanckocr.models;

import static java.util.stream.Collectors.joining;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import raphaelpantaleao.katabanckocr.api.NumberParser;
import raphaelpantaleao.katabanckocr.exceptions.DocumentProcessorException;
import raphaelpantaleao.katabanckocr.exceptions.EntryValidationException;

public class DocumentProcessor {
	private final List<Entry> accountEntries;
	private final EntryExtractor extractor;
	private final NumberParser parser;

	@Inject
	public DocumentProcessor(final EntryExtractor extractor, final NumberParser parser) {
		accountEntries = new LinkedList<>();
		this.extractor = extractor;
		this.parser = parser;
	}

	public void process(InputStream streamedDoc)
			throws DocumentProcessorException {
		try {
			accountEntries.addAll(extractor.extractEntriesFrom(streamedDoc));
		} catch (EntryValidationException e) {
			throw new DocumentProcessorException(e);
		}
	}

	public String entries() {
		return accountEntries
				.stream()
				.map((entry) -> parser.parse(entry.entry.replaceAll("\\n", "")))
				.collect(joining("\n"))
				+ "\n";
	}

	public String unprocessedEntries() {
		return accountEntries.stream().map((entry) -> entry.entry)
				.collect(joining());
	}
}
