package raphaelpantaleao.katabanckocr.api;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.inject.Inject;
import javax.inject.Provider;

import raphaelpantaleao.katabanckocr.controller.DocumentController;

public class ScanFileListener implements ActionListener {

    private final Provider<DocumentController> controller;

    @Inject
    public ScanFileListener(final Provider<DocumentController> controller) {
	this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	controller.get().appendOutput();
    }

}
