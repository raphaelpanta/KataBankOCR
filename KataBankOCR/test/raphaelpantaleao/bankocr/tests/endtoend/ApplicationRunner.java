package raphaelpantaleao.bankocr.tests.endtoend;

import static raphaelpantaleao.katabanckocr.constants.Constants.APP_TITLE;
import static raphaelpantaleao.katabanckocr.constants.Constants.INPUT_LABEL_NAME;
import static raphaelpantaleao.katabanckocr.constants.Constants.INPUT_LABEL_TEXT;
import static raphaelpantaleao.katabanckocr.constants.Constants.INPUT_TEXT_AREA_NAME;
import static raphaelpantaleao.katabanckocr.constants.Constants.MAX_ACCOUNT_LINES;
import static raphaelpantaleao.katabanckocr.constants.Constants.MAX_ACCOUNT_LINE_LENGTH;
import static raphaelpantaleao.katabanckocr.constants.Constants.MAX_SCANNER_LINES;
import static raphaelpantaleao.katabanckocr.constants.Constants.MAX_SCANNER_LINE_LENGTH;
import static raphaelpantaleao.katabanckocr.constants.Constants.OUTPUT_LABEL_NAME;
import static raphaelpantaleao.katabanckocr.constants.Constants.OUTPUT_LABEL_TEXT;
import static raphaelpantaleao.katabanckocr.constants.Constants.OUTPUT_TEXT_AREA_NAME;
import static raphaelpantaleao.katabanckocr.constants.Constants.SCANNER_BUTTON_NAME;
import static raphaelpantaleao.katabanckocr.constants.Constants.SCANNER_BUTTON_TEXT;
import static raphaelpantaleao.katabanckocr.constants.Constants.SELECT_FILE_BUTTON_NAME;
import static raphaelpantaleao.katabanckocr.constants.Constants.SELECT_FILE_BUTTON_TEXT;

import java.awt.HeadlessException;
import java.lang.reflect.InvocationTargetException;

import raphaelpantaleao.katabanckocr.module.Main;

public class ApplicationRunner {

	private BankOCRDriver driver;

	public void openItsUI() throws HeadlessException,
			InvocationTargetException, InterruptedException {
		startApplication();
		initialUIStateAssertions();
	}

	private void initialUIStateAssertions() {
		driver = new BankOCRDriver(1000);
		driver.hasTitle(APP_TITLE);
		driver.hasALabelWith(INPUT_LABEL_NAME, INPUT_LABEL_TEXT);
		driver.hasAButtonWith(SELECT_FILE_BUTTON_NAME, SELECT_FILE_BUTTON_TEXT);
		driver.hasATextAreaWith(INPUT_TEXT_AREA_NAME, "", MAX_SCANNER_LINES,
				MAX_SCANNER_LINE_LENGTH);
		driver.hasALabelWith(OUTPUT_LABEL_NAME, OUTPUT_LABEL_TEXT);
		driver.hasADisabledButtonWith(SCANNER_BUTTON_NAME, SCANNER_BUTTON_TEXT);
		driver.hasATextAreaWith(OUTPUT_TEXT_AREA_NAME, "", MAX_ACCOUNT_LINES,
				MAX_ACCOUNT_LINE_LENGTH);
	}

	private void startApplication() throws InvocationTargetException,
			InterruptedException {
		Main.main(null);
	}

	public void receivesActionPeformedByAUserUsingAButtonNamed(String buttonName) {
		driver.performsAActionWith(buttonName);
	}

	public void showsInputTextAreaReceivedATextLike(String text) {
		driver.hasATextAreaWith(INPUT_TEXT_AREA_NAME, text, MAX_SCANNER_LINES,
				MAX_SCANNER_LINE_LENGTH);
	}

	public void showsOutputTextAreaReceivedATextLike(String text) {
		driver.hasATextAreaWith(OUTPUT_TEXT_AREA_NAME, text, MAX_ACCOUNT_LINES,
				MAX_ACCOUNT_LINE_LENGTH);
	}

	public void showsAButtonIsEnabledNamed(String withAName) {
		driver.hasAButtonWith(withAName);
	}

	public void showsAButtonIsDisabledNamed(String withAName) {
		driver.hasADisabledButtonNamed(withAName);
	}

	public void opensAFileDialogForAFileWith(String aString) {
		driver.opensAFileDialogForAFileWith(aString);
	}

	public void opensAFileDialogForAFileAndCancels() {
		driver.opensAFileDialogAndCancels();
	}

	public void dispose() {
	//driver.dispose();
	driver.deleteTmpFiles();
	}

	public void showsAAlertThatFileHasMalformedText() {
		driver.opensAErrorDialog();
	}
}
