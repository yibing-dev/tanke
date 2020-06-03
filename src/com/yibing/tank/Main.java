package com.yibing.tank;

import java.util.Random;

/**
 * @author yibing
 *
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		int initTankCount = Integer.parseInt(PropertyMgr.get("initTankCount").toString());
		TankFrame tf = new TankFrame();

		// 初始化敌方坦克
		for (int i = 0; i < initTankCount; i++) {
			tf.tanks.add(new Tank(50 + i * 80, 200, Dir.values()[new Random().nextInt(4)], tf, Group.Bad, 10));
		}
		while (true) {
			Thread.sleep(100);
			tf.repaint();
		}

	}

}
