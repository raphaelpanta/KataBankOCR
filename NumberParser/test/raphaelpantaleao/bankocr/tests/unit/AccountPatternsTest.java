package raphaelpantaleao.bankocr.tests.unit;

import static com.github.raphaelpanta.katabankocr.parser.EnumNumberParser.validatePatternOf;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static raphaelpantaleao.bankocr.tests.TestConstants.SCANNED_TEXT_123456789;
import static raphaelpantaleao.bankocr.tests.TestConstants.SCANNED_TEXT_WITH_ERRORS;

import org.junit.Test;

public class AccountPatternsTest {

    @Test
    public void shouldReconizedAAccount() {
	assertThat(validatePatternOf(SCANNED_TEXT_123456789), is(TRUE));
    }

    @Test
    public void shouldNotReconizedAMalFormedAccount() {
	assertThat(validatePatternOf(SCANNED_TEXT_WITH_ERRORS), is(FALSE));
    }
}
