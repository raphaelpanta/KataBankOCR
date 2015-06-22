package raphaelpantaleao.bankocr.tests.integration;

import static org.hamcrest.Matchers.containsString;
import static raphaelpantaleao.bankocr.tests.StreamCreator.createAStreamWith;
import static raphaelpantaleao.bankocr.tests.TestConstants.SCANNED_TEXT_WITH_ERRORS;
import static raphaelpantaleao.bankocr.tests.TestConstants.ZEROS;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.raphaelpanta.katabankocr.config.ScannerProps.DefaultScannerProps;
import com.github.raphaelpanta.katabankocr.exceptions.EntryValidationException;
import com.github.raphaelpanta.katabankocr.models.EntryExtractor;
import com.github.raphaelpanta.katabankocr.models.EntryValidator;

public class EntryExtractorTest {

    private static final DefaultScannerProps SCANNER_PROPS = new DefaultScannerProps();
	@Rule
    public ExpectedException expectedException = ExpectedException.none();
    private final EntryExtractor extractor = new EntryExtractor(
	    new EntryValidator(SCANNER_PROPS), SCANNER_PROPS);

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
		SCANNED_TEXT_WITH_ERRORS));

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
			+ 27 + "."));
    }

    private void assertExceptionWhenHasLinesWithCharactersLessThan27()
	    throws EntryValidationException {
	expectException("     \n     \n     \n     \n",
		containsString("Entry has lines with length less than "
			+ 27 + "."));
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
