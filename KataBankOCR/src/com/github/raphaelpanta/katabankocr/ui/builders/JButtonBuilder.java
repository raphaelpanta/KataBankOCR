package com.github.raphaelpanta.katabankocr.ui.builders;

import javax.swing.JButton;

public class JButtonBuilder {

	private JButton button;

	private JButtonBuilder() {
		this.button = new JButton();
	}

	public static JButtonBuilder newButton() {
		JButtonBuilder builder = new JButtonBuilder();
		return builder;
	}

	public JButton build() {
		return button;
	}

	public JButtonBuilder named(String withAName) {
		button.setName(withAName);
		return this;
	}

	public JButtonBuilder withText(String aText) {
		button.setText(aText);
		return this;
	}

	public JButtonBuilder enabled() {
		button.setEnabled(true);
		return this;
	}

	public JButtonBuilder disabled() {
		button.setEnabled(false);
		return this;
	}
}
