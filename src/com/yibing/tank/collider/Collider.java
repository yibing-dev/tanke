package com.yibing.tank.collider;

import com.yibing.tank.gamemodel.GameObject;

/**
 * @Author yibing
 * @CreateTime 2020年6月9日 上午7:22:49
 * @Description 类描述:
 */
public interface Collider {
	void collider(GameObject o1, GameObject o2);
}
