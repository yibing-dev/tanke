package com.yibing.tank.abstractfactory;

import com.yibing.tank.Bullet;
import com.yibing.tank.Dir;
import com.yibing.tank.Explode;
import com.yibing.tank.Group;
import com.yibing.tank.TankFrame;

/**
* @Author yibing
* @CreateTime 2020年6月7日 上午6:12:55
* @Description 类描述:
*/
public class DefaultFactory extends GameFactory {

	@Override
	public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf,int speed) {
		return null;
	}

	@Override
	public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
		return new Bullet(x, y, dir, tf, group);
	}

	@Override
	public BaseExplode createExplode(int x, int y, TankFrame f) {
		return new Explode(x, y, f);
	}

}
