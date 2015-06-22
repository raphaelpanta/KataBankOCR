package com.github.raphaelpanta.katabankocr.modules;

import static com.google.inject.name.Names.named;
import static javax.swing.SwingUtilities.invokeAndWait;

import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import com.github.raphaelpanta.katabankocr.api.ErrorHandlerListener;
import com.github.raphaelpanta.katabankocr.api.NumberParser;
import com.github.raphaelpanta.katabankocr.api.StreamProvider;
import com.github.raphaelpanta.katabankocr.config.AccountProps;
import com.github.raphaelpanta.katabankocr.config.ScannerProps;
import com.github.raphaelpanta.katabankocr.controller.DocumentController;
import com.github.raphaelpanta.katabankocr.models.DocumentProcessor;
import com.github.raphaelpanta.katabankocr.models.EntryExtractor;
import com.github.raphaelpanta.katabankocr.models.EntryValidator;
import com.github.raphaelpanta.katabankocr.parser.EnumNumberParser;
import com.github.raphaelpanta.katabankocr.ui.UIFrame;
import com.github.raphaelpanta.katabankocr.ui.errorhandlers.JDialogErrorHandler;
import com.github.raphaelpanta.katabankocr.ui.listeners.ScanFileListener;
import com.github.raphaelpanta.katabankocr.ui.listeners.SelectFileListener;
import com.github.raphaelpanta.katabankocr.ui.streamproviders.FileChooserStreamProvider;
import com.google.inject.AbstractModule;

public class BankOCRModule extends AbstractModule {

	@Override
	protected void configure() {

		try {
			invokeAndWait(() -> {
				bind(AccountProps.class).to(
						AccountProps.DefaultAccountProps.class)
						.asEagerSingleton();
				bind(ScannerProps.class).to(
						ScannerProps.DefaultScannerProps.class)
						.asEagerSingleton();
				bind(EntryExtractor.class).asEagerSingleton();
				bind(EntryValidator.class).asEagerSingleton();
				bind(DocumentProcessor.class).asEagerSingleton();
				bind(DocumentController.class).asEagerSingleton();
				bind(NumberParser.class).to(EnumNumberParser.class)
						.asEagerSingleton();

				bind(ErrorHandlerListener.class).to(JDialogErrorHandler.class)
						.asEagerSingleton();
				bind(StreamProvider.class).to(FileChooserStreamProvider.class)
						.asEagerSingleton();
				bind(ActionListener.class).annotatedWith(
						named("selectFileListener")).to(
						SelectFileListener.class);
				bind(ActionListener.class).annotatedWith(
						named("scanFileListener")).to(ScanFileListener.class);

				bind(UIFrame.class);
			});
		} catch (InvocationTargetException | InterruptedException e) {
			throw new RuntimeException(e);
		}

	}
}
