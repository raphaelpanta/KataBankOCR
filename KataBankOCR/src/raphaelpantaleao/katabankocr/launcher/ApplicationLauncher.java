package raphaelpantaleao.katabankocr.launcher;


import static com.google.inject.Guice.createInjector;

import java.awt.HeadlessException;
import java.lang.reflect.InvocationTargetException;

import raphaelpantaleao.katabanckocr.module.BankOCRModule;
import raphaelpantaleao.katabankocr.ui.UIFrame;

import com.google.inject.Injector;

public class ApplicationLauncher {
    static final Injector injector = createInjector(new BankOCRModule());

    public static void main(String[] args) throws HeadlessException,
	    InvocationTargetException, InterruptedException {
	final UIFrame topLevelFrame = injector.getInstance(UIFrame.class);
	topLevelFrame.setVisible(true);
    }

}