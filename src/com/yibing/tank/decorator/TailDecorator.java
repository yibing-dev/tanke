package com.yibing.tank.decorator;

import java.awt.Color;
import java.awt.Graphics;

import com.yibing.tank.gamemodel.GameObject;

/**
 * @Author yibing
 * @CreateTime 2020年6月14日 下午4:20:55
 * @Description 类描述:
 */
public class TailDecorator extends GoDecorator {

	public TailDecorator(GameObject go) {
		super(go);
	}
  
	@Override
	public void paint(Graphics g) {
		this.x = go.x;
		this.y = go.y;
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.drawLine(super.go.x, super.go.y, super.go.x + this.getWidth(), super.go.y + this.getHight());
		g.setColor(c);
	}

	@Override
	public int getWidth() {
		return super.go.getWidth();
	}

	@Override
	public int getHight() {
		return super.go.getHight();
	}

}
