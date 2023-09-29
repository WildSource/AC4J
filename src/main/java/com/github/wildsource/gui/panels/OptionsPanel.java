package com.github.wildsource.gui.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class OptionsPanel {
	private JPanel panel;
	private JTextField timeUniTJTextField;

	public OptionsPanel() {
		this.panel = new JPanel();
		this.panel.setLayout(new MigLayout());

		this.panel.add(new JLabel("1 click per "));

		this.timeUniTJTextField = new JTextField("5000");
		this.panel.add(this.timeUniTJTextField);

		JLabel timeUnit = new JLabel(" milliseconds");
		this.panel.add(timeUnit);
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JTextField getTimeUniTJTextField() {
		return timeUniTJTextField;
	}

	public void setTimeUniTJTextField(JTextField timeUniTJTextField) {
		this.timeUniTJTextField = timeUniTJTextField;
	}

}
