package raphaelpantaleao.bankocr.tests.endtoend;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static raphaelpantaleao.katabanckocr.constants.Constants.FILE_CHOOSER_NAME;
import static raphaelpantaleao.katabanckocr.constants.Constants.TITLE_NAME;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.hamcrest.Description;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import com.objogate.exception.Defect;
import com.objogate.wl.Prober;
import com.objogate.wl.Query;
import com.objogate.wl.swing.AWTEventQueueProber;
import com.objogate.wl.swing.ComponentSelector;
import com.objogate.wl.swing.driver.JButtonDriver;
import com.objogate.wl.swing.driver.JFileChooserDriver;
import com.objogate.wl.swing.driver.JFrameDriver;
import com.objogate.wl.swing.driver.JLabelDriver;
import com.objogate.wl.swing.driver.JOptionPaneDriver;
import com.objogate.wl.swing.driver.JTextComponentDriver;
import com.objogate.wl.swing.gesture.GesturePerformer;

@SuppressWarnings("unchecked")
public class BankOCRDriver extends JFrameDriver {

	private static final ComponentSelector<JFrame> topLevelFrame = topLevelFrame(
			named(TITLE_NAME), showingOnScreen());
	private File scannedFile;

	private static Prober eventQueueProberWith(final int timeoutMillis) {
		return new AWTEventQueueProber(timeoutMillis, 100);
	}

	public BankOCRDriver(final int aTimeoutInMillis) {
		super(new GesturePerformer(), topLevelFrame,
				eventQueueProberWith(aTimeoutInMillis));
	}

	public void hasTitle(String titleName) {
		this.hasTitle(equalTo(titleName));
	}

	public void hasALabelWith(String labelName, String text) {
		new JLabelDriver(this, named(labelName)).hasText(equalTo(text));
	}

	public void hasAButtonWith(String name, String text) {
		new JButtonDriver(this, JButton.class, named(name), enabled(true))
				.hasText(equalTo(text));
	}

	public void hasAButtonWith(String name) {
		new JButtonDriver(this, JButton.class, named(name)).is(enabled(true));
	}

	public void hasADisabledButtonWith(String name, String text) {
		new JButtonDriver(this, JButton.class, named(name), enabled(false))
				.hasText(equalTo(text));
	}

	public void hasADisabledButtonNamed(String name) {
		new JButtonDriver(this, JButton.class, named(name)).is(enabled(false));
	}

	public void hasATextAreaWith(String textAreaName, String text, int rows,
			int columns) {
		new JTextComponentDriver<JTextArea>(this, JTextArea.class,
				named(textAreaName), numberOfRowsIs(rows),
				numberOfColumnsIs(columns), isReadOnly())
				.hasText(equalTo(text));
	}

	public void opensAFileDialogForAFileWith(String aString) {
		File scannedFile = prepareTmpFileFor(aString);
		JFileChooserDriver jFileChooserDriver = new JFileChooserDriver(this,
				named(FILE_CHOOSER_NAME));
		try {
			jFileChooserDriver.enterManually(scannedFile.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		jFileChooserDriver.approve();
	}

	public void opensAFileDialogAndCancels() {
		JFileChooserDriver jFileChooserDriver = new JFileChooserDriver(this,
				named(FILE_CHOOSER_NAME));
		jFileChooserDriver.cancel();
	}

	private File prepareTmpFileFor(String aString) {
		final String tmpdir = System.getProperty("java.io.tmpdir");
		final File tmpDir = new File(tmpdir);
		scannedFile = new File(tmpDir, "scn.txt");
		try (PrintWriter printWriter = new PrintWriter(scannedFile)) {
			printWriter.print(aString);
		} catch (IOException e) {
			throw new RuntimeException("Could not write a tmp file", e);
		}

		return scannedFile;
	}

	private Matcher<? super JTextArea> numberOfColumnsIs(int columns) {
		String expectedText = "JTextArea with number of columns";
		String actualValueText = "number of columns";
		return createJTextAreaMatcher(columns, "getColumns", expectedText,
				actualValueText);
	}

	private Matcher<? super JTextArea> isReadOnly() {
		return createJTextAreaMatcher(false, "isEditable",
				"JTextArea editable property", "editable property");

	}

	private Matcher<? super JButton> enabled(boolean enabled) {
		return new FeatureMatcher<JButton, Boolean>(equalTo(enabled),
				"Jbutton enable property", "enable property") {
			protected Boolean featureValueOf(JButton jbutton) {
				return jbutton.isEnabled();
			};
		};
	}

	private Matcher<? super JTextArea> numberOfRowsIs(int rows) {
		String expectedText = "JTextArea with number of rows";
		String actualValueText = "number of rows";
		return createJTextAreaMatcher(rows, "getRows", expectedText,
				actualValueText);
	}

	private <T> Matcher<? super JTextArea> createJTextAreaMatcher(T object,
			String methodName, String expectedText, String actualValueText) {

		return new FeatureMatcher<JTextArea, T>(equalTo(object), expectedText,
				actualValueText) {

			@Override
			protected T featureValueOf(JTextArea textArea) {
				Method method = null;
				try {
					method = JTextArea.class.getMethod(methodName);
					return (T) method.invoke(textArea);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException
						| SecurityException e) {
					throw new Defect("Could not invoke method "
							+ method.getName() + ".", e);
				}

			}
		};
	}

	public void performsAActionWith(String buttonName) {
		new JButtonDriver(this, JButton.class, named(buttonName)).click();
	}

	public void deleteTmpFiles() {

		if (scannedFile != null && scannedFile.exists())
			scannedFile.delete();
	}

	public void opensAErrorDialog() {
		JOptionPaneDriver jOptionPaneDriver = new JOptionPaneDriver(this,
				JOptionPane.class);
		jOptionPaneDriver.is(showingOnScreen());
		jOptionPaneDriver.has(aMessageThat(),
				containsString("File is malformed: "));
		jOptionPaneDriver.clickOK();
	}

	private Query<JOptionPane, String> aMessageThat() {
		return new Query<JOptionPane, String>() {
			@Override
			public String query(JOptionPane pane) {
				return pane.getMessage().toString();
			}

			@Override
			public void describeTo(Description desc) {
				desc.appendText("Message");
			}
		};
	}
}
