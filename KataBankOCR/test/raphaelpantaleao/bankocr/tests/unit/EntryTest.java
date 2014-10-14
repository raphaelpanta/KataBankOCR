package raphaelpantaleao.bankocr.tests.unit;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static raphaelpantaleao.bankocr.tests.StreamCreator.createAStreamWith;
import static raphaelpantaleao.bankocr.tests.TestConstants.ONES;
import static raphaelpantaleao.bankocr.tests.TestConstants.ZEROS;
import static raphaelpantaleao.katabanckocr.appconstants.Constants.MAX_SCANNER_LINE_LENGTH;
import static raphaelpantaleao.katabanckocr.models.values.Entry.create;

import java.util.Scanner;

import org.hamcrest.Matcher;
import org.junit.Test;

import raphaelpantaleao.katabanckocr.exceptions.EntryValidationException;
import raphaelpantaleao.katabanckocr.models.values.Entry;

@SuppressWarnings("unused")
public class EntryTest {

	@Test
	public void acceptOnly4Lines() {
		try {
			Scanner aScanner = new Scanner(createAStreamWith(ZEROS, ONES
					+ "                           "));
			Entry entry = create().with(aScanner);
			Entry entry2 = create().with(aScanner);
			Entry entry3 = create().with(aScanner);
		} catch (EntryValidationException e) {
			assertThat("Error message", e.getMessage(),
					containsString("Entry has insuficient lines."));
		}
	}

	@Test
	public void acceptOnly27chars() throws EntryValidationException {
		assertExceptionWhenHasLinesWithCharactersLessThan27();
		assertExceptionWhenHasLinesWithCharactersGreaterThan27();
	}

	@Test
	public void acceptOnlyPipesUnderScoresAndWhitespace() {
		String invalidZeroEntry = " _  _  _  _  _  _  _  _  _ \n"
				+ "| || || || || || || || || |\n"
				+ "|_||_||_||_||_||_||_||_||_|\n"
				+ " _ _                       \n";
		Scanner aScanner = new Scanner(createAStreamWith(ZEROS,
				invalidZeroEntry));

		try {
			Entry entry1 = create().with(aScanner);
			Entry entry2 = create().with(aScanner);
		} catch (EntryValidationException e) {
			assertThat(
					"Entry error message",
					e.getMessage(),
					containsString("Entry only accepts pipes, underscores and writespace in first 3 lines and only whitespace in 4th line."));
		}
	}

	private void assertExceptionWhenHasLinesWithCharactersGreaterThan27() {
		assertException("                               \n"
				+ "                               \n"
				+ "                               \n"
				+ "                               \n",
				containsString("Entry has lines with length greater than "
						+ MAX_SCANNER_LINE_LENGTH + "."));
	}

	private void assertExceptionWhenHasLinesWithCharactersLessThan27() {
		assertException("     \n     \n     \n     \n",
				containsString("Entry has lines with length less than "
						+ MAX_SCANNER_LINE_LENGTH + "."));
	}

	private void assertException(String text, Matcher<String> m) {
		try {
			Scanner aScanner = new Scanner(createAStreamWith(text));
			Entry entry = create().with(aScanner);
		} catch (EntryValidationException e) {
			assertThat("Error message", e.getMessage(), m);
		}
	}
}
