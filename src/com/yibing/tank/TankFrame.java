package com.yibing.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yibing
 *
 */
public class TankFrame extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7176408429911310474L;
	// 坦克
	Tank myTank = new Tank(200, 400, Dir.DOWN, this, Group.Good, 15);
	// 子弹容器
	List<Bullet> bullets = new ArrayList<>();
	// 坦克容器
	List<Tank> tanks = new ArrayList<>();
	// 爆炸集合
	List<Explode> explodes = new ArrayList<>();

	Dir dir = Dir.DOWN;

	static final int GAME_WIDTH = 1000, GAME_HEIGHT = 800;

	public TankFrame() {
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setResizable(false);
		setTitle("̹坦克大戰");
		setVisible(true);
		this.addKeyListener(new MyKeyListener());

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	class MyKeyListener extends KeyAdapter {
		boolean bL = false;
		boolean bR = false;
		boolean bU = false;
		boolean bD = false;

		// 按下键盘
		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("pressed");
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = true;
				break;
			case KeyEvent.VK_RIGHT:
				bR = true;
				break;
			case KeyEvent.VK_UP:
				bU = true;
				break;
			case KeyEvent.VK_DOWN:
				bD = true;
				break;
			}

			setMainTankDir();
		}

		// 释放按键
		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("released");
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = false;
				break;
			case KeyEvent.VK_RIGHT:
				bR = false;
				break;
			case KeyEvent.VK_UP:
				bU = false;
				break;
			case KeyEvent.VK_DOWN:
				bD = false;
				break;
			case KeyEvent.VK_CONTROL:
				//myTank.fire(FourDirFireStrategy.getInstance());
				myTank.fire(FireStrategyFactory.getStrategy("goodFireStrategy"));
				break;
			}
			setMainTankDir();
		}

		private void setMainTankDir() {
			if (!bL && !bR && !bU && !bD) {
				myTank.setMoving(false);
			} else {
				myTank.setMoving(true);
				if (bL)
					myTank.setDir(Dir.LEFT);
				if (bR)
					myTank.setDir(Dir.RIGHT);
				if (bU)
					myTank.setDir(Dir.UP);
				if (bD)
					myTank.setDir(Dir.DOWN);
			}
		}
	}

	Image offScreenImage = null;

	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.darkGray);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量" + bullets.size(), 10, 60);
		g.drawString("敌方坦克的数量" + tanks.size(), 10, 80);
		g.drawString("爆炸的数量" + explodes.size(), 10, 100);
		myTank.paint(g);
		g.setColor(c);
		// for循环遍历，可以规避集合并发异常
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
		// 画敌方坦克
		for (int i = 0; i < tanks.size(); i++) {
			tanks.get(i).paint(g);
		}

		// 子弹和坦克的碰撞检测
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < tanks.size(); j++) {
				Explode e = new Explode(tanks.get(j).getX(), tanks.get(j).getY(), this);
				bullets.get(i).collidWidth(tanks.get(j));
			}
		}

		for (int i = 0; i < explodes.size(); i++) {
			explodes.get(i).paint(g);
		}
		/*
		 * 迭代遍历，如果其他操作有对该容器执行删除操作，会导致并发操作异常 for (Bullet bullet : bullets) {
		 * bullet.paint(g); }
		 */
	}
}
