package raphaelpantaleao.bankocr.tests.endtoend;

import static raphaelpantaleao.katabanckocr.Constants.INPUT_LABEL_NAME;
import static raphaelpantaleao.katabanckocr.Constants.INPUT_LABEL_TEXT;
import static raphaelpantaleao.katabanckocr.Constants.INPUT_TEXT_AREA_NAME;
import static raphaelpantaleao.katabanckocr.Constants.OUTPUT_LABEL_NAME;
import static raphaelpantaleao.katabanckocr.Constants.OUTPUT_LABEL_TEXT;
import static raphaelpantaleao.katabanckocr.Constants.OUTPUT_TEXT_AREA_NAME;
import static raphaelpantaleao.katabanckocr.Constants.SCANNER_BUTTON_NAME;
import static raphaelpantaleao.katabanckocr.Constants.SCANNER_BUTTON_TEXT;
import static raphaelpantaleao.katabanckocr.Constants.SELECT_FILE_BUTTON_NAME;
import static raphaelpantaleao.katabanckocr.Constants.SELECT_FILE_BUTTON_TEXT;
import static raphaelpantaleao.katabanckocr.Constants.TITLE_TEXT;

import java.awt.HeadlessException;
import java.lang.reflect.InvocationTargetException;

import raphaelpantaleao.katabanckocr.Main;

public class ApplicationRunner {

	private BankOCRDriver driver;

	public void openItsUI() throws HeadlessException,
			InvocationTargetException, InterruptedException {
		startApplication();
		initialUIStateAssertions();
	}

	private void initialUIStateAssertions() {
		driver = new BankOCRDriver(1000);
		driver.hasTitle(TITLE_TEXT);
		driver.hasALabelWith(INPUT_LABEL_NAME, INPUT_LABEL_TEXT);
		driver.hasAButtonWith(SELECT_FILE_BUTTON_NAME, SELECT_FILE_BUTTON_TEXT);
		driver.hasATextAreaWith(INPUT_TEXT_AREA_NAME, "", 3, 27);
		driver.hasALabelWith(OUTPUT_LABEL_NAME, OUTPUT_LABEL_TEXT);
		driver.hasADisabledButtonWith(SCANNER_BUTTON_NAME, SCANNER_BUTTON_TEXT);
		driver.hasATextAreaWith(OUTPUT_TEXT_AREA_NAME, "", 1, 9);
	}

	private void startApplication() throws InvocationTargetException,
			InterruptedException {
		Main.main(null);
	}

	public void receivesActionPeformedByAUserUsingAButtonNamed(String buttonName) {
		driver.performsAActionWith(buttonName);
	}

	public void showsInputTextAreaReceivedATextLike(String text) {
		driver.hasATextAreaWith(INPUT_TEXT_AREA_NAME, text, 3, 27);
	}

	public void showsOutputTextAreaReceivedATextLike(String text) {
		driver.hasATextAreaWith(OUTPUT_TEXT_AREA_NAME, text, 1, 9);
	}

	public void showsAButtonIsEnabledNamed(String string) {
		driver.hasAButtonWith(string);
	}

	public void opensAFileDialogForAFileWith(String aString) {
		driver.opensAFileDialogForAFileWith(aString);
	}
}
