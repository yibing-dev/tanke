package com.yibing.tank;

import java.awt.Graphics;

/**
 * @author yibing
 *
 */
public class Explode extends GameObject{

	public static final int WIDTH = ResourceMgr.getInstance().explodes[0].getWidth();
	public static final int HEIGHT = ResourceMgr.getInstance().explodes[0].getHeight();

	private int x, y;
	private boolean living = true;
	GameModel gm = null;

	private int step = 0;

	public Explode(int x, int y, GameModel gm) {
		this.x = x;
		this.y = y;
		this.gm = gm;
	}

	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		if (step >= ResourceMgr.explodes.length) {// 遍历完图片之后，自动移除这个对象，即爆炸消失
			gm.explodes.remove(this);
		}
	}

}
