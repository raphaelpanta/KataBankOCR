package raphaelpantaleao.bankocr.tests.endtoend;

import java.awt.HeadlessException;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class BankOCREndToEndTest {

	private static final String ACCOUNT_FOUND_123456789 = "123456789\n";
	private static final String SCANNED_TEXT_123456789 = "  _  _     _  _  _  _  _ \n"
			+ "| _| _||_||_ |_   ||_||_|\n" + "||_  _|  | _||_|  ||_| _|\n";
	private final ApplicationRunner application = new ApplicationRunner();

	@Test
	public void userSelectsAScannedFileAndExhibitsItsAccountSuccesfully()
			throws HeadlessException, InvocationTargetException,
			InterruptedException {
		application.openItsUI();
		application
				.receivesActionPeformedByAUserUsingAButtonNamed("select file button");
		application.opensAFileDialogForAFileWith(SCANNED_TEXT_123456789);
		application.showsInputTextAreaReceivedATextLike(SCANNED_TEXT_123456789);

		application.showsAButtonIsEnabledNamed("scanner button");
		application
				.receivesActionPeformedByAUserUsingAButtonNamed("scanner button");
		application
				.showsOutputTextAreaReceivedATextLike(ACCOUNT_FOUND_123456789);
	}

}
