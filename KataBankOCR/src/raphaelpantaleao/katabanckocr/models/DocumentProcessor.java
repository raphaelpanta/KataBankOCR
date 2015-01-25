package raphaelpantaleao.katabanckocr.models;

import static java.util.stream.Collectors.joining;
import static raphaelpantaleao.katabanckocr.parser.NumberParserFactory.createParser;

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

	@Inject
	public DocumentProcessor(final EntryExtractor extractor) {
		accountEntries = new LinkedList<>();
		this.extractor = extractor;
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
		final NumberParser parser = createParser();
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
