package com.yibing.tank.abstractfactory;

import java.awt.Color;
import java.awt.Graphics;

import com.yibing.tank.Audio;
import com.yibing.tank.ResourceMgr;
import com.yibing.tank.TankFrame;

/**
 * @Author yibing
 * @CreateTime 2020年6月7日 上午6:38:30
 * @Description 类描述:
 */
public class RecExplode extends BaseExplode {

	public static final int WIDTH = ResourceMgr.getInstance().explodes[0].getWidth();
	public static final int HEIGHT = ResourceMgr.getInstance().explodes[0].getHeight();

	private int x, y;
	private boolean living = true;
	TankFrame tf = null;

	private int step = 0;

	public RecExplode(int x, int y, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.tf = tf;
		new Thread(() -> {
			new Audio("audio/explode.wav").play();
		}).start();
	}

	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillRect(x, y, 10 * step++, 10 * step);
		if (step >= 15) {// 遍历完图片之后，自动移除这个对象，即爆炸消失
			tf.explodes.remove(this);
		}
		g.setColor(c);
	}

}
