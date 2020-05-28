package com.yibing.tank;

public class main {
	public static void main(String[] args) throws InterruptedException {
		TankFrame f = new TankFrame();

		while (true) {
			Thread.sleep(50);
			f.repaint();
		}

	}
}
