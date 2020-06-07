package com.yibing.tank;

import java.awt.Graphics;

import com.yibing.tank.abstractfactory.BaseExplode;

/**
 * @author yibing
 *
 */
public class Explode extends BaseExplode{

	public static final int WIDTH = ResourceMgr.getInstance().explodes[0].getWidth();
	public static final int HEIGHT = ResourceMgr.getInstance().explodes[0].getHeight();

	private int x, y;
	private boolean living = true;
	TankFrame tf = null;

	private int step = 0;

	public Explode(int x, int y, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.tf = tf;
	}

	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		if (step >= ResourceMgr.explodes.length) {// 遍历完图片之后，自动移除这个对象，即爆炸消失
			tf.explodes.remove(this);
		}
	}

}
