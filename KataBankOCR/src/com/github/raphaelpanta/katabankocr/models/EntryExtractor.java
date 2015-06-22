package com.github.raphaelpanta.katabankocr.models;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.inject.Inject;

import com.github.raphaelpanta.katabankocr.config.ScannerProps;
import com.github.raphaelpanta.katabankocr.exceptions.EntryValidationException;

public class EntryExtractor {

    private final EntryValidator validator;
    private final ScannerProps props;

    @Inject
    public EntryExtractor(EntryValidator validator, ScannerProps scannerProps) {
		this.validator = validator;
		this.props = scannerProps;
    }

    public List<Entry> extractEntriesFrom(InputStream input)
	    throws EntryValidationException {
	List<Entry> entries = new ArrayList<Entry>();
	if (input != null)
	    try (Scanner scanner = new Scanner(input)) {
		EntryValidator.ValidationResults validationResults = null;
		while (scanner.hasNextLine()) {
		    Entry entry = createFrom(scanner);
		    if (validationResults == null)
			validationResults = validator.verifyEntry(entry);
		    else
			validationResults.addErrors(validator
				.verifyEntry(entry).getErrorList());
		    entries.add(entry);
		}
		validationResults.throwIfhasErrors();
	    }
	return entries;
    }

    private Entry createFrom(Scanner aScanner) throws EntryValidationException {
	final StringBuilder textBuilder = new StringBuilder();
	String line;
	for (int i = 0; i < props.lines(); i++) {
	    try {
		line = aScanner.nextLine();
	    } catch (NoSuchElementException e) {
		throw new EntryValidationException(
			"Entry has insuficient lines.", e);
	    }
	    textBuilder.append(line).append("\n");
	}
	return new Entry(textBuilder.toString());
    }

}
