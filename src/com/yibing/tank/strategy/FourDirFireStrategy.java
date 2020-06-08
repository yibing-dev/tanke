package com.yibing.tank.strategy;

import com.yibing.Utils.Group;
import com.yibing.tank.gamemodel.Audio;
import com.yibing.tank.gamemodel.Bullet;
import com.yibing.tank.gamemodel.Dir;
import com.yibing.tank.gamemodel.Tank;

/**
 * @createTime 2020年6月3日 下午1:00:27
 * @author yibing
 */
public class FourDirFireStrategy implements FireStrategy {

	private FourDirFireStrategy() {}

	private static class innerClass {
		static FourDirFireStrategy INSTANCE = new FourDirFireStrategy();
	}

	static FourDirFireStrategy getInstance() {
		return innerClass.INSTANCE;
	}

	@Override
	public void fire(Tank t) {
		int bx = t.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
		int by = t.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
		Dir[] dir = Dir.values();
		for (Dir d : dir) {
			new Bullet(bx, by, d, t.gm, t.group);
		}
		if (t.group == Group.Good) {
			new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
		}

	}

}
