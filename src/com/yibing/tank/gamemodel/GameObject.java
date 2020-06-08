package com.yibing.tank.gamemodel;

import java.awt.Graphics;

/**
 * @Author yibing
 * @CreateTime 2020年6月9日 上午6:43:20
 * @Description 类描述:
 */
public abstract class GameObject {
	int x, y;
	
	public abstract void paint(Graphics g);
}
