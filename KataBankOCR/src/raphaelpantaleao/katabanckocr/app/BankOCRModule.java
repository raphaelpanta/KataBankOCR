package raphaelpantaleao.katabanckocr.app;

import static com.google.inject.name.Names.named;
import static javax.swing.SwingUtilities.invokeAndWait;
import static raphaelpantaleao.katabanckocr.constants.Constants.FILE_CHOOSER_NAME;
import static raphaelpantaleao.katabanckocr.parser.NumberParserFactory.createParser;

import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFileChooser;

import raphaelpantaleao.katabanckocr.api.ErrorHandlerListener;
import raphaelpantaleao.katabanckocr.api.NumberParser;
import raphaelpantaleao.katabanckocr.api.ScanFileListener;
import raphaelpantaleao.katabanckocr.api.SelectFileListener;
import raphaelpantaleao.katabanckocr.api.StreamProvider;
import raphaelpantaleao.katabanckocr.controller.DocumentController;
import raphaelpantaleao.katabanckocr.models.DocumentProcessor;
import raphaelpantaleao.katabanckocr.models.EntryExtractor;
import raphaelpantaleao.katabanckocr.models.EntryValidator;
import raphaelpantaleao.katabankocr.ui.FileChooserStreamProvider;
import raphaelpantaleao.katabankocr.ui.JDialogErrorHandler;
import raphaelpantaleao.katabankocr.ui.UIFrame;

import com.google.inject.AbstractModule;

public class BankOCRModule extends AbstractModule {

    @Override
    protected void configure() {

	try {
	    invokeAndWait(() -> {
		bind(EntryExtractor.class).asEagerSingleton();
		bind(EntryValidator.class).asEagerSingleton();
		bind(DocumentProcessor.class).asEagerSingleton();
		bind(DocumentController.class).asEagerSingleton();
		bind(NumberParser.class).toInstance(createParser());

		final UIFrame topLevelFrame;
		final JDialogErrorHandler jDialogErrorHandler;
		final FileChooserStreamProvider fileChooserStreamProvider;

		topLevelFrame = new UIFrame();
		jDialogErrorHandler = new JDialogErrorHandler(topLevelFrame);
		fileChooserStreamProvider = new FileChooserStreamProvider(
			newJFileChooser(), topLevelFrame);

		bind(UIFrame.class).toInstance(topLevelFrame);
		bind(ErrorHandlerListener.class)
			.toInstance(jDialogErrorHandler);
		bind(StreamProvider.class)
			.toInstance(fileChooserStreamProvider);
		bind(ActionListener.class).annotatedWith(
			named("selectFileListener")).to(
			SelectFileListener.class);
		bind(ActionListener.class).annotatedWith(
			named("scanFileListener")).to(ScanFileListener.class);

	    });
	} catch (InvocationTargetException | InterruptedException e) {
	    throw new RuntimeException(e);
	}

    }

    JFileChooser newJFileChooser() {
	JFileChooser jFileChooser = new JFileChooser();
	jFileChooser.setName(FILE_CHOOSER_NAME);
	return jFileChooser;
    }
}
