package com.github.raphaelpanta.katabankocr.ui.builders;

import javax.swing.JTextArea;

public class JTextAreaBuilder {
	private JTextArea textArea;

	private JTextAreaBuilder() {
		textArea = new JTextArea();
	}

	public static JTextAreaBuilder newTextArea() {
		JTextAreaBuilder builder = new JTextAreaBuilder();
		return builder;
	}

	public JTextArea build() {
		return textArea;
	}

	public JTextAreaBuilder named(String withAName) {
		textArea.setName(withAName);
		return this;
	}

	public JTextAreaBuilder withText(String aText) {
		textArea.setText(aText);
		return this;
	}

	public JTextAreaBuilder withRows(int rows) {
		textArea.setRows(rows);
		return this;
	}

	public JTextAreaBuilder withColumns(int columns) {
		textArea.setColumns(columns);
		return this;
	}

	public JTextAreaBuilder editable() {
		textArea.setEditable(true);
		return this;
	}

	public JTextAreaBuilder nonEditable() {
		textArea.setEditable(false);
		return this;
	}
}
