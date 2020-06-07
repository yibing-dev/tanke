package com.yibing.tank.abstractfactory;

import com.yibing.tank.Dir;
import com.yibing.tank.Group;
import com.yibing.tank.TankFrame;

/**
 * @Author yibing
 * @CreateTime 2020年6月6日 下午8:20:18
 * @Description 类描述:
 */
public abstract class GameFactory {
	abstract public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf,int speed);

	abstract public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, Group group);

	abstract public BaseExplode createExplode(int x, int y, TankFrame f);
}
