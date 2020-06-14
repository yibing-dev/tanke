package com.yibing.tank.gamemodel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * @Author yibing
 * @CreateTime 2020年6月14日 上午9:42:00
 * @Description 类描述:
 */
public class Wall extends GameObject {
	public int w, h;

	public Rectangle rect;// 矩形墙

	public Wall(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		this.rect = new Rectangle(x, y, w, h);
		GameModel.getInstance().add(this);
	}

	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, w, h);
		g.setColor(c);

	}

}
