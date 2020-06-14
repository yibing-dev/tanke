package com.yibing.tank.collider;

import com.yibing.tank.gamemodel.Bullet;
import com.yibing.tank.gamemodel.Explode;
import com.yibing.tank.gamemodel.GameObject;
import com.yibing.tank.gamemodel.Tank;
import com.yibing.tank.gamemodel.Wall;

/**
 * @Author yibing
 * @CreateTime 2020年6月9日 上午7:29:44
 * @Description 类描述:
 */
public class BulletWallCollider implements Collider {

	@Override
	public boolean collider(GameObject o1, GameObject o2) {
		if (o1 instanceof Bullet && o2 instanceof Wall) {
			
			Bullet b = (Bullet) o1;
			Wall w = (Wall) o2;
			
			if (b.rect.intersects(w.rect)) {
				b.die();
			}
			
		} else if (o1 instanceof Wall && o2 instanceof Bullet) {
			return this.collider(o2, o1);
		}
		
		return true;
	}

}
