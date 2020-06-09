package com.yibing.tank.collider;

import com.yibing.tank.gamemodel.Bullet;
import com.yibing.tank.gamemodel.Explode;
import com.yibing.tank.gamemodel.GameObject;
import com.yibing.tank.gamemodel.Tank;

/**
 * @Author yibing
 * @CreateTime 2020年6月9日 上午7:29:44
 * @Description 类描述:
 */
public class BulletTankCollider implements Collider {

	@Override
	public void collider(GameObject o1, GameObject o2) {
		if (o1 instanceof Bullet && o2 instanceof Tank) {
			Bullet b = (Bullet) o1;
			Tank t = (Tank) o2;

			// 如果子弹和坦克是一伙的，则不销毁坦克
			if (b.getGroup() == t.getGroup()) {
				return;
			}
			// TODO：用一个子弹的位置
			// 根据字段位置和子弹的宽高，以及坦克的位置和宽高生成两个矩形
			// 判断两个矩形是否相交，如果相交，则说明子弹和坦克是撞在一起了
			if (b.rect.intersects(t.rect)) {
				// 爆炸
				int ex = t.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
				int ey = t.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
				Explode e = new Explode(ex, ey, b.gm);
				b.gm.add(e);
				// 销毁坦克和子弹
				t.die();
				b.die();
			}
		} else if (o1 instanceof Tank && o2 instanceof Bullet) {
			this.collider(o2, o1);
		} else {
			return;
		}
	}

}
