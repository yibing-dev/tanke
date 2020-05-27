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
	int x = 20, y = 20;

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

		//按下键盘
		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("pressed");
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				x -= 10;
				y -= 10;
				break;
			case KeyEvent.VK_RIGHT:
				x += 10;
				y += 10;
				break;
			case KeyEvent.VK_UP:
				y -= 10;
				break;
			case KeyEvent.VK_DOWN:
				y += 10;
				break;
			}

			repaint();
		}

		// 释放按键
		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("released");
		}
	}

	@Override
	public void paint(Graphics g) {
		System.out.println("print");
		g.fillRect(x, y, 50, 50);
		// x += 10;
		// y += 10;
	}
}
