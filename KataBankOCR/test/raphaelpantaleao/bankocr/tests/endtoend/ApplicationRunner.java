package raphaelpantaleao.bankocr.tests.endtoend;

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
		driver.hasTitle("BankOCR App");
		driver.hasALabelWith(itsNameIs("input"), itsTextIs("Input:"));
		driver.hasAButtonWith(itsNameIs("select file button"),
				itsTextIs("Select File..."));
		driver.hasATextAreaWith("input text area", "", 3, 27);
		driver.hasALabelWith(itsNameIs("output"), itsTextIs("Output:"));
		driver.hasADisabledButtonWith(itsNameIs("scanner button"),
				itsTextIs("Scan File..."));
		driver.hasATextAreaWith("output text area", "", 1, 9);
	}

	private void startApplication() throws InvocationTargetException,
			InterruptedException {
		Main.main(null);
	}

	private String itsNameIs(String aName) {
		return aName;
	}

	private String itsTextIs(String aText) {
		return aText;
	}

	public void receivesActionPeformedByAUserUsingAButtonNamed(String buttonName) {
		driver.performsAActionWith(buttonName);
	}

	public void showsInputTextAreaReceivedATextLike(String text) {
		driver.hasATextAreaWith("input text area", text, 3, 27);
	}

	public void showsOutputTextAreaReceivedATextLike(String text) {
		driver.hasATextAreaWith("output text area", text, 1, 9);
	}

	public void showsAButtonIsEnabledNamed(String string) {
		driver.hasAButtonWith(itsNameIs(string));
	}

	public void opensAFileDialogForAFileWith(String aString) {
		driver.opensAFileDialogForAFileWith(aString);
	}
}
