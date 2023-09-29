package com.github.wildsource;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import javax.swing.JLabel;

public class Clicker {
	private Robot clickRobot;
	private volatile boolean isRunning;
	private JLabel log;

	public Clicker(JLabel logger) {
		log = logger;
		this.isRunning = false;
		try {
			this.clickRobot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void click() {
		this.clickRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		this.clickRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	public void click(Long delay) {

		this.isRunning = true;

		Thread clickerThread = new Thread(new Runnable() {

			@Override
			public void run() {
				int clickAmount = 0;
				countdown(5);
				while (isRunning) {
					try {
						Thread.sleep(delay);
						click();
						log.setText("Mouse has clicked " + clickAmount++ + " time.");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		clickerThread.start();
		System.gc();
	}

	private void countdown(int seconds) {
		try {
			for (int i = 1; i <= seconds; i++) {
				Thread.sleep(1000);
				log.setText("Auto-Clicker will start in " + i + " seconds.");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void stopFlagThread() {
		this.isRunning = false;
	}

	public void triggerFlagThread() {
		this.isRunning = true;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

}
