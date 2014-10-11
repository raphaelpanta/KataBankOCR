package raphaelpantaleao.bankocr.tests;

import static org.hamcrest.Matchers.containsString;
import static raphaelpantaleao.bankocr.tests.StreamCreator.createAStreamWith;
import static raphaelpantaleao.bankocr.tests.TestConstants.ONES;
import static raphaelpantaleao.bankocr.tests.TestConstants.ZEROS;
import static raphaelpantaleao.katabanckocr.models.values.Entry.create;

import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import raphaelpantaleao.katabanckocr.exceptions.EntryValidationException;
import raphaelpantaleao.katabanckocr.models.values.Entry;

public class EntryTest {

	@SuppressWarnings("unused")
	@Test
	public void acceptOnly4Lines() {
		try {
			Scanner aScanner = new Scanner(createAStreamWith(ZEROS, ONES
					+ "                           "));
			Entry entry = create().with(aScanner);
			Entry entry2 = create().with(aScanner);
			Entry entry3 = create().with(aScanner);
		} catch (EntryValidationException e) {
			Assert.assertThat("Error message expected is", e.getMessage(),
					containsString("Entry has insuficient lines."));
		}
	}
}
