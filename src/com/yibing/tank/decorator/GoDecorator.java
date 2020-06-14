package com.yibing.tank.decorator;

import java.awt.Graphics;

import com.yibing.tank.gamemodel.GameObject;

/**
 * @Author yibing
 * @CreateTime 2020年6月14日 下午4:14:13
 * @Description 类描述:
 */
public abstract class GoDecorator extends GameObject {
	GameObject go;

	public GoDecorator(GameObject go) {
		this.go = go;
	}

	@Override
	public abstract void paint(Graphics g);

}
