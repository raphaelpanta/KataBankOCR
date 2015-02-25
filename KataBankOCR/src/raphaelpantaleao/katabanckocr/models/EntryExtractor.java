package raphaelpantaleao.katabanckocr.models;

import static raphaelpantaleao.katabanckocr.constants.Constants.MAX_SCANNER_LINES;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.inject.Inject;

import raphaelpantaleao.katabanckocr.exceptions.EntryValidationException;

public class EntryExtractor {

    private final EntryValidator validator;

    @Inject
    public EntryExtractor(EntryValidator validator) {
	this.validator = validator;
    }

    public List<Entry> extractEntriesFrom(InputStream input)
	    throws EntryValidationException {
	List<Entry> entries = new ArrayList<Entry>();
	if (input != null)
	    try (Scanner scanner = new Scanner(input)) {
		while (scanner.hasNextLine()) {
		    Entry entry = createFrom(scanner);
		    validator.verifyEntry(entry);
		    entries.add(entry);
		}
	    }
	return entries;
    }

    private Entry createFrom(Scanner aScanner) throws EntryValidationException {
	final StringBuilder textBuilder = new StringBuilder();
	String line;
	for (int i = 0; i < MAX_SCANNER_LINES; i++) {
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
