package raphaelpantaleao.bankocr.tests.integration;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static raphaelpantaleao.bankocr.tests.StreamCreator.createStreamOf;
import static raphaelpantaleao.bankocr.tests.TestConstants.ACCOUNTS_EXPECTED;
import static raphaelpantaleao.bankocr.tests.TestConstants.NUMBER_IN_DIGITS;

import java.io.InputStream;

import org.junit.Test;

import com.github.raphaelpanta.katabankocr.config.ScannerProps;
import com.github.raphaelpanta.katabankocr.config.ScannerProps.DefaultScannerProps;
import com.github.raphaelpanta.katabankocr.exceptions.DocumentProcessorException;
import com.github.raphaelpanta.katabankocr.models.DocumentProcessor;
import com.github.raphaelpanta.katabankocr.models.EntryExtractor;
import com.github.raphaelpanta.katabankocr.models.EntryValidator;
import com.github.raphaelpanta.katabankocr.parser.EnumNumberParser;

public class DocumentProcessorTest {

    private static final DefaultScannerProps SCANNER_PROPS = new ScannerProps.DefaultScannerProps();
	private final DocumentProcessor docProcessor = new DocumentProcessor(
	    new EntryExtractor(new EntryValidator(SCANNER_PROPS), SCANNER_PROPS),
new EnumNumberParser());

    @Test
    public void wellformedDigitsConvertsSuccessfully()
	    throws DocumentProcessorException {
	docProcessor.process(a10EntryStream());
	assertThat(docProcessor.entries(),
		is(equalTo(concat(ACCOUNTS_EXPECTED))));
    }

    private InputStream a10EntryStream() {
	return createStreamOf(NUMBER_IN_DIGITS);
    }

    private String concat(String[] strings) {
	return asList(strings).stream().collect(joining());
    }

}