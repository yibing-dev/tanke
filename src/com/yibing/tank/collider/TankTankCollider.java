package com.yibing.tank.collider;

import com.yibing.tank.gamemodel.GameObject;
import com.yibing.tank.gamemodel.Tank;

/**
 * @Author yibing
 * @CreateTime 2020年6月10日 上午6:54:36
 * @Description 类描述:
 */
public class TankTankCollider implements Collider {

	@Override
	public boolean collider(GameObject o1, GameObject o2) {
		if (o1 instanceof Tank && o2 instanceof Tank) {
			Tank t1 = (Tank) o1;
			Tank t2 = (Tank) o2;
			if(t1.getRect().intersects(t2.getRect())) {
				t1.stop();
				t2.stop();
			}
		}
		return true;
	}

}
