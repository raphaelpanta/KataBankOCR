package com.github.raphaelpanta.katabankocr.models;

import static java.util.stream.Collectors.joining;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import com.github.raphaelpanta.katabankocr.api.NumberParser;
import com.github.raphaelpanta.katabankocr.exceptions.DocumentProcessorException;
import com.github.raphaelpanta.katabankocr.exceptions.EntryValidationException;

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
				.map((entry) -> parser.parse(entry.entry))
				.collect(joining("\n"))
				+ "\n";
	}

	public String unprocessedEntries() {
		return accountEntries.stream().map((entry) -> entry.entry)
				.collect(joining());
	}
}
