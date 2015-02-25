package raphaelpantaleao.bankocr.tests.integration;

import static org.hamcrest.Matchers.containsString;
import static raphaelpantaleao.bankocr.tests.StreamCreator.createAStreamWith;
import static raphaelpantaleao.bankocr.tests.TestConstants.ONES;
import static raphaelpantaleao.bankocr.tests.TestConstants.SCANNED_TEXT_WRONG;
import static raphaelpantaleao.bankocr.tests.TestConstants.ZEROS;
import static raphaelpantaleao.katabanckocr.constants.Constants.MAX_SCANNER_LINE_LENGTH;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import raphaelpantaleao.katabanckocr.exceptions.EntryValidationException;
import raphaelpantaleao.katabanckocr.models.EntryExtractor;
import raphaelpantaleao.katabanckocr.models.EntryValidator;

public class EntryExtractorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private final EntryExtractor extractor = new EntryExtractor(
	    new EntryValidator());

    @Test
    public void expectsEntryHasOnlyWhitespaceInLastLine()
	    throws EntryValidationException {
	String invalidZeroEntry = " _  _  _  _  _  _  _  _  _ \n"
		+ "| || || || || || || || || |\n"
		+ "|_||_||_||_||_||_||_||_||_|\n"
		+ " _ _                       \n";
	expectException(containsString("Last Line could only have whitespaces."));
	extractor
		.extractEntriesFrom(createAStreamWith(ZEROS, invalidZeroEntry));

    }

    @Test
    public void expectsEntryToMatchAPattern() throws EntryValidationException {
	expectException(containsString("Account did not match any number pattern."));
	extractor.extractEntriesFrom(createAStreamWith(ZEROS,
		SCANNED_TEXT_WRONG));

    }

    @Test
    public void expectsEntryHasOnlyPipesUnderscoresAndWhitespaceInFirst3Lines()
	    throws EntryValidationException {
	String invalidZeroEntry = " _ 1_  _  _  _  _  _  _  _ \n"
		+ "| || || || || || || || || |\n"
		+ "|_||2||_||_||_||_||4||_||_|\n"
		+ "                           \n";

	expectException(
		invalidZeroEntry,
		containsString("First Three lines could only have pipes, underscores and whitespaces."));
    }

    @Test
    public void acceptOnly4Lines() throws EntryValidationException {
	expectException(containsString("Entry has insuficient lines."));
	extractor
		.extractEntriesFrom(createAStreamWith("                           \n"));
    }

    @Test
    public void acceptOnly27chars() throws EntryValidationException {
	assertExceptionWhenHasLinesWithCharactersLessThan27();
	assertExceptionWhenHasLinesWithCharactersGreaterThan27();
    }

    private void assertExceptionWhenHasLinesWithCharactersGreaterThan27()
	    throws EntryValidationException {
	String onlyWhitespace = "                               \n"
		+ "                               \n"
		+ "                               \n"
		+ "                               \n";
	expectException(onlyWhitespace,
		containsString("Entry has lines with length greater than "
			+ MAX_SCANNER_LINE_LENGTH + "."));
    }

    private void assertExceptionWhenHasLinesWithCharactersLessThan27()
	    throws EntryValidationException {
	expectException("     \n     \n     \n     \n",
		containsString("Entry has lines with length less than "
			+ MAX_SCANNER_LINE_LENGTH + "."));
    }

    private void expectException(Matcher<String> message) {
	expectedException.expect(EntryValidationException.class);
	expectedException.expectMessage(message);
    }

    private void expectException(String text, Matcher<String> message)
	    throws EntryValidationException {
	expectException(message);
	extractor.extractEntriesFrom(createAStreamWith(text));
    }

}
