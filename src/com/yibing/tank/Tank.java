package com.yibing.tank;

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
	private boolean living = true;

	private int speed = 10;
	public static final int WIDTH = ResourceMgr.tankD.getWidth();
	public static final int HEIGHT = ResourceMgr.tankD.getHeight();

	public Tank(int x, int y, Dir dir, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
	}

	public void paint(Graphics g) {
		if (!living) {
			return;
		}
		switch (dir) {
		case LEFT:
			g.drawImage(ResourceMgr.tankL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.tankR, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.tankU, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.tankD, x, y, null);
			break;
		default:
			break;

		}
		this.move();
	}

	private void move() {
		if (!living) {
			return;
		}
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
		int bx = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
		int by = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

		tf.bullets.add(new Bullet(bx, by, this.dir, this.tf));
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void die() {
		this.living = false;
		tf.tanks.remove(this);
	}

}
