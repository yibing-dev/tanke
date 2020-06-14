package com.yibing.tank.collider;

import com.yibing.tank.gamemodel.GameObject;
import com.yibing.tank.gamemodel.Tank;
import com.yibing.tank.gamemodel.Wall;

/**
 * @Author yibing
 * @CreateTime 2020年6月10日 上午6:54:36
 * @Description 类描述:
 */
public class TankWallCollider implements Collider {

	@Override
	public boolean collider(GameObject o1, GameObject o2) {
		if (o1 instanceof Tank && o2 instanceof Wall) {
			Tank t1 = (Tank) o1;
			Wall w = (Wall) o2;
			if (t1.getRect().intersects(w.rect)) {
				t1.back();
			}
		} else if (o1 instanceof Wall && o2 instanceof Tank) {
			return collider(o2, o1);
		}
		return true;
	}

}
