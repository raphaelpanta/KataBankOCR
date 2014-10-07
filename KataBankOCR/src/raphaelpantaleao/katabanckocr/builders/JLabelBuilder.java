package raphaelpantaleao.katabanckocr.builders;

import javax.swing.JLabel;

public class JLabelBuilder {

	private JLabel label;

	private JLabelBuilder() {
		this.label = new JLabel();
	}

	public static JLabelBuilder newJLabel() {
		JLabelBuilder builder = new JLabelBuilder();
		return builder;
	}

	public JLabel build() {
		return label;
	}

	public JLabelBuilder named(String withAName) {
		label.setName(withAName);
		return this;
	}

	public JLabelBuilder withText(String aText) {
		label.setText(aText);
		return this;
	}

}
