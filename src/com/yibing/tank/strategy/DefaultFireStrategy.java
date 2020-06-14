package com.yibing.tank.strategy;

import com.yibing.Utils.Group;
import com.yibing.tank.decorator.RectDecorator;
import com.yibing.tank.decorator.TailDecorator;
import com.yibing.tank.gamemodel.Audio;
import com.yibing.tank.gamemodel.Bullet;
import com.yibing.tank.gamemodel.GameModel;
import com.yibing.tank.gamemodel.Tank;

/**
 * @createTime 2020年6月3日 下午12:39:17
 * @author yibing 
 */
public class DefaultFireStrategy implements FireStrategy {
	// 为了避免每次使用发火策略都new对象，因此设计为单例模式
	private DefaultFireStrategy() {
	} 

	static class innerClass {
		private static DefaultFireStrategy INSTANCE = new DefaultFireStrategy();
	}

	static DefaultFireStrategy getInstance() {
		return innerClass.INSTANCE;
	}

	@Override
	public void fire(Tank t) {
		int bx = t.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
		int by = t.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
		new Bullet(bx, by, t.dir, t.group, 20);
		//GameModel.getInstance().add(new RectDecorator(new TailDecorator(new Bullet(bx, by, t.dir, t.group, 20))));
		if (t.group == Group.Good) {
			new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
		}
	}

}
