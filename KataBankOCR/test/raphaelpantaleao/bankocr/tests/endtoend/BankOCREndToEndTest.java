package raphaelpantaleao.bankocr.tests.endtoend;

import static raphaelpantaleao.bankocr.tests.TestConstants.ACCOUNT_FOUND_123456789;
import static raphaelpantaleao.bankocr.tests.TestConstants.SCANNED_TEXT_123456789;
import static raphaelpantaleao.katabanckocr.appconstants.Constants.SCANNER_BUTTON_NAME;
import static raphaelpantaleao.katabanckocr.appconstants.Constants.SELECT_FILE_BUTTON_NAME;

import java.awt.HeadlessException;
import java.lang.reflect.InvocationTargetException;

import org.junit.After;
import org.junit.Test;

public class BankOCREndToEndTest {

	
	private final ApplicationRunner application = new ApplicationRunner();
	
	@Test
	public void userSelectsAScannedFileAndExhibitsItsAccountSuccesfully()
			throws HeadlessException, InvocationTargetException,
			InterruptedException {
		application.openItsUI();
		application
				.receivesActionPeformedByAUserUsingAButtonNamed(SELECT_FILE_BUTTON_NAME);
		application.opensAFileDialogForAFileWith(SCANNED_TEXT_123456789);
		application.showsInputTextAreaReceivedATextLike(SCANNED_TEXT_123456789);

		application.showsAButtonIsEnabledNamed(SCANNER_BUTTON_NAME);
		application
				.receivesActionPeformedByAUserUsingAButtonNamed(SCANNER_BUTTON_NAME);
		application
				.showsOutputTextAreaReceivedATextLike(ACCOUNT_FOUND_123456789);
	}

	@Test
	public void userOpensAMalformedFile() throws HeadlessException,
			InvocationTargetException, InterruptedException {
		application.openItsUI();
		application
				.receivesActionPeformedByAUserUsingAButtonNamed(SELECT_FILE_BUTTON_NAME);
		application
				.opensAFileDialogForAFileWith("---------------------------------");
		application.showsAAlertThatFileHasMalformedText();
		application.showsAButtonIsDisabledNamed(SCANNER_BUTTON_NAME);
	}

	@Test
	public void userCancelsFileDialog() throws HeadlessException,
			InvocationTargetException, InterruptedException {
		application.openItsUI();
		application
				.receivesActionPeformedByAUserUsingAButtonNamed(SELECT_FILE_BUTTON_NAME);
		application.opensAFileDialogForAFileAndCancels();
		application.showsAButtonIsDisabledNamed(SCANNER_BUTTON_NAME);
	}

	@After
	public void dispose() {
		application.dispose();
	}
}
