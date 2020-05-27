package com.yibing.tank;

import java.awt.Frame;

public class main {
	public static void main(String[] args) throws InterruptedException {
		TankFrame f = new TankFrame();
		while(true) {
			Thread.sleep(1000);
			f.repaint();
		}
	}
}
