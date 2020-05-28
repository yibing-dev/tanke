package com.yibing.tank;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author yibing
 *
 *         抽象类
 */
public class Tank {
	private int x, y;
	private Dir dir = Dir.DOWN;
	private boolean moving = false;
	private TankFrame tf = null;

	private int speed = 1;

	public Tank(int x, int y, Dir dir, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
	}

	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.setColor(c);
		g.fillRect(x, y, 50, 50);
		this.move();
	}

	private void move() {
		if (!moving) {
			return;
		}
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

	public void fire() {
		tf.bu = new Bullet(this.x, this.y, this.dir);
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

}
