package com.yibing.tank.abstractfactory;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.yibing.tank.Dir;
import com.yibing.tank.FireStrategy;
import com.yibing.tank.Group;
import com.yibing.tank.TankFrame;

/**
* @Author yibing
* @CreateTime 2020年6月7日 上午6:10:16
* @Description 类描述:
*/
public abstract class BaseTank {

	public int x;
	public int y;
	public Dir dir;
	public TankFrame tf;
	public Group group;
	public Rectangle rect;

	public abstract void paint(Graphics g);

	public abstract void die();

	public abstract void setMoving(boolean b);

	public abstract void setDir(Dir left);

	public abstract void fire();

}
