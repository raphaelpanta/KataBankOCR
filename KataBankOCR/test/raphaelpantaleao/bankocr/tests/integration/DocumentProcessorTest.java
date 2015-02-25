package raphaelpantaleao.bankocr.tests.integration;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static raphaelpantaleao.bankocr.tests.StreamCreator.createStreamOf;
import static raphaelpantaleao.bankocr.tests.TestConstants.ACCOUNTS_EXPECTED;
import static raphaelpantaleao.bankocr.tests.TestConstants.NUMBER_IN_DIGITS;
import static raphaelpantaleao.katabanckocr.parser.NumberParserFactory.createParser;

import java.io.InputStream;

import org.junit.Test;

import raphaelpantaleao.katabanckocr.exceptions.DocumentProcessorException;
import raphaelpantaleao.katabanckocr.models.DocumentProcessor;
import raphaelpantaleao.katabanckocr.models.EntryExtractor;
import raphaelpantaleao.katabanckocr.models.EntryValidator;

public class DocumentProcessorTest {

    private final DocumentProcessor docProcessor = new DocumentProcessor(
	    new EntryExtractor(new EntryValidator()),
 createParser());

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