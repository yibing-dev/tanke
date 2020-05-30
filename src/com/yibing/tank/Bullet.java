package com.yibing.tank;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author yibing
 *
 */
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

		switch (dir) {
		case LEFT:
			g.drawImage(ResourceMgr.bulletL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.bulletR, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.bulletU, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, x, y, null);
			break;
		default:
			break;

		}
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
		//让子弹自动销毁
		if (x <= 0 || y <= 0 || x >= TankFrame.GAME_WIDTH || y >= TankFrame.GAME_HEIGHT) {
			live = false;
		}
	}
}
