package com.yibing.tank;

import java.awt.Graphics;

/**
 * @author Administrator
 *
 *         抽象类
 */
public class Tank {
	private int x, y;
	private Dir dir = Dir.DOWN;
	private int speed = 10;

	public Tank(int x, int y, Dir dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public void paint(Graphics g) {
		g.fillRect(x, y, 50, 50);
		switch (dir) {
		case LEFT:
			x -= speed;
			if (x <= 0) {
				dir = Dir.RIGHT;
			}
			break;
		case RIGHT:
			x += speed;
			if (x >= 800) {
				dir = Dir.LEFT;
			}
			break;
		case UP:
			y -= speed;
			if (y <= 0) {
				dir = Dir.DOWN;
			}
			break;
		case DOWN:
			y += speed;
			if (y >= 600) {
				dir = Dir.UP;
			}
			break;
		default:
			break;
		}
	}
}
