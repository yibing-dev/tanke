package com.yibing.tank.gamemodel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.yibing.Utils.Group;
import com.yibing.tank.mgr.PropertyMgr;

/**
* @Author yibing
* @CreateTime 2020年6月8日 上午7:20:00
* @Description 类描述:
*/
public class GameModel {
	// 坦克
	private Tank myTank = new Tank(200, 400, Dir.DOWN, this, Group.Good, 15);
	// 子弹容器
	public List<Bullet> bullets = new ArrayList<>();
	// 敌方坦克容器
	public List<Tank> tanks = new ArrayList<>();
	// 爆炸集合
	public List<Explode> explodes = new ArrayList<>();
	
	public GameModel() {
		int initTankCount = Integer.parseInt(PropertyMgr.get("initTankCount").toString());
		// 初始化敌方坦克
		for (int i = 0; i < initTankCount; i++) {
			this.tanks.add(new Tank(50 + i * 80, 200, Dir.values()[new Random().nextInt(4)], this, Group.Bad, 10));
		}
	}

	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量" + this.bullets.size(), 10, 60);
		g.drawString("敌方坦克的数量" + this.tanks.size(), 10, 80);
		g.drawString("爆炸的数量" + this.explodes.size(), 10, 100);
		this.myTank.paint(g);
		g.setColor(c);
		// for循环遍历，可以规避集合并发异常
		for (int i = 0; i < this.bullets.size(); i++) {
			this.bullets.get(i).paint(g);
		}
		// 画敌方坦克
		for (int i = 0; i < this.tanks.size(); i++) {
			this.tanks.get(i).paint(g);
		}

		// 子弹和坦克的碰撞检测
		for (int i = 0; i < this.bullets.size(); i++) {
			for (int j = 0; j < this.tanks.size(); j++) {
				//Explode e = new Explode(this.tanks.get(j).getX(), this.tanks.get(j).getY(), this);
				this.bullets.get(i).collidWidth(this.tanks.get(j));
			}
		}

		for (int i = 0; i < this.explodes.size(); i++) {
			this.explodes.get(i).paint(g);
		}
		/*
		 * 迭代遍历，如果其他操作有对该容器执行删除操作，会导致并发操作异常 for (Bullet bullet : bullets) {
		 * bullet.paint(g); }
		 */
	}

	public Tank getMainTank() {
		return myTank;
	}
	
}
