package com.yibing.tank.gamemodel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.yibing.Utils.Group;
import com.yibing.tank.collider.BulletTankCollider;
import com.yibing.tank.collider.Collider;
import com.yibing.tank.collider.ColliderChain;
import com.yibing.tank.collider.TankTankCollider;
import com.yibing.tank.mgr.PropertyMgr;

/**
 * @Author yibing
 * @CreateTime 2020年6月8日 上午7:20:00
 * @Description 类描述:
 */
public class GameModel {
	private static class InnerClass {
		private static GameModel INSTANCE = new GameModel();
	}

	public static GameModel getInstance() {
		return InnerClass.INSTANCE;
	}
	
	static {
		InnerClass.INSTANCE.init();
	}

	// 初始化主站坦克
	private Tank myTank;

	private List<GameObject> objects = new ArrayList<>();

	// 碰撞责任链
	ColliderChain chain = new ColliderChain();

	private GameModel() {
		
	}

	private void init() {
		myTank = new Tank(200, 400, Dir.DOWN, Group.Good, 15);
		int initTankCount = Integer.parseInt(PropertyMgr.get("initTankCount").toString());
		// 初始化敌方坦克
		for (int i = 0; i < initTankCount; i++) {
			new Tank(50 + i * 80, 200, Dir.values()[new Random().nextInt(4)], Group.Bad, 15);
		}
		// 初始化墙
		new Wall(150, 150, 200, 50);
		new Wall(550, 150, 200, 50);
		new Wall(300, 300, 50, 200);
		new Wall(550, 300, 50, 200);
	}

	public void add(GameObject go) {
		this.objects.add(go);
	}

	public void remove(GameObject go) {
		this.objects.remove(go);
	}

	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
//		g.drawString("子弹的数量" + this.bullets.size(), 10, 60);
//		g.drawString("敌方坦克的数量" + this.tanks.size(), 10, 80);
//		g.drawString("爆炸的数量" + this.explodes.size(), 10, 100);
		this.myTank.paint(g);
		g.setColor(c);
		// for循环遍历，可以规避集合并发异常
		for (int i = 0; i < this.objects.size(); i++) {
			this.objects.get(i).paint(g);
		}

		// 碰撞逻辑
		for (int i = 0; i < objects.size(); i++) {
			for (int j = i + 1; j < objects.size(); j++) {
				GameObject o1 = objects.get(i);
				GameObject o2 = objects.get(j);
				chain.collider(o1, o2);
			}
		}

	}

	public Tank getMainTank() {
		return myTank;
	}

}
