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

import com.yibing.tank.gamemodel.Dir;
import com.yibing.tank.gamemodel.GameModel;
import com.yibing.tank.gamemodel.Tank;
import com.yibing.tank.strategy.FireStrategyFactory;

/**
 * @author yibing
 *
 */
public class TankFrame extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7176408429911310474L;
	
	GameModel gameMdoel = new GameModel();
	

	Dir dir = Dir.DOWN;

	public static final int GAME_WIDTH = 1000;

	public static final int GAME_HEIGHT = 800;

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
				//gameMdoel.myTank.fire(FireStrategyFactory.getStrategy("goodFireStrategy"));
				
				gameMdoel.getMainTank().fire(FireStrategyFactory.getStrategy("goodFireStrategy"));
				break;
			}
			setMainTankDir();
		}

		private void setMainTankDir() {
			Tank myTank = gameMdoel.getMainTank();
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
		gameMdoel.paint(g);
	}
}
