package raphaelpantaleao.katabanckocr.models;

import static raphaelpantaleao.katabanckocr.constants.Constants.MAX_SCANNER_LINE_LENGTH;
import static raphaelpantaleao.katabanckocr.patterns.AccountPatterns.validatePatternOf;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

import raphaelpantaleao.katabanckocr.exceptions.EntryValidationException;

public class EntryValidator {
    private List<String> errors = new ArrayList<>();

    void verifyEntry(Entry anEntry) throws EntryValidationException {

	String[] entryLines = anEntry.entry.split("\\n");

	boolean hasNotExactNumberOfLines = entryLines.length != 4;
	addErrorMessageIf(hasNotExactNumberOfLines,
		"Entry has insuficient lines.");

	boolean allLinesHasExact27chars = true;
	for (String line : entryLines) {
	    addErrorMessageIf(line.length() > MAX_SCANNER_LINE_LENGTH,
		    "Entry has lines with length greater than "
			    + MAX_SCANNER_LINE_LENGTH + ".");
	    addErrorMessageIf(line.length() < MAX_SCANNER_LINE_LENGTH,
		    "Entry has lines with length less than "
			    + MAX_SCANNER_LINE_LENGTH + ".");
	    allLinesHasExact27chars &= line.length() == MAX_SCANNER_LINE_LENGTH;
	}

//	boolean hasNotEndWithWhitespace = !entryLines[3]
//		.equals("                           \n");
//	addErrorMessageIf(hasNotEndWithWhitespace,
//		"Last Line could only have whitespaces.");

	boolean notHaveOnlyPipesUnderscoresOrWhitespaces = !anEntry.entry
		.matches("[\\s\\|\\_\\\\n]+");

	addErrorMessageIf(notHaveOnlyPipesUnderscoresOrWhitespaces,
		"First Three lines could only have pipes, underscores and whitespaces.");

//	if (allLinesHasExact27chars)
//	    addErrorMessageIf(!validatePatternOf(anEntry.entry),
//		    "Account did not match any number pattern. " + anEntry.entry);

	if (!errors.isEmpty()) {
	    throw new EntryValidationException(errors.stream().collect(
		    Collectors.joining("\n")));

	}
    }

    private void addErrorMessageIf(boolean conditionOccourred, String message)
	    throws EntryValidationException {
	if (conditionOccourred) {
	    errors.add(message);
	}
    }

    String lineFrom(Scanner scanner) throws EntryValidationException {
	String line;
	try {
	    line = scanner.nextLine();
	} catch (NoSuchElementException e) {
	    throw new EntryValidationException("Entry has insuficient lines.",
		    e);
	}
	return line;
    }
}
