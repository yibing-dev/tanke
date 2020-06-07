package com.yibing.tank.abstractfactory;

import java.awt.Graphics;

import com.yibing.tank.Tank;

/**
* @Author yibing
* @CreateTime 2020年6月7日 上午6:11:01
* @Description 类描述:
*/
public abstract class BaseBullet {

	public abstract boolean collidWidth(Tank tank);

	public abstract void paint(Graphics g);
	
}
