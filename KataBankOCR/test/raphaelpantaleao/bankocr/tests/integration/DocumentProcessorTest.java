package raphaelpantaleao.bankocr.tests.integration;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static raphaelpantaleao.bankocr.tests.StreamCreator.createStreamOf;
import static raphaelpantaleao.bankocr.tests.TestConstants.ACCOUNTS_EXPECTED;
import static raphaelpantaleao.bankocr.tests.TestConstants.NUMBER_IN_DIGITS;

import java.io.InputStream;

import org.junit.Test;

import raphaelpantaleao.katabanckocr.exceptions.DocumentProcessorException;
import raphaelpantaleao.katabanckocr.models.DocumentProcessor;

public class DocumentProcessorTest {

	private final DocumentProcessor docProcessor = new DocumentProcessor();

	@Test
	public void wellformedDigitsConvertsSuccessfully()
			throws DocumentProcessorException {
		docProcessor.process(aStream());
		assertThat(docProcessor.entries(),
				is(equalTo(concat(ACCOUNTS_EXPECTED))));
	}

	private String concat(String[] strings) {
		String result = "";
		for (String account : strings) {
			result += account;
		}
		return result;
	}

	private InputStream aStream() {
	
		return createStreamOf(NUMBER_IN_DIGITS);
	}
}