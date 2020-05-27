package com.yibing.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7176408429911310474L;
	//坦克
	Tank myTank = new Tank(200, 200, Dir.DOWN);
	
	//子弹
	Bullet bu = new Bullet(300, 300, Dir.DOWN);

	Dir dir = Dir.DOWN;

	public TankFrame() {
		setSize(800, 600);
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

	@Override
	public void paint(Graphics g) {
		myTank.paint(g);
		bu.paint(g);
	}
}
