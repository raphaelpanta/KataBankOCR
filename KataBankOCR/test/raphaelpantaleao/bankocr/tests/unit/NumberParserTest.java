package raphaelpantaleao.bankocr.tests.unit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static raphaelpantaleao.bankocr.tests.TestConstants.SCANNED_TEXT_123456789;
import static raphaelpantaleao.bankocr.tests.TestConstants.SCANNED_TEXT_WITH_ERRORS;
import static raphaelpantaleao.katabanckocr.parser.NumberParserFactory.createParser;

import org.junit.Test;

import raphaelpantaleao.bankocr.tests.TestConstants;
import raphaelpantaleao.katabanckocr.api.NumberParser;

public class NumberParserTest {

    private NumberParser parser = createParser();

    @Test
    public void shouldReconizeZeros() {
	assertThat(parser.parse(TestConstants.ZEROS), is(equalTo("000000000")));
    }

    @Test
    public void shouldNotReconizeNumber() {
	assertThat(parser.parse(SCANNED_TEXT_WITH_ERRORS), is(nullValue()));
    }

    @Test
    public void shouldReconizeAllNumbers() {
	assertThat(parser.parse(SCANNED_TEXT_123456789),
		is(equalTo("123456789")));
    }
}
