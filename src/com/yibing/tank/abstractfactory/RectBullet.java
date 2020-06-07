package com.yibing.tank.abstractfactory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.yibing.tank.Audio;
import com.yibing.tank.Dir;
import com.yibing.tank.Explode;
import com.yibing.tank.Group;
import com.yibing.tank.ResourceMgr;
import com.yibing.tank.Tank;
import com.yibing.tank.TankFrame;
import com.yibing.tank.abstractfactory.BaseBullet;

/**
 * @author yibing
 *
 */
public class RectBullet extends BaseBullet {
	private static final int speed = 100;
	private int x, y;
	private Dir dir;

	public static final int WIDTH = ResourceMgr.bulletD.getWidth();
	public static final int HEIGHT = ResourceMgr.bulletD.getHeight();

	Rectangle rect = new Rectangle();

	// 子弹活着
	private boolean living = true;
	public TankFrame tf = null;
	public Group group = Group.Bad;

	public RectBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
		this.group = group;
		this.rect.x = this.x;
		this.rect.y = this.y;
		this.rect.width = this.WIDTH;
		this.rect.height = this.HEIGHT;
		tf.bullets.add(this);
	}

	public void paint(Graphics g) {
		if (!living) {
			tf.bullets.remove(this);
		}
		Color c = g.getColor();
		g.setColor(Color.yellow);
		g.fillRect(x, y, 20, 20);
		g.setColor(c);
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

	// 子弹和坦克碰撞检测
	public boolean collidWidth(Tank tank) {
		// 如果子弹和坦克是一伙的，则不销毁坦克
		if (this.getGroup() == tank.group) {
			return false;
		}
		// TODO：用一个子弹的位置
		// 根据字段位置和子弹的宽高，以及坦克的位置和宽高生成两个矩形
		// 判断两个矩形是否相交，如果相交，则说明子弹和坦克是撞在一起了
		if (this.rect.intersects(tank.rect)) {
			// 爆炸
			int ex = tank.x + Tank.WIDTH / 2 - Explode.WIDTH / 2;
			int ey = tank.y + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
			this.tf.explodes.add(tf.fg.createExplode(ex, ey, tf));
			// 销毁坦克和子弹
			tank.die();
			this.die();
			new Thread(() -> {
				new Audio("audio/explode.wav").play();
			}).start();
			return true;
		}
		return false;
	}

	// 碰撞之后让子弹销毁
	private void die() {
		this.living = false;
		tf.bullets.remove(this);
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}
