package raphaelpantaleao.bankocr.tests.unit;

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
	private final EntryExtractor extractor = new EntryExtractor(new EntryValidator());

	@Test
	public void expectsEntryHasOnlyWhitespaceInLastLine()
			throws EntryValidationException {
		String invalidZeroEntry = " _  _  _  _  _  _  _  _  _ \n"
				+ "| || || || || || || || || |\n"
				+ "|_||_||_||_||_||_||_||_||_|\n"
				+ " _ _                       \n";
		expectedException.expect(EntryValidationException.class);
		expectedException
				.expectMessage(containsString("Last Line could only have whitespaces."));
		extractor
				.extractEntriesFrom(createAStreamWith(ZEROS, invalidZeroEntry));

	}
	
	@Test
	public void expectsEntryToMatchAPattern()
			throws EntryValidationException {
		expectedException.expect(EntryValidationException.class);
		expectedException
				.expectMessage(containsString("Account did not match any number patern."));
		extractor
				.extractEntriesFrom(createAStreamWith(ZEROS, SCANNED_TEXT_WRONG));

	}

	@Test
	public void expectsEntryHasOnlyPipesUnderscoresAndWhitespaceInFirst3Lines()
			throws EntryValidationException {
		String invalidZeroEntry = " _ 1_  _  _  _  _  _  _  _ \n"
				+ "| || || || || || || || || |\n"
				+ "|_||2||_||_||_||_||4||_||_|\n"
				+ "                           \n";

		expectedException.expect(EntryValidationException.class);
		expectedException
				.expectMessage(containsString("First Three lines could only have pipes, underscores and whitespaces."));
		extractor
				.extractEntriesFrom(createAStreamWith(ZEROS, invalidZeroEntry));
	}

	@Test
	public void acceptOnly4Lines() throws EntryValidationException {
		expectedException.expect(EntryValidationException.class);
		expectedException
				.expectMessage(containsString("Entry has insuficient lines."));
		extractor.extractEntriesFrom(createAStreamWith(ZEROS, ONES
				+ "                           "));
	}

	@Test
	public void acceptOnly27chars() throws EntryValidationException {
		assertExceptionWhenHasLinesWithCharactersLessThan27();
		assertExceptionWhenHasLinesWithCharactersGreaterThan27();
	}

	private void assertExceptionWhenHasLinesWithCharactersGreaterThan27()
			throws EntryValidationException {
		assertException("                               \n"
				+ "                               \n"
				+ "                               \n"
				+ "                               \n",
				containsString("Entry has lines with length greater than "
						+ MAX_SCANNER_LINE_LENGTH + "."));
	}

	private void assertExceptionWhenHasLinesWithCharactersLessThan27()
			throws EntryValidationException {
		assertException("     \n     \n     \n     \n",
				containsString("Entry has lines with length less than "
						+ MAX_SCANNER_LINE_LENGTH + "."));
	}

	private void assertException(String text, Matcher<String> m)
			throws EntryValidationException {
		expectedException.expect(EntryValidationException.class);
		expectedException.expectMessage(m);
		extractor.extractEntriesFrom(createAStreamWith(text));
	}
}
