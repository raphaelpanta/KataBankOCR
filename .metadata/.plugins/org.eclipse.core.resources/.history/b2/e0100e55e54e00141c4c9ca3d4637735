package raphaelpantaleao.bankocr.tests.unit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static raphaelpantaleao.bankocr.tests.endtoend.TestConstants.ACCOUNTS_EXPECTED;
import static raphaelpantaleao.bankocr.tests.endtoend.TestConstants.NUMBER_IN_DIGITS;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import raphaelpantaleao.katabanckocr.models.DocumentProcessor;

public class DocumentProcessorTest {

	private final DocumentProcessor docProcessor = new DocumentProcessor();

	@Test
	public void digitsConvertionSuccessfully() {
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

	private ByteArrayInputStream aStream() {
		StringBuilder builder = new StringBuilder();
		for (String string : NUMBER_IN_DIGITS) {
			builder.append(string);
		}
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				builder.toString().getBytes());
		return byteArrayInputStream;
	}
}