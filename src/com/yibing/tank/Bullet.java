package com.yibing.tank;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	private static final int speed = 10;
	private int x, y;
	private Dir dir;
	private static final int WIDTH = 30, HEIGHT = 30;
	// 子弹活着
	private boolean live = true;
	TankFrame tf = null;

	public Bullet(int x, int y, Dir dir, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
	}

	public void paint(Graphics g) {
		if (!live) {
			tf.bullets.remove(this);
		}

		Color c = g.getColor();
		g.setColor(Color.RED);// 设置颜色
		g.fillOval(x, y, WIDTH, HEIGHT);// 设置出现的初始位置
		g.setColor(c);// 还原画笔的颜色
		move();
	}

	private void move() {
		switch (dir) {
		case LEFT:
			x -= speed;
			break;
		case RIGHT:
			x += speed;
			break;
		case UP:
			y -= speed;
			break;
		case DOWN:
			y += speed;
			break;
		default:
			break;
		}
		if (x <= 0 || y <= 0 || x >= TankFrame.GAME_WIDTH || y >= TankFrame.GAME_HEIGHT) {
			live = false;
		}
	}
}
