package com.yibing.tank.abstractfactory;

import com.yibing.tank.Dir;
import com.yibing.tank.Group;
import com.yibing.tank.TankFrame;

/**
* @Author yibing
* @CreateTime 2020年6月7日 上午6:38:58
* @Description 类描述:
*/
public class RectFactory extends GameFactory {

	@Override
	public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf,int speed) {
		return new RectTank(x, y, dir, tf, group, speed);
	}

	@Override
	public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
		return new RectBullet(x, y, dir, tf, group);
	}

	@Override
	public BaseExplode createExplode(int x, int y, TankFrame f) {
		return new RecExplode(x, y, f);
	}

}
