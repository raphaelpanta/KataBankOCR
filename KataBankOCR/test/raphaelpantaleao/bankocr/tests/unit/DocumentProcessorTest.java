package raphaelpantaleao.bankocr.tests.unit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static raphaelpantaleao.bankocr.tests.endtoend.TestConstants.ZEROS;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import raphaelpantaleao.katabanckocr.models.DocumentProcessor;

public class DocumentProcessorTest {

	// @Rule
	// public JUnit4Mockery context = new JUnit4Mockery();
	private final DocumentProcessor docProcessor = new DocumentProcessor();

	@Test
	public void digitsConvertionSuccessfully() {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				ZEROS.getBytes());
		docProcessor.process(byteArrayInputStream);
		assertThat(docProcessor.entries(), is(equalTo("000000000\n")));
	}
}