package raphaelpantaleao.katabanckocr.api;

import static javax.swing.SwingUtilities.invokeLater;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NonBlokingActionListener implements ActionListener {

	private final ActionListener actionListener;

	private NonBlokingActionListener(ActionListener e) {
		actionListener = e;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		invokeLater(() -> actionListener.actionPerformed(e));

	}

	public static ActionListener asNonBlocking(ActionListener e) {
		return new NonBlokingActionListener(e);
	}
}
