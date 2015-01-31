package raphaelpantaleao.bankocr.tests.integration;

import static java.lang.System.currentTimeMillis;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static raphaelpantaleao.bankocr.tests.StreamCreator.createStreamOf;
import static raphaelpantaleao.bankocr.tests.TestConstants.ACCOUNTS_EXPECTED;
import static raphaelpantaleao.bankocr.tests.TestConstants.NUMBER_IN_DIGITS;
import static raphaelpantaleao.katabanckocr.parser.NumberParserFactory.createParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void documentProcessorCanHandle5000entryFile()
	    throws DocumentProcessorException {
	Long startTime = currentTimeMillis();
	docProcessor.process(a5000EntryStream());
	Long endTime = currentTimeMillis() - startTime;
	assertThat(endTime, lessThan(500L));
    }

    private InputStream a10EntryStream() {
	return createStreamOf(NUMBER_IN_DIGITS);
    }

    private InputStream a5000EntryStream() {
	List<String> list = new ArrayList<String>(500);
	for (int i = 0; i < 500; i++) {
	    list.addAll(asList(NUMBER_IN_DIGITS));
	}
	return createStreamOf(list.toArray(new String[] {}));
    }

    private String concat(String[] strings) {
	return asList(strings).stream().collect(joining());
    }

}