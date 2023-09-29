package com.github.wildsource;

import javax.swing.SwingUtilities;

import com.github.wildsource.gui.Window;

public class App {

	public static void main(String[] args) throws InterruptedException {
		SwingUtilities.invokeLater(() -> {
			new Window();
		});
	}

}
