package com.yibing.tank.abstractfactory;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.yibing.tank.Audio;
import com.yibing.tank.Bullet;
import com.yibing.tank.Dir;
import com.yibing.tank.FireStrategy;
import com.yibing.tank.FireStrategyFactory;
import com.yibing.tank.Group;
import com.yibing.tank.ResourceMgr;
import com.yibing.tank.Tank;
import com.yibing.tank.TankFrame;

/**
* @Author yibing
* @CreateTime 2020年6月7日 上午8:31:59
* @Description 类描述:
*/
public class RectTank extends BaseTank {
	public int x, y;
	public Dir dir = Dir.DOWN;
	private boolean moving = true;
	public TankFrame tf = null;
	private boolean living = true;
	private Random random = new Random();
	private int speed = 10;
	public Group group = Group.Bad;
	public Rectangle rect = new Rectangle();

	public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
	public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();

	public RectTank(int x, int y, Dir dir, TankFrame tf, Group group, int speed) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
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
//			if (random.nextInt(10) > 5) {
//				this.fire(FireStrategyFactory.getStrategy("badFireStrategy"));
//			}
			if (random.nextInt(10) > 5) {
				this.fire();
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
//	public void fire() {
//		int bx = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
//		int by = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
//		new RectFactory().createBullet(bx, by, this.dir, this.tf, this.group);
//		if (this.group == Group.Good) {
//			new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
//		}
//	}
	
	/**
	 * @param strategy 开火策略
	 */
	public void fire(FireStrategy strategy) {
		//strategy.fire(this);
	}

	public void die() {
		this.living = false;
		tf.tanks.remove(this);
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

	public TankFrame getTf() {
		return tf;
	}

	public void setTf(TankFrame tf) {
		this.tf = tf;
	}

	@Override
	public void fire() {
			int bx = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
			int by = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
			new RectFactory().createBullet(bx, by, this.dir, this.tf, this.group);
			if (this.group == Group.Good) {
				new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
			}
	}

}
