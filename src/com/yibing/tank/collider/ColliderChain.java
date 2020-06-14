package com.yibing.tank.collider;

import java.util.LinkedList;
import java.util.List;

import com.yibing.tank.gamemodel.GameObject;

/**
 * @Author yibing
 * @CreateTime 2020年6月10日 上午7:15:22
 * @Description 类描述:碰撞责任链
 */
public class ColliderChain implements Collider {

	public ColliderChain() {
		this.add(new BulletTankCollider());
		this.add(new TankTankCollider());
		this.add(new BulletWallCollider());
		this.add(new TankWallCollider());
	}

	private List<Collider> colliders = new LinkedList<>();

	public void add(Collider c) {
		this.colliders.add(c);
	}

	public boolean collider(GameObject o1, GameObject o2) {
		for (int i = 0; i < this.colliders.size(); i++) {
			if (!colliders.get(i).collider(o1, o2)) {
				return false;
			}
		}
		return true;
	}
}
