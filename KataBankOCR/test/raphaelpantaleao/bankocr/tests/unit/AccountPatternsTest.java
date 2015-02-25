package raphaelpantaleao.bankocr.tests.unit;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static raphaelpantaleao.bankocr.tests.TestConstants.SCANNED_TEXT_123456789;
import static raphaelpantaleao.bankocr.tests.TestConstants.SCANNED_TEXT_WITH_ERRORS;
import static raphaelpantaleao.bankocr.tests.TestConstants.ZEROS;
import static raphaelpantaleao.katabanckocr.patterns.AccountPatterns.ONE;
import static raphaelpantaleao.katabanckocr.patterns.AccountPatterns.ZERO;
import static raphaelpantaleao.katabanckocr.patterns.AccountPatterns.validatePatternOf;

import org.junit.Test;

public class AccountPatternsTest {

    @Test
    public void shouldMatchZeroPatternInFirstPosition() {
	assertThat(ZERO.matches(ZEROS, 0), is(TRUE));
    }

    @Test
    public void shouldMatchZeroPatternInSecondPosition() {
	assertThat(ZERO.matches(ZEROS, 1), is(TRUE));
    }

    @Test
    public void shouldNotReconizeOtherPatternsPatterns() {
	assertThat(ONE.matches(ZEROS, 1), is(FALSE));
    }

    @Test
    public void shouldReconizedAAccount() {
	assertThat(validatePatternOf(SCANNED_TEXT_123456789), is(TRUE));
    }

    @Test
    public void shouldNotReconizedAMalFormedAccount() {
	assertThat(validatePatternOf(SCANNED_TEXT_WITH_ERRORS), is(FALSE));
    }
}
