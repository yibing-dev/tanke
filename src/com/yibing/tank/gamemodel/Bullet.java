package com.yibing.tank.gamemodel;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.yibing.Utils.Group;
import com.yibing.tank.TankFrame;
import com.yibing.tank.mgr.ResourceMgr;

/**
 * @author yibing
 *
 */
public class Bullet extends GameObject {
	private static int speed = 30;
	private Dir dir;

	public static final int WIDTH = ResourceMgr.bulletD.getWidth();
	public static final int HEIGHT = ResourceMgr.bulletD.getHeight();

	public Rectangle rect = new Rectangle();

	// 子弹活着
	private boolean living = true;
	private Group group = Group.Bad;

	public Bullet(int x, int y, Dir dir, Group group,int speed) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.speed = speed;
		this.group = group;
		this.rect.x = this.x;
		this.rect.y = this.y;
		this.rect.width = this.WIDTH;
		this.rect.height = this.HEIGHT;
		GameModel.getInstance().add(this);
	}

	public void paint(Graphics g) {
		if (!living) {
			GameModel.getInstance().remove(this);
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
		if (!living) {
			return;
		}
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
		this.rect.x = this.x;
		this.rect.y = this.y;

		// 让子弹自动销毁
		if (x <= 0 || y <= 0 || x >= TankFrame.GAME_WIDTH || y >= TankFrame.GAME_HEIGHT) {
			living = false;
		}
	}

	// 碰撞之后让子弹销毁
	public void die() {
		this.living = false;
		GameModel.getInstance().remove(this);
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHight() {
		return HEIGHT;
	}

}
