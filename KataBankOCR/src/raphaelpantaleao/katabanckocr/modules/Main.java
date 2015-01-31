package raphaelpantaleao.katabanckocr.modules;

import static javax.swing.SwingUtilities.invokeAndWait;

import java.awt.HeadlessException;
import java.lang.reflect.InvocationTargetException;

import raphaelpantaleao.katabanckocr.app.BankOCRModule;
import raphaelpantaleao.katabankocr.ui.UIFrame;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static Injector injector = Guice.createInjector(new BankOCRModule());

    public static void main(String[] args) throws HeadlessException,
	    InvocationTargetException, InterruptedException {
	invokeAndWait(() -> {
	    final UIFrame topLevelFrame = injector.getInstance(UIFrame.class);
	    topLevelFrame.setVisible(true);
	});
    }

}
