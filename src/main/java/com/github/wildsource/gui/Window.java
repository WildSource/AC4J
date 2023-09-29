package com.github.wildsource.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.github.wildsource.Clicker;
import com.github.wildsource.gui.panels.OptionsPanel;

import net.miginfocom.swing.MigLayout;

public class Window {
	private static JLabel log;

	private JFrame frame;
	private OptionsPanel optionsPanel;
	private Clicker clicker;

	public Window() {
		log = new JLabel();
		clicker = new Clicker(log);
		frameSetup();
		welcomeTxt();
		optionsUI();
		actionUI();
		initLogUI();
		initFrame();
	}

	private void frameSetup() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new MigLayout());
		frame.setTitle("AC4J");
	}

	private void welcomeTxt() {
		frame.add(new JLabel("Welcome to Auto-Clicker for Java !"), "wrap");
	}

	private void optionsUI() {
		/*
		 * frame.add(new JLabel("1 click per "), "split 3");
		 * 
		 * millis = new JTextField("5000"); frame.add(millis);
		 * 
		 * JLabel timeUnit = new JLabel(" milliseconds"); frame.add(timeUnit, "wrap");
		 */
		this.optionsPanel = new OptionsPanel();
		frame.add(optionsPanel.getPanel(), "wrap");
	}

	private void actionUI() {
		JCheckBox alwaysOnTop = new JCheckBox("Always on top");
		alwaysOnTop.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				switch (e.getStateChange()) {
				case 1: {
					frame.setAlwaysOnTop(true);
					log.setText("window is always on top ? " + frame.isAlwaysOnTop());
					break;
				}
				case 2: {
					frame.setAlwaysOnTop(false);
					log.setText("window is always on top ?" + frame.isAlwaysOnTop());
					break;
				}
				}
			}
		});
		frame.add(alwaysOnTop);
		alwaysOnTop.setSelected(true);

		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!clicker.isRunning()) {
					Long delay = Long.parseLong(optionsPanel.getTimeUniTJTextField()
															.getText());
					clicker.click(delay);
				} else {
					log.setText("Clicker is already clicking");
				}
			}
		});
		frame.add(startButton);

		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clicker.stopFlagThread();
				clicker.stopFlagThread();
				log.setText("Thread killed");
			}
		});

		frame.add(stopButton, "wrap");
	}

	private void initLogUI() {
		frame.add(log);
		log.setText("There is no kill switch so be carefull !");
	}

	private void initFrame() {
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
