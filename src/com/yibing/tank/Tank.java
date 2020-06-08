package com.yibing.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Properties;
import java.util.Random;

import com.yibing.tank.strategy.FireStrategy;
import com.yibing.tank.strategy.FireStrategyFactory;

/**
 * @author yibing
 *
 *         抽象类
 */
public class Tank extends GameObject{
	public int x, y;
	public Dir dir = Dir.DOWN;
	private boolean moving = true;
	private boolean living = true;
	private Random random = new Random();
	private int speed = 10;
	public Group group = Group.Bad;
	Rectangle rect = new Rectangle();
	public GameModel gm;

	public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
	public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();

	public Tank(int x, int y, Dir dir, GameModel gm, Group group, int speed) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.gm = gm;
		this.group = group;
		this.speed = speed;
		this.rect.x = this.x;
		this.rect.y = this.y;
		this.rect.height = this.HEIGHT;
		this.rect.width = this.WIDTH;
	}

	public void paint(Graphics g) {
		if (!living) {
			return;
		}
		switch (dir) {
		case LEFT:
			g.drawImage(this.group == Group.Good ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(this.group == Group.Good ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
			break;
		case UP:
			g.drawImage(this.group == Group.Good ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
			break;
		case DOWN:
			g.drawImage(this.group == Group.Good ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
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
		if (this.group == Group.Bad) {
			if (random.nextInt(10) > 5) {
				//this.fire(DefaultFireStrategy.getInstance());
				this.fire(FireStrategyFactory.getStrategy("badFireStrategy"));
			}
			randomDir();
		}
		boundsCheck();
		this.rect.x = this.x;
		this.rect.y = this.y;
	}

	// 边界检测
	private void boundsCheck() {
		if (this.x < 0) {
			this.x = 0;
		}
		if (this.y < 30) {
			this.y = 30;
		}
		if (this.x > TankFrame.GAME_WIDTH - this.WIDTH) {
			x = TankFrame.GAME_WIDTH - this.WIDTH;
		}
		if (this.y > TankFrame.GAME_HEIGHT - this.HEIGHT) {
			y = TankFrame.GAME_HEIGHT - this.HEIGHT;
		}
	}

	private void randomDir() {
		// 随机获取枚举类的下标，返回随机的方向
		if (random.nextInt(100) > 95) {
			this.dir = Dir.values()[random.nextInt(4)];
		}
	}

	/**
	 * @param strategy 开火策略
	 */
	public void fire(FireStrategy strategy) {
		strategy.fire(this);
	}

	public void die() {
		this.living = false;
		gm.tanks.remove(this);
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public GameModel getGm() {
		return gm;
	}

	public void setGm(GameModel gm) {
		this.gm = gm;
	}
}
