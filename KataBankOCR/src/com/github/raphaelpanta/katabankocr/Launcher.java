package com.github.raphaelpanta.katabankocr;


import static com.google.inject.Guice.createInjector;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

import java.awt.HeadlessException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import com.github.raphaelpanta.katabankocr.modules.BankOCRModule;
import com.github.raphaelpanta.katabankocr.ui.UIFrame;
import com.google.inject.Injector;

public class Launcher {
    static final Injector injector = createInjector(new BankOCRModule());

    public static void main(String[] args) throws HeadlessException,
	    InvocationTargetException, InterruptedException {
	final UIFrame topLevelFrame = injector.getInstance(UIFrame.class);
	SwingUtilities.invokeLater(() -> {
	    topLevelFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    topLevelFrame.pack();
	    topLevelFrame.setVisible(true);
	});
    }

}
