package raphaelpantaleao.bankocr.tests.unit;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import raphaelpantaleao.bankocr.tests.TestConstants;
import raphaelpantaleao.katabanckocr.patterns.AccountPatterns;

public class AccountPatternsTest {

    @Test
    public void shouldMatchZeroPatternInFirstPosition() {
	assertThat(AccountPatterns.ZERO.matches(TestConstants.ZEROS, 0),
		is(TRUE));
    }

    @Test
    public void shouldMatchZeroPatternInSecondPosition() {
	assertThat(AccountPatterns.ZERO.matches(TestConstants.ZEROS, 1),
		is(TRUE));
    }

    @Test
    public void shouldNotReconizeOtherPatternsPatterns() {
	assertThat(AccountPatterns.ONE.matches(TestConstants.ZEROS, 1), is(FALSE));
    }
}
