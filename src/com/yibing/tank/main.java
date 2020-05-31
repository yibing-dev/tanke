package com.yibing.tank;

import java.util.Random;

/**
 * @author yibing
 *
 */
public class main {
	public static void main(String[] args) throws InterruptedException {
		TankFrame tf = new TankFrame();

		// 初始化敌方坦克
		for (int i = 0; i < 10; i++) {
			tf.tanks.add(new Tank(50 + i * 80, 200, Dir.values()[new Random().nextInt(4)], tf, Group.Bad, 10));
		}
		while (true) {
			Thread.sleep(100);
			tf.repaint();
		}

	}

}
