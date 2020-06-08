package com.yibing.tank;

import com.yibing.tank.gamemodel.Audio;

/**
 * @author yibing
 *
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		TankFrame tf = new TankFrame();
		new Thread(()->{
			new Audio("audio/war1.wav").loop();
		}).start();
		while (true) {
			Thread.sleep(100);
			tf.repaint();
		}

	}

}
